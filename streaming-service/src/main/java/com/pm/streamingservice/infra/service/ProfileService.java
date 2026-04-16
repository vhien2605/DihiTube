package com.pm.streamingservice.infra.service;

import com.pm.streamingservice.domain.IProfileService;
import com.pm.streamingservice.domain.IStreamingService;
import com.pm.streamingservice.infra.openfeign.ProfileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final ProfileClient profileClient;

    @Override
    public boolean isMembership() {
        return profileClient.isMembership().getData();
    }
}
