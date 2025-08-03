package com.general.store.trial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductItem {
    private final String name;
    private final String price;
    private boolean addToCartButtonVisible;
}
