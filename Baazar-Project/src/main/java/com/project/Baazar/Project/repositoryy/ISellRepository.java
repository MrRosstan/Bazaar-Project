
package com.project.Baazar.Project.repositoryy;

import com.project.Baazar.Project.model.Sell;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellRepository extends JpaRepository<Sell, Long> {
    
    // From Query: "s" is an alias to fhe Sell Entity
    // Condition : On Sell we selected only the dates on sell_date
    @Query("SELECT s FROM Sell s WHERE s.sell_date = :sellDate")
    List<Sell> findBySellDate(@Param("sellDate") LocalDate sellDate);
    
   // Custom query to find a sell by customer ID
    @Query("SELECT s FROM Sell s WHERE s.oneCustomer.id_customer = :id_customer")
    Sell findSellByCustomerId(@Param("id_customer") Long id_customer);
}
