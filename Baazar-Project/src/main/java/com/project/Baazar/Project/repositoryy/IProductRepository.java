
package com.project.Baazar.Project.repositoryy;

import com.project.Baazar.Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
    
}
