
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.DisplaySuccessfulEditingDTO;
import com.project.Baazar.Project.dto.DisplaySuccessfulEditingDTOAddOn;
import com.project.Baazar.Project.dto.GetSellDTO;
import com.project.Baazar.Project.dto.GetSellDTOAddOn;
import com.project.Baazar.Project.dto.HighestSellDTO;
import com.project.Baazar.Project.dto.TotalRevenueAndSellsOnADayDTO;
import com.project.Baazar.Project.model.Customer;
import com.project.Baazar.Project.model.Product;
import com.project.Baazar.Project.model.Sell;
import com.project.Baazar.Project.repositoryy.ICustomerRepository;
import com.project.Baazar.Project.repositoryy.IProductRepository;
import com.project.Baazar.Project.repositoryy.ISellRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellService implements ISellService{
    
    @Autowired
    private ISellRepository sellRep;
    @Autowired
    private IProductRepository proRep;
    @Autowired
    private IProductService proServ;
    @Autowired
    private ICustomerRepository customerRep;
    
    @Override
    public void saveSell(Sell sell) {
        
        // Doing and if statement in case the user gives a sell without product and quantity
        if (sell.getProductList() != null && !sell.getProductList().isEmpty()){
            
            // Initialize total sell amount
            BigDecimal totalSell = BigDecimal.ZERO;
            
            // Iterate over each product in the sell
            for (Product product : sell.getProductList()){
               
             // Fetch the existing product from the repository 
              Product existingProduct = proRep.findById(product.getId_product()).orElse(null);

              // Check if the product exist.
              if (existingProduct == null){
                  throw new RuntimeException("Product not found: " + product.getId_product());
              }
              
              // Calculate the new quantity after the sale
              double newQuantity = existingProduct.getQuantity_product() - product.getQuantity_product();

              // Check for suficient stock
              if (newQuantity < 0){
                  throw new RuntimeException("Insufficient stock for product " + product.getName_product());
              }

              // Calculate the total cost for the quantity sold of this product
                BigDecimal productCost = BigDecimal.valueOf(existingProduct.getCost_product());
                BigDecimal quantity = BigDecimal.valueOf(product.getQuantity_product());
                totalSell = totalSell.add(productCost.multiply(quantity));
              
              // Update the quantity for the product
              existingProduct.setQuantity_product(newQuantity);
              
              // Save the updated product
              proServ.editProduct(existingProduct);
               }
            
            // Round the total cost of the sell to one decimal place
            BigDecimal roundedTotalSell = totalSell.setScale(1, RoundingMode.HALF_UP);
            sell.setTotal(roundedTotalSell.doubleValue());
        }
        
        // Fetch and set the customer if present
        if (sell.getOneCustomer() != null && sell.getOneCustomer().getId_customer() != null) {
            Customer customer = customerRep.findById(sell.getOneCustomer().getId_customer())
                    .orElseThrow(() -> new RuntimeException("Customer not found: " + sell.getOneCustomer().getId_customer()));
            sell.setOneCustomer(customer);
        }
        
        // Saving the new sell
        sellRep.save(sell);

    }

    @Override
    public List<GetSellDTO> getAllSell() {
        // Retrieve all sells from the repository
        List<Sell> allSellsList =  sellRep.findAll();
        
        // Initialize a list to hold DTOs for all sells
        List<GetSellDTO> getSellDTOList = new ArrayList<>();
        
        // Iterate over each sell to convert it to a DTO
        for(Sell sell : allSellsList){
            // Create a DTO object for the current sell
            GetSellDTO getSellDTOobj = new GetSellDTO();
            // Setting the sell data into the DTO
            getSellDTOobj.setId_sell(sell.getId_sell());
            getSellDTOobj.setSell_date(sell.getSell_date());
            getSellDTOobj.setTotal(sell.getTotal());
           
            // Check if the customer is available before accessing their data
            if (sell.getOneCustomer() != null) {
                getSellDTOobj.setName_customer(sell.getOneCustomer().getName_customer());
            } else {
                getSellDTOobj.setName_customer("Unknown Customer");
            }
            
            // Initialize a list to hold DTOs for products in the current sell
            List<GetSellDTOAddOn> getSellDTOAddOnList = new ArrayList<>(); 
            
           // Check if the product list is not null or empty
            if (sell.getProductList() != null && !sell.getProductList().isEmpty()) {
            // Iterate over each product in the sell to convert it to a DTO
            for (Product product : sell.getProductList()) {
                // Create a DTO object for the current product
                GetSellDTOAddOn getSellDTOAddOn = new GetSellDTOAddOn();
                getSellDTOAddOn.setId_product(product.getId_product());
                getSellDTOAddOn.setName_product(product.getName_product());
                getSellDTOAddOn.setBrand_product(product.getBrand_product());
                getSellDTOAddOn.setCost_product(product.getCost_product());

                // Add the product DTO to the list
                getSellDTOAddOnList.add(getSellDTOAddOn);
              }
           } else {
            // Handle the case where there are no products for the sell
            System.out.println("No products found for sell ID: " + sell.getId_sell());
            }
            
            // Set the list of product DTOs in the current sell DTO
            getSellDTOobj.setProductList(getSellDTOAddOnList);
            // Add the sell DTO to the list
            getSellDTOList.add(getSellDTOobj);
        }
        
        // Return the list of sell DTOs
        return getSellDTOList;
    }

    @Override
    public GetSellDTO getOneSell(Long id_sell) {
        
        // Retrieve the sell by ID
        Sell sell =  sellRep.findById(id_sell).orElse(null); 
        
        // Check if the sell exists
        if (sell == null) {
            throw new RuntimeException("Sell not found: " + id_sell);
        }
        
        // Create a DTO to hold the sell details
        GetSellDTO getSellDTO = new GetSellDTO();
        
        // Set basic sell properties
        getSellDTO.setId_sell(sell.getId_sell());
        getSellDTO.setSell_date(sell.getSell_date());
        getSellDTO.setTotal(sell.getTotal());
        
         // Check if the customer is available before accessing their data
        if (sell.getOneCustomer() != null) {
            getSellDTO.setName_customer(sell.getOneCustomer().getName_customer());
        } else {
            getSellDTO.setName_customer("Unknown Customer");
        }
        
        // Initialize the list of sold products 
        List<GetSellDTOAddOn> getSellDTOAddOnList = new ArrayList<>();
        
        // Iterate over each product in the sell list
        for(Product product : sell.getProductList()){
            GetSellDTOAddOn getSellDTOAddON = new GetSellDTOAddOn();
            
            // Set product properties in the DTO
            getSellDTOAddON.setId_product(product.getId_product());
            getSellDTOAddON.setName_product(product.getName_product());
            getSellDTOAddON.setBrand_product(product.getBrand_product());
            getSellDTOAddON.setCost_product(product.getCost_product());
            
            // Add the product to the DTO list
            getSellDTOAddOnList.add(getSellDTOAddON);
        }
        
        // Set the list of products in the sell DTO
        getSellDTO.setProductList(getSellDTOAddOnList);
        
        // Return the DTO with all the sell information
        return getSellDTO;
    }

    @Override
    public void deleteSell(Long id_sell) {
        sellRep.deleteById(id_sell);
    }

    // The editing function is design so it can only be change the date or customer name, not the product list or the total amount sold. 
    @Override
    public DisplaySuccessfulEditingDTO editSell(Sell sell) {
        
        if ( sell.getId_sell() != null){
            
            // Get the exisitng value of the sell that is inside the databse
             Sell existingSell = sellRep.findById(sell.getId_sell())
            .orElseThrow(() -> new RuntimeException("Sell not found: " + sell.getId_sell()));


            // Update sell date field if provided
            if (sell.getSell_date() != null){
                existingSell.setSell_date(sell.getSell_date());
            }
            // Update the customer from the sell
           existingSell.setOneCustomer(sell.getOneCustomer());
            
           // Save the updated sell record
           sellRep.save(existingSell);

           // Initialize the DTO to show specific data
           // Beggining of the DTO  ///////////////////////////////////////////////////////////////////////
           DisplaySuccessfulEditingDTO dispEditDTO = new DisplaySuccessfulEditingDTO();
           List<DisplaySuccessfulEditingDTOAddOn> dispEditDTOAddOnList = new ArrayList<>();
           
           dispEditDTO.setId_sell(sell.getId_sell());
           dispEditDTO.setSell_date(sell.getSell_date());
           dispEditDTO.setTotal(existingSell.getTotal());
           
          for(Product product : existingSell.getProductList()){
              DisplaySuccessfulEditingDTOAddOn addOn = new DisplaySuccessfulEditingDTOAddOn();
              addOn.setId_product(product.getId_product());
              addOn.setName_product(product.getName_product());
              addOn.setBrand_product(product.getBrand_product());
              
              dispEditDTOAddOnList.add(addOn);
          }
          
           dispEditDTO.setSucEditDTOAddOn(dispEditDTOAddOnList);
           // End of the DTO /////////////////////////////////////////////////////////////////////////////
           
           // Returning the DTO
           return dispEditDTO;
           
        } else {throw new RuntimeException("Sell ID cannot be null.");}

    }

    @Override
    public TotalRevenueAndSellsOnADayDTO getTotalRevenueAndSellsOnADay(LocalDate theDay) {

        // Create  a list of the sells from an specific day
        List<Sell> sellsOnDay = sellRep.findBySellDate(theDay);
        
        // Created a DTO object where is going to store the data and be sended to the user
        TotalRevenueAndSellsOnADayDTO result = new TotalRevenueAndSellsOnADayDTO();
        
        // Setting the Date and the amount of sell that were made in that Date
        result.setDateInfo(theDay);
        result.setTotalSellsADay(sellsOnDay.size());
        
        // Create a paramater were is going be adding the total sum of the sells
        double totalSum = 0;
        
        // Made a for loop were is going to be adding each sell
          for(Sell sell : sellsOnDay){
            if (sell.getTotal() != null) {
                totalSum += sell.getTotal();
                }
            }
        
          // Set the total value of the sum to the DTO object
        result.setTotalRevenueADay(totalSum);
        
        return result;
    }

    @Override
    public HighestSellDTO getHighestSell() {
        // We get the list of all sells
        List <Sell>sellList = sellRep.findAll();
        
        // In case the sell is empty
        if (sellList.isEmpty()) {
        return null; 
        }
        
        // Set highest sell with any value, we are going to filter the highest one after
        Sell highestSell = sellList.get(0);
        
       // Finding the highest sell
        for (Sell sell : sellList){
            // Making a comparison between the each sell with the current highest one, if is greater, the new highest sell value is updated
            if (sell.getTotal()>highestSell.getTotal()){
                highestSell = sell;
            }
        }
        
        // Create the DTO object to store the data and then send the response
        HighestSellDTO highestSellDTO = new HighestSellDTO();
        
        // Setting the highest sell_id, the sell amount, and the quantity of products sold
        highestSellDTO.setId_sell(highestSell.getId_sell());
        highestSellDTO.setTotal_sell(highestSell.getTotal());
        highestSellDTO.setQuantity_products(highestSell.getProductList().size());
        
         // Safely retrieve the customer associated with the highest sell
        Customer customer = highestSell.getOneCustomer();
        if (customer !=null){
             highestSellDTO.setName_customer(customer.getName_customer());
            highestSellDTO.setLastName_customer(customer.getLastName_customer());
        }   
        // In case the Customer is not known
        else {
        highestSellDTO.setName_customer("Unknown");
        highestSellDTO.setLastName_customer("Unknown");
        }
        
        return highestSellDTO;
        
    }
    
    
}
