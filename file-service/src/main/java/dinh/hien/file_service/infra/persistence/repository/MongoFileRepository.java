package dinh.hien.file_service.infra.persistence.repository;


import dinh.hien.file_service.infra.model.FileMetadataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoFileRepository extends MongoRepository<FileMetadataDocument, String> {

}
