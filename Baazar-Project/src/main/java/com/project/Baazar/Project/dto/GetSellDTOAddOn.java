
package com.project.Baazar.Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetSellDTOAddOn {
    private Long id_product;
    private String name_product;
    private String brand_product;
    private Double cost_product;

    public GetSellDTOAddOn() {
    }

    public GetSellDTOAddOn(Long id_product, String name_product, String brand_product, Double cost_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.brand_product = brand_product;
        this.cost_product = cost_product;
    }
}
