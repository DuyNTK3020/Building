package openerp.openerpresourceserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "building_apartment_invoice")
public class ApartmentInvoice {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id", nullable = false)
    private Apartment apartment;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "payment_due_date", nullable = false)
    private Date paymentDueDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_stamp", nullable = false, updatable = false)
    private Date createdStamp;

    @Column(name = "last_updated_stamp", nullable = false)
    private Date lastUpdatedStamp;
}
