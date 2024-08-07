
package com.project.Baazar.Project.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// DTO for displaying all customer sales and products 
@Getter @Setter
public class DisplayCustomerSellProductDTO {
    private Long id_customer;
    private String dni_customer;
    private String name_customer;
    private String lastName_customer;
    private DisplayCustomerSellProductDTOAddOnPt2 sellsObj;
    private List<DisplayCustomerSellProductDTOAddON> listProducts;

    public DisplayCustomerSellProductDTO() {
    }

    public DisplayCustomerSellProductDTO(Long id_customer, String dni_customer, String name_customer, String lastName_customer, DisplayCustomerSellProductDTOAddOnPt2 sellsObj, List<DisplayCustomerSellProductDTOAddON> listProducts) {
        this.id_customer = id_customer;
        this.dni_customer = dni_customer;
        this.name_customer = name_customer;
        this.lastName_customer = lastName_customer;
        this.sellsObj = sellsObj;
        this.listProducts = listProducts;
    }

 

 


    
    
    
}
