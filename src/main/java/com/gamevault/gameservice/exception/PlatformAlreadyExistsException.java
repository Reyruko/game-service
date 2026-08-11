package com.gamevault.gameservice.exception;

public class PlatformAlreadyExistsException extends RuntimeException {
    public PlatformAlreadyExistsException() {
        super("Platform already exists!");
    }
}
