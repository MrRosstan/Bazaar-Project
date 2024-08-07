
package com.project.Baazar.Project.controller;

import com.project.Baazar.Project.dto.DisplaySuccessfulEditingDTO;
import com.project.Baazar.Project.dto.GetSellDTO;
import com.project.Baazar.Project.dto.HighestSellDTO;
import com.project.Baazar.Project.dto.TotalRevenueAndSellsOnADayDTO;
import com.project.Baazar.Project.model.Sell;
import com.project.Baazar.Project.service.ISellService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellController {
    
    @Autowired
    private ISellService sellServ;
    
     // Save Sell
    @PostMapping("/sell/create")
    public String creatSell(@RequestBody Sell  sell){
        sellServ.saveSell(sell);
         
        return"The sale has been created properly";
    }
    
    // Get all sales
    @GetMapping("/sell/find/all")
    public List<GetSellDTO> getAllSell(){
        return sellServ.getAllSell();
    }
    
    // Find an specific sale
    @GetMapping("/sell/find/{id_sell}")
    public GetSellDTO getOneSell(@PathVariable Long id_sell){
        return sellServ.getOneSell(id_sell);
    }
    
    // Delete a sell
    @DeleteMapping("/sell/delete/{id_sell}")
    public String deleteSell(@PathVariable Long id_sell){
        sellServ.deleteSell(id_sell);
        return "The sale has being deleted";
    }
    
    // Edit a sell
    @PutMapping("/sell/edit")
    public DisplaySuccessfulEditingDTO  editSell(@RequestBody Sell sell){
        return sellServ.editSell(sell);
    }
    
    // Get total revenue and the amount of sells on a day
    @GetMapping("/sell/date/{sell_date}")
    public TotalRevenueAndSellsOnADayDTO getTotalRevenueAndSellsADay(@PathVariable LocalDate sell_date ){
        return sellServ.getTotalRevenueAndSellsOnADay(sell_date);
    }
    
    @GetMapping("/sell/highest")
    public HighestSellDTO getHighestSell(){
        return sellServ.getHighestSell();
    }
}
