package com.autos.concesionaria.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "marca")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID de la marca
    private Long id;

    // El nombre de la marca
    private String nombre;

    // El pais de origen de la marca
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

}
