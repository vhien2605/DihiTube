package com.pm.streamingservice.infra.service;

import com.pm.streamingservice.domain.IStreamingService;
import com.pm.streamingservice.infra.openfeign.StreamingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingServiceImpl implements IStreamingService {
    private final StreamingClient streamingClient;

    @Override
    public String stream(UUID id) {
        return streamingClient.metadata(id).getData();
    }
}
