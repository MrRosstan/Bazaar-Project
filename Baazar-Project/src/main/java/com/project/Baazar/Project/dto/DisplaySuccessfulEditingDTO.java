
package com.project.Baazar.Project.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// DTO for displaying a succesful editing on a sell and its products
@Getter @Setter
public class DisplaySuccessfulEditingDTO {
    private Long id_sell;
    private LocalDate sell_date;
    private Double total;
    private List<DisplaySuccessfulEditingDTOAddOn> sucEditDTOAddOn;

    public DisplaySuccessfulEditingDTO() {
    }

    public DisplaySuccessfulEditingDTO(Long id_sell, LocalDate sell_date, Double total, List<DisplaySuccessfulEditingDTOAddOn> sucEditDTO) {
        this.id_sell = id_sell;
        this.sell_date = sell_date;
        this.total = total;
        this.sucEditDTOAddOn = sucEditDTO;
    }

}
