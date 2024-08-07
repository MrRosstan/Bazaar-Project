
package com.project.Baazar.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// Sell will manage the OneToMany relationship between Sell and Product, as well as the OneToOne relationship between Sell and Customer.
@Getter @Setter
@Entity
public class Sell {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_sell;
    private LocalDate sell_date;
    private Double total;
    @ManyToMany
    // By using JoinTable it indicate the union table between a relation ManyToMany
    // name: defines the name of union table
    // JoinColumn: indicates the "Sell" column class that is going to be linked with the union table, in this case id_sell
    // inverseJoinColumns: indicates the colum that is going link in the union table, this column comes from the non owner of the relation, in this case Product 
    @JoinTable(name ="JoinTable_sell_product", joinColumns  = @JoinColumn(name ="id_sell"), inverseJoinColumns  =@JoinColumn(name="id_product") )
    private List<Product> productList;

    @OneToOne
    @JoinColumn(name = "id_customer")   // Foreign key column
    private Customer oneCustomer;

    public Sell() {
    }

    public Sell(Long id_sell, LocalDate sell_date, Double total, List<Product> productList, Customer oneCustomer) {
        this.id_sell = id_sell;
        this.sell_date = sell_date;
        this.total = total;
        this.productList = productList;
        this.oneCustomer = oneCustomer;
    }

    
}
