
package com.project.Baazar.Project.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// DTO for displaying the products of a sell
@Getter @Setter
public class ProductBySellDTO {

    private Long id_sell;
    private List<ProductBySellDTOAddOn> productInfoList;
    
    public ProductBySellDTO() {
    }

    public ProductBySellDTO(Long id_sell, List<ProductBySellDTOAddOn> productInfoList) {
        this.id_sell = id_sell;
        this.productInfoList = productInfoList;
    }

    
    
}
