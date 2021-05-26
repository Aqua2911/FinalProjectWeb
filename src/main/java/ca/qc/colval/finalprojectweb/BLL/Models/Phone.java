package ca.qc.colval.finalprojectweb.BLL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "telephones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telephone_id")
    private Long phoneId;

    @Column(name = "telephone_number")
    private String phoneNumber;

    @Column(name = "contact_id")
    private Long contactId;
}