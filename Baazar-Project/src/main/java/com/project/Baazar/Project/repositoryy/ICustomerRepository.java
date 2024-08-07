
package com.project.Baazar.Project.repositoryy;

import com.project.Baazar.Project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long>{
    
    
}
