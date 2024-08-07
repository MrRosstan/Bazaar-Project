
package com.project.Baazar.Project.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

// DTO for displaying the total sales of a day and the amount of products sold
@Getter @Setter
public class TotalRevenueAndSellsOnADayDTO {
    private LocalDate dateInfo;
    private int  totalSellsADay ;
    private double totalRevenueADay;

    public TotalRevenueAndSellsOnADayDTO() {
    }

    public TotalRevenueAndSellsOnADayDTO(LocalDate dateInfo, int totalSellsADay, double totalRevenueADay) {
        this.dateInfo = dateInfo;
        this.totalSellsADay = totalSellsADay;
        this.totalRevenueADay = totalRevenueADay;
    }

  
    
 
    
    
}
