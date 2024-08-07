
package com.project.Baazar.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// Product class will connect to the Sell class in a OneToMany relationship, where the link will be handled by the Sell class.
@Getter @Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_product;
    private String name_product;
    private String brand_product;
    private Double cost_product;
    private Double quantity_product;

    // With mappedBy its declare that the owner comes from the class Sell
    // "productList" should match with the declarated variable on Sell class.
    @ManyToMany(mappedBy ="productList")
    private List<Sell> sellList;
    
    public Product() {
    }  

    public Product(Long id_product, String name_product, String brand, Double cost, Double available_quantity, List<Sell> sellList) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.brand_product = brand;
        this.cost_product = cost;
        this.quantity_product = available_quantity;
        this.sellList = sellList;
    }
    
    
}
