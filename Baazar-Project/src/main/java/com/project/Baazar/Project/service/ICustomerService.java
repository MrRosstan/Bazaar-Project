
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.DisplayCustomerSellProductDTO;
import com.project.Baazar.Project.model.Customer;
import java.util.List;


public interface ICustomerService {
     // Create a customer
    public void saveProduct(Customer cus);
    
    // Bring all the Customers
    public List<Customer> getAllCustomer();
    
    // Bring one Customer
    public Customer getOneCustomer(Long id_customer);
    
    // Delete a Customer
    public void deleteCustomer(Long id_customer);
    
    // Edit a Customer
    public void editCustomer(Customer cus);
    
    // Bring All the data from one Customer
    public DisplayCustomerSellProductDTO getOneCustomerAllData(Long id_customer);
}
