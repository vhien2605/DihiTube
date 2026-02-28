package dinh.hien.identity_service.infra.persistence;


import dinh.hien.identity_service.domain.user.*;
import dinh.hien.identity_service.infra.mapper.UserMapper;
import dinh.hien.identity_service.infra.model.JpaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        var wrapper=userJpaRepository.findByUsername(username);
        if(wrapper.isPresent()){
            JpaUser jpaUser=wrapper.get();
            User user= UserMapper.toDomain(jpaUser);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(UserId id) {
        var wrapper=userJpaRepository.findById(id.getValue().toString());
        if(wrapper.isPresent()){
            JpaUser jpaUser=wrapper.get();
            User user= UserMapper.toDomain(jpaUser);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public boolean existedById(UserId id) {
        return findById(id).isPresent();
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(UserMapper.toJpa(user));
    }

    @Override
    public void deleteById(UserId id) {
        userJpaRepository.deleteById(id.getValue().toString());
    }
}
