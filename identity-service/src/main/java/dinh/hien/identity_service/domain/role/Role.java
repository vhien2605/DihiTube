package dinh.hien.identity_service.domain.role;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private RoleId id;
    private String name;
}
