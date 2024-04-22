package com.example.sanbongminiserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"importOrderProducts"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Integer stock;

    @NotNull
    private Integer price;

    @Column(length = 500)
    private String description;

    private String image;

    @Column(name="deleted", columnDefinition="BOOLEAN DEFAULT false")
    private Boolean deleted = false;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"importOrder", "product"})
    private List<ImportOrderProduct> importOrderProducts;

}
