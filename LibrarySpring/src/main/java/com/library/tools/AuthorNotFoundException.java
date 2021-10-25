package com.library.tools;

public class AuthorNotFoundException extends Throwable {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
