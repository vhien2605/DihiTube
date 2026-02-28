package dinh.hien.identity_service.infra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "tokens")
public class RefreshToken {
    @Id
    private String jti;
    
    @Column(name = "user_id", nullable = false)
    private String userId;
}
