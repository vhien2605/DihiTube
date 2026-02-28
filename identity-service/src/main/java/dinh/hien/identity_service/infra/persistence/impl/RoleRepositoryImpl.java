package dinh.hien.identity_service.infra.persistence.impl;

import dinh.hien.identity_service.domain.role.IRoleRepository;
import dinh.hien.identity_service.domain.role.Role;
import dinh.hien.identity_service.infra.mapper.RoleMapper;
import dinh.hien.identity_service.infra.model.JpaRole;
import dinh.hien.identity_service.infra.persistence.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository {
    private final RoleJpaRepository repository;

    @Override
    public Optional<Role> findByName(String name) {
        var wrapper = repository.findByName(name.toUpperCase());
        if (wrapper.isPresent()) {
            JpaRole jpaRole = wrapper.get();
            Role role = RoleMapper.toDomain(jpaRole);
            return Optional.of(role);
        }
        return Optional.empty();
    }
}
