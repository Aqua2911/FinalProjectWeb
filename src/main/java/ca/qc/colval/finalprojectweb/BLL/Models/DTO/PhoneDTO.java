package ca.qc.colval.finalprojectweb.BLL.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private Long phoneId;
    private String phoneNumber;
    private int contactId;
}

