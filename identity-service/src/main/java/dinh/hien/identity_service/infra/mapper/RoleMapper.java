package dinh.hien.identity_service.infra.mapper;

import dinh.hien.identity_service.domain.role.Role;
import dinh.hien.identity_service.domain.role.RoleId;
import dinh.hien.identity_service.infra.model.JpaRole;

public class RoleMapper {
    private RoleMapper() {}

    public static Role toDomain(JpaRole jpaRole) {
        if (jpaRole == null) return null;

        return new Role(
                RoleId.of(jpaRole.getId()),
                jpaRole.getName()
        );
    }


    public static JpaRole toJpa(Role role) {
        if (role == null) return null;

        return JpaRole.builder()
                .id(role.getId().getValue().toString())
                .name(role.getName())
                .build();
    }
}
