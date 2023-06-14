package com.portfolio.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Portfolio {
    private String name;
    private Integer portfolioId;
    private Float nav;
    private Float dailyReturn;
    private Float monthlyReturn;
    private Float yearlyReturn;
    private Float volatility;
    private String[] holdingSymbols;
    private Float[] holdingAmounts;
}


