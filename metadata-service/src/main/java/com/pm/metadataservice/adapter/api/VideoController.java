package com.pm.metadataservice.adapter.api;

import com.pm.metadataservice.adapter.dto.request.CreateVideoRequest;
import com.pm.metadataservice.adapter.mapper.VideoMapper;
import com.pm.metadataservice.application.CreateVideoCommand;
import com.pm.metadataservice.application.VideoApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoApplicationService service;

    @PostMapping
    public String create(@RequestBody CreateVideoRequest request) {
        CreateVideoCommand cmd = VideoMapper.toCreateVideoCommand(request);
        return service.createVideo(cmd);
    }

    @PostMapping("/{id}/view")
    public void view(@PathVariable String id) {
        service.increaseView(UUID.fromString(id));
    }
}
