package ca.qc.colval.finalprojectweb.BLL.Models;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_date")
    private Date serviceDate;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "compte_id")
    private Long compteId;

    @Column(name = "contact_id")
    private Long contactId;
}