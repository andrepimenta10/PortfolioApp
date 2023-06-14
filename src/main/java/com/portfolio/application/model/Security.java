package com.portfolio.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Security {
    Float avgTotalVolume;
    Float latestPrice;
    Float previousVolume;
    String symbol;
    Float iexVolume;
    Float iexAskPrice;
    Float iexAskSize;
    Float iexBidPrice;
    Float iexBidSize;
    String companyName;
}
