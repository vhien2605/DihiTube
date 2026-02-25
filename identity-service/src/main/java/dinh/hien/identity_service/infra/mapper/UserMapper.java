package dinh.hien.identity_service.infra.mapper;

import dinh.hien.identity_service.domain.role.Role;
import dinh.hien.identity_service.domain.role.RoleId;
import dinh.hien.identity_service.domain.user.Email;
import dinh.hien.identity_service.domain.user.Password;
import dinh.hien.identity_service.domain.user.User;
import dinh.hien.identity_service.domain.user.UserId;
import dinh.hien.identity_service.infra.model.JpaUser;

public class UserMapper {

    private UserMapper() {}

    public static User toDomain(JpaUser jpaUser) {
        if (jpaUser == null) return null;

        return new User(
                UserId.of(jpaUser.getId()),
                jpaUser.getUsername(),
                Password.of(jpaUser.getPassword()),
                Email.of(jpaUser.getEmail()),
                new Role(
                        RoleId.of(jpaUser.getRole().getId()),
                        jpaUser.getRole().getName()
                )
        );
    }


    public static JpaUser toJpa(User user) {
        if (user == null) return null;
        return JpaUser.builder()
                .id(user.getId().getValue().toString())
                .email(user.getEmail().getValue())
                .password(user.getPassword().getHashValue())
                .username(user.getUsername())
                .role(RoleMapper.toJpa(user.getRole()))
                .build();
    }
}
