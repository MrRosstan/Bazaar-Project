
package com.project.Baazar.Project.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// DTO for displaying the sell
@Getter @Setter
public class GetSellDTO {
    private Long id_sell;
    private LocalDate sell_date;
    private Double total;
    private String name_customer;
    private List<GetSellDTOAddOn> productList;

    public GetSellDTO() {
    }

    public GetSellDTO(Long id_sell, LocalDate sell_date, Double total, String name_customer, List<GetSellDTOAddOn> productList) {
        this.id_sell = id_sell;
        this.sell_date = sell_date;
        this.total = total;
        this.name_customer = name_customer;
        this.productList = productList;
    }
    
    
    
}
