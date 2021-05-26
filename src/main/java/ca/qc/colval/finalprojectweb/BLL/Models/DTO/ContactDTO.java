package ca.qc.colval.finalprojectweb.BLL.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long contactId;
    private String firstName;
    private String lastName;
    private Long compteId;
}
