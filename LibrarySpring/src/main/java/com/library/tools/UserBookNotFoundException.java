package com.library.tools;

public class UserBookNotFoundException extends Throwable {
    public UserBookNotFoundException(String message) {
        super(message);
    }
}
