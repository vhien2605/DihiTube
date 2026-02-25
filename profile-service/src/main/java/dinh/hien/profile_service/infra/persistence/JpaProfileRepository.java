package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.infra.model.JpaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProfileRepository extends JpaRepository<JpaProfile,String> {

}
