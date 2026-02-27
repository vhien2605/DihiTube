package dinh.hien.identity_service.infra.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class JpaRole {
    @Id
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "name")
    private String name;
}
