
package com.project.Baazar.Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductBySellDTOAddOn {
    private String name_product;
    private String brand;
    private Double cost;

    public ProductBySellDTOAddOn() {
    }

    public ProductBySellDTOAddOn(String name_product, String brand, Double cost) {
        this.name_product = name_product;
        this.brand = brand;
        this.cost = cost;
    }

  
    
}
