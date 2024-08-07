
package com.project.Baazar.Project.controller;

import com.project.Baazar.Project.dto.DisplayCustomerSellProductDTO;
import com.project.Baazar.Project.model.Customer;
import com.project.Baazar.Project.service.ICustomerService;
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

public class CustomerController {
    @Autowired
    private ICustomerService cusServ;
    
    // Save customer
    @PostMapping("/customer/create")
    public String creatCustomer(@RequestBody Customer cus){
        cusServ.saveProduct(cus);
        return "The customer  has being created propertly";
    }
    
    // Get all customers
    @GetMapping("/customer/find/all")
    public List<Customer> getAllCustomer(){
        return cusServ.getAllCustomer();
    }
    
    // Find an specific customer
    @GetMapping("/customer/find/{id_customer}")
    public Customer getOneCustomer(@PathVariable Long id_customer){
        return cusServ.getOneCustomer(id_customer);
    }
    
    // Delete a customer
    @DeleteMapping("/customer/delete/{id_customer}")
    public String deleteCustomer(@PathVariable Long id_customer){
        cusServ.deleteCustomer(id_customer);
        return "The customer has being deleted";
    }
    
    // Edit a customer
    @PutMapping("/customer/edit")
    public Customer  editCustomer(@RequestBody Customer cus){
        cusServ.editCustomer(cus);
        return this.getOneCustomer(cus.getId_customer());
    }
    
    // Get all the data from one customer
    @GetMapping("/customer/allData/{id_customer}")
    public DisplayCustomerSellProductDTO getAllCustomerData(@PathVariable Long id_customer){
        return cusServ.getOneCustomerAllData(id_customer);
    }
}
