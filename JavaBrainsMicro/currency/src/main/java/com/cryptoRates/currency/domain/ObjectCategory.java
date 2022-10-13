package com.cryptoRates.currency.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectCategory {
    private String id;
    private String name;
    private double market_cap;
    private double market_cap_change_14th;
    private String content;
    private List<String> top_3_coins;
    private double volume_24th;
    private String updated_at;
}
