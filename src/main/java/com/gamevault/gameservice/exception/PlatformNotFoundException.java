package com.gamevault.gameservice.exception;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException() {
        super("One or more platforms not found.");
    }
}
