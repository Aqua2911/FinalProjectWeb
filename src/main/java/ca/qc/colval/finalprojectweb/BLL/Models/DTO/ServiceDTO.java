package ca.qc.colval.finalprojectweb.BLL.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    private Long serviceId;
    private String serviceType;
    private Date serviceDate;
    private Double cost;
    private Long compteId;
    private Long contactId;
}

