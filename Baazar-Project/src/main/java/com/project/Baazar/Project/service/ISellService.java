
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.DisplaySuccessfulEditingDTO;
import com.project.Baazar.Project.dto.GetSellDTO;
import com.project.Baazar.Project.dto.HighestSellDTO;
import com.project.Baazar.Project.dto.TotalRevenueAndSellsOnADayDTO;
import com.project.Baazar.Project.model.Sell;
import java.time.LocalDate;
import java.util.List;


public interface ISellService {
    
    // Create a Sell
    public void saveSell(Sell sell);
    
    // Bring all the Sell
    public List<GetSellDTO> getAllSell();
    
    // Bring one Sell
    public GetSellDTO getOneSell(Long id_sell);
    
    // Delete a Sell
    public void deleteSell(Long id_sell);
    
    // Edit a Sell
    public DisplaySuccessfulEditingDTO editSell(Sell sell);
    
    // Ge the total amount of sells and total revenue on a day
    public TotalRevenueAndSellsOnADayDTO getTotalRevenueAndSellsOnADay(LocalDate theDay);
    
    // Get the highest sell
    public HighestSellDTO getHighestSell();
}
