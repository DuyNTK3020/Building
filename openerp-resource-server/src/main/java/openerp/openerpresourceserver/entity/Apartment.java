package openerp.openerpresourceserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "building_apartment")
public class Apartment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "building_id", referencedColumnName = "id", nullable = false)
    private Building building;

    @Column(name = "area")
    private Double area;

    @Column(name = "price")
    private Double price;

    @Column(name = "rental_price")
    private Double rental_price;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "floor")
    private Integer floor;

    @CreatedDate
    @Column(name = "created_stamp")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_updated_stamp")
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "apartment")
    @JsonManagedReference
    private List<Customer> customers;

    @OneToMany(mappedBy = "apartment")
    @JsonManagedReference
    private List<ApartmentInvoice> apartmentInvoices;

    @OneToMany(mappedBy = "apartment")
    @JsonManagedReference
    private List<ApartmentServiceLink> apartmentServiceLinks;

    @OneToMany(mappedBy = "apartment")
    @JsonManagedReference
    private List<ResidentApartmentHistory> residentApartmentHistories;
}
