package dinh.hien.identity_service.infra.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class JpaUser {
    @Id
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private JpaRole role;
}
