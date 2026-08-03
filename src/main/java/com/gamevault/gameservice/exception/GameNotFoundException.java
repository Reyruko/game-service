package com.gamevault.gameservice.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Game not found.");
    }
}