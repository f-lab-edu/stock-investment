package com.jstock.agent.runtime.dto;

import lombok.Getter;

@Getter
public class Body {

    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
    private Items items;

}
