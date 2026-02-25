package dinh.hien.profile_service.infra.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class JpaSubscription {
    @Id
    private String id;

    @Column(name = "type", unique = true)
    private String type;
}
