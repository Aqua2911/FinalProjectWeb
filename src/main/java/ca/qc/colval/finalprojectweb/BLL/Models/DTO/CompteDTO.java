package ca.qc.colval.finalprojectweb.BLL.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteDTO {
    private Long compteId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
