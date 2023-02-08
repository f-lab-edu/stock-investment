package com.jstock.agent.runtime.depository;

import com.jstock.agent.runtime.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositoryApiRT {

    private final RestTemplate restTemplate;

    @Scheduled(fixedRateString = "5", initialDelay = 3000)
    public void stockInfoRequest() throws URISyntaxException, UnsupportedEncodingException {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        URI uri = new URI("/getStockPriceInfo?serviceKey=hPAfHi0XmaDwAbzLoS7xPf2pnOCqVch48IB6qua4HxANuhrtHZSt3zrCLDWOiSXj7h8yjK0Vf22SrDOHvRs4wQ%3D%3D&resultType=json");
        String url = URLDecoder.decode("/getStockPriceInfo?serviceKey=hPAfHi0XmaDwAbzLoS7xPf2pnOCqVch48IB6qua4HxANuhrtHZSt3zrCLDWOiSXj7h8yjK0Vf22SrDOHvRs4wQ%3D%3D&resultType=json", "UTF-8");
        StockResponse exchange = restTemplate.getForObject(url, StockResponse.class);
        log.info("DispositoryApiRT running");
        log.info(exchange.toString());
    }
}
