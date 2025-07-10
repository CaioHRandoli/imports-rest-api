package com.api.importacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "MARITIME_IMPORTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaritimeModel extends RepresentationModel<MaritimeModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMaritimeProcess;
    private String originCountry;
    private String destinationCountry;
    private String cargoDescription;
    private BigDecimal cargoValue;
    private Integer boxQuantity;
}
