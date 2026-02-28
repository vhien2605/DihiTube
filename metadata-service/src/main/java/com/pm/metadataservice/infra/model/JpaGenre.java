package com.pm.metadataservice.infra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genres")
@Getter
@Setter
public class JpaGenre {
    @Id
    private String id;

    @NotBlank(message = "Genre's name is not blank")
    @Column(unique = true)
    private String name;

}
