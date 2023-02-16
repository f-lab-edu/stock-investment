package com.jstock.agent.runtime.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.List;

@Getter
public class Items {

    private List<Item> item;

    @Getter
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Item {

        // 기준일자
        private String basDt;
        // 단축코드 (종목 코드보다 짧으면서 유일성이 보장되는 코드(6자리))
        private String srtnCd;
        // ISIN 코드 (국제 채권 식별 번호. 유가증권(채권)의 국제인증 고유번호)
        private String isinCd;
        // 종목명 (유가증권 국제인증 고유번호 코드 이름)
        private String itmsNm;
        // 주식의 시장 구분
        private String mrktCtg;
        // 정규시장의 매매시간종료시까지 형성되는 최종가격
        private double clpr;
        // 전일 대비 등락
        private double vs;
        // 전일 대비 등락에 따른 비율
        private double fltRt;
        // 정규시장의 매매시간 개시후 형성되는 최초가격
        private double mkp;
        // 하루 중 가격의 최고치
        private double hipr;
        // 하루 중 가격의 최저치
        private double lopr;
        // 체결수량의 누적 합계
        private double trqu;
        // 거래건 별 체결가격 * 체결수량의 누적 합계
        private double trPrc;
        // 종목의 상장주식수
        private double lstgStCnt;
        // 종가 * 상장주식수
        private double mrktTotAmt;
    }
}
