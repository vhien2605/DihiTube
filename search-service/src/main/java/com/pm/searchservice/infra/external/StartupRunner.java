package com.pm.searchservice.infra.external;

import com.pm.searchservice.application.createIndex.CreateIndexUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final CreateIndexUseCase useCase;

    @Override
    public void run(ApplicationArguments args) {
        useCase.execute();
    }
}
