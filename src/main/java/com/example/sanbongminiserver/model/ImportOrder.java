package com.example.sanbongminiserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "import_orders")
public class ImportOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank
    @NotEmpty
    @NotNull
    private String code;

    private String note;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "importOrder",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"importOrder",})
    private List<ImportOrderProduct> importOrderProducts;
}
