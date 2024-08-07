

package com.project.Baazar.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// Customer class will be related OneToOne to the Sell class, therefore, it will be one customer per sale. The Sell class will handle the connection
@Getter @Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_customer;
    private String dni_customer;
    private String name_customer;
    private String lastName_customer;   
       
    
    public Customer() {
    }

    public Customer(Long id_customer, String dni_customer, String name_customer, String lastName_customer) {
        this.id_customer = id_customer;
        this.dni_customer = dni_customer;
        this.name_customer = name_customer;
        this.lastName_customer = lastName_customer;
    }

  
    
    
    
}
