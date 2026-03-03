package dinh.hien.file_service.adapter.mapper;


import dinh.hien.file_service.adapter.dto.request.FileUploadRequestDTO;
import dinh.hien.file_service.adapter.dto.response.SingleFileResponse;
import dinh.hien.file_service.adapter.dto.response.UploadResponseDTO;
import dinh.hien.file_service.application.usecase.upload.ASingleFileRequestInfo;
import dinh.hien.file_service.application.usecase.upload.ASingleFileResponse;
import dinh.hien.file_service.application.usecase.upload.UploadCommand;
import dinh.hien.file_service.application.usecase.upload.UploadResult;

import java.util.List;


public class FileMapper {
    private FileMapper() {
        // prevent instantiation
    }

    public static UploadCommand toUploadCommand(FileUploadRequestDTO dto) {
        List<ASingleFileRequestInfo> list = dto.getFiles()
                .stream().map(
                        d ->
                                ASingleFileRequestInfo.builder()
                                        .fileName(d.getFileName())
                                        .contentType(d.getContentType())
                                        .size(d.getSize())
                                        .build()
                ).toList();
        return UploadCommand.builder()
                .files(list)
                .build();
    }


    public static UploadResponseDTO toUploadResponseDTO(UploadResult result) {
        List<SingleFileResponse> list = result.getSingleFileResponseList()
                .stream().map(
                        d ->
                                SingleFileResponse.builder()
                                        .storageKey(d.getStorageKey())
                                        .presignUrl(d.getPresignUrl())
                                        .createdAt(d.getCreatedAt())
                                        .contentType(d.getContentType())
                                        .size(d.getSize())
                                        .fileName(d.getFileName())
                                        .isPrivateFile(d.getIsPrivateFile())
                                        .id(d.getId())
                                        .build()
                ).toList();
        return UploadResponseDTO.builder()
                .singleFileResponseList(list)
                .build();
    }
}
