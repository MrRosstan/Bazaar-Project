
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.DisplayCustomerSellProductDTO;
import com.project.Baazar.Project.dto.DisplayCustomerSellProductDTOAddON;
import com.project.Baazar.Project.dto.DisplayCustomerSellProductDTOAddOnPt2;
import com.project.Baazar.Project.model.Customer;
import com.project.Baazar.Project.model.Product;
import com.project.Baazar.Project.model.Sell;
import com.project.Baazar.Project.repositoryy.ICustomerRepository;
import com.project.Baazar.Project.repositoryy.ISellRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    
    @Autowired
    private ICustomerRepository cusRep;
    @Autowired
    private ISellRepository sellRep;
    
    @Override
    public void saveProduct(Customer cus) {
        cusRep.save(cus);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return cusRep.findAll();
    }

    @Override
    public Customer getOneCustomer(Long id_customer) {
        return cusRep.findById(id_customer).orElse(null); 
    }

    @Override
    public void deleteCustomer(Long id_customer) {
        cusRep.deleteById(id_customer);
    }

    @Override
    public void editCustomer(Customer cus) {

                this.saveProduct(cus);

    }

    @Override
    public DisplayCustomerSellProductDTO getOneCustomerAllData(Long id_customer) {
        
        // Create the DTO object
        DisplayCustomerSellProductDTO disCusDTO = new DisplayCustomerSellProductDTO();
        
       // Fetch the customer from the repository
        Customer customer = cusRep.findById(id_customer).orElse(null);
        
       // Set customer properties in the DTO
        disCusDTO.setId_customer(customer.getId_customer());
        disCusDTO.setDni_customer(customer.getDni_customer());
        disCusDTO.setName_customer(customer.getName_customer());
        disCusDTO.setLastName_customer(customer.getLastName_customer());
        
        // Retrieve the single sale associated with this customer
          Sell sell = sellRep.findSellByCustomerId(id_customer);
          
       
    
        // Setting sell properties
        if (sell != null){
            DisplayCustomerSellProductDTOAddOnPt2 sellDTO = new DisplayCustomerSellProductDTOAddOnPt2();
            sellDTO.setId_sell(sell.getId_sell());
            sellDTO.setSell_date(sell.getSell_date());
            sellDTO.setTotal(sell.getTotal());
            // Adding the sell data into the general DTO
            disCusDTO.setSellsObj(sellDTO);
        }
        
        
        // Handle products
         if (sell != null && sell.getProductList() != null) {
             // Initialize lists for products
            List<DisplayCustomerSellProductDTOAddON> productDTOs = new ArrayList<>();
            // Create and populate the list of products
            for( Product product : sell.getProductList()){
                DisplayCustomerSellProductDTOAddON productDTO  = new DisplayCustomerSellProductDTOAddON();
                productDTO .setId_product(product.getId_product());
                productDTO .setName_product(product.getName_product());
                productDTO .setBrand_product(product.getBrand_product());
                productDTOs.add(productDTO );
                }
                // Adding the products data into the general DTO
                disCusDTO.setListProducts(productDTOs);
         }

       return disCusDTO;
    }
 
}
