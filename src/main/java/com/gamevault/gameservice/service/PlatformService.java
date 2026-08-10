package com.gamevault.gameservice.service;

import com.gamevault.gameservice.dto.PlatformCreateRequest;
import com.gamevault.gameservice.dto.PlatformDTO;
import com.gamevault.gameservice.entity.Platform;
import com.gamevault.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformDTO createPlatform(PlatformCreateRequest request) {

        Platform platform = new Platform();
        platform.setName(request.getName());

        Platform savedPlatform = platformRepository.save(platform);

        return new PlatformDTO(
                savedPlatform.getId(),
                savedPlatform.getName()
        );
    }

}
