package com.jstock.agent.runtime.depository;

import com.jstock.agent.runtime.dto.Items;
import com.jstock.agent.runtime.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositoryApiRT {

    private final RestTemplate restTemplate;

    @Value("${serviceKey}")
    private String serviceKey;

    @Scheduled(fixedRateString = "5", initialDelay = 3000)
    public void stockInfoRequest() {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String url = URLDecoder.decode("/getStockPriceInfo?serviceKey=" + serviceKey + "&resultType=json", StandardCharsets.UTF_8);
        StockResponse exchange = restTemplate.getForObject(url, StockResponse.class);
        List<Items.Item> items = exchange.getResponse().getBody().getItems().getItem();
        log.info("DispositoryApiRT running");
        log.info(items.toString());
    }
}
