package com.gamevault.gameservice.controller;

import com.gamevault.gameservice.dto.PlatformCreateRequest;
import com.gamevault.gameservice.dto.PlatformDTO;
import com.gamevault.gameservice.service.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping
    public PlatformDTO createPlatform(
            @Valid @RequestBody PlatformCreateRequest request) {

        return platformService.createPlatform(request);
    }
}
