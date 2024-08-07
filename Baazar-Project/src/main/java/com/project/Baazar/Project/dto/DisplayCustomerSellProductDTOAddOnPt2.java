
package com.project.Baazar.Project.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplayCustomerSellProductDTOAddOnPt2 {
     private Long id_sell;
     private LocalDate sell_date;
     private Double total;

    public DisplayCustomerSellProductDTOAddOnPt2() {
    }

    public DisplayCustomerSellProductDTOAddOnPt2(Long id_sell, LocalDate sell_date, Double total) {
        this.id_sell = id_sell;
        this.sell_date = sell_date;
        this.total = total;
    }
     
     
}
