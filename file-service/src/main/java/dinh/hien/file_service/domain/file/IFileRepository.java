package dinh.hien.file_service.domain.file;


import java.util.List;

public interface IFileRepository {
    void save(List<FileMetaData> file);
}
