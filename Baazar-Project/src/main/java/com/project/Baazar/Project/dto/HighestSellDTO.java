
package com.project.Baazar.Project.dto;

import lombok.Getter;
import lombok.Setter;

// DTO for displaying the highest sale
@Getter @Setter
public class HighestSellDTO {
    
    private Long id_sell;
    private Double total_sell;
    private int quantity_products;
    private String name_customer;
    private String lastName_customer;

    public HighestSellDTO() {
    }

    public HighestSellDTO(Long id_sell, Double total_sell, int quantity_products, String name_customer, String lastName_customer) {
        this.id_sell = id_sell;
        this.total_sell = total_sell;
        this.quantity_products = quantity_products;
        this.name_customer = name_customer;
        this.lastName_customer = lastName_customer;
    }
    
    
}
