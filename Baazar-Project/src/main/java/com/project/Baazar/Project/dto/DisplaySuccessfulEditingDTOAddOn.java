
package com.project.Baazar.Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplaySuccessfulEditingDTOAddOn {
    private Long id_product;
    private String name_product;
    private String brand_product;

    public DisplaySuccessfulEditingDTOAddOn() {
    }

    public DisplaySuccessfulEditingDTOAddOn(Long id_product, String name_product, String brand_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.brand_product = brand_product;
    }
    
}
