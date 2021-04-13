package ca.qc.colval.finalprojectweb.BLL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
