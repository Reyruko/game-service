package com.gamevault.gameservice.exception;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException(String name) {
        super("Game '" + name + "' already exists.");
    }
}
