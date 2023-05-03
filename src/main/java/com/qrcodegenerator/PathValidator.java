package com.qrcodegenerator;


public class PathValidator {
    public boolean validate(String path) {
        return path == null || path.isEmpty() || path.isBlank();
    }

}
