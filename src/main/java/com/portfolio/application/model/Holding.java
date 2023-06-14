package com.portfolio.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Holding {
    Float units;
    Float holdingValue;
    Security security;
}
