package com.example.sanbongminiserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "import_order_product")
public class ImportOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private Integer importPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("importOrderProducts")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "import_order_id")
    @JsonIgnoreProperties("importOrderProducts")
    private ImportOrder importOrder;
}
