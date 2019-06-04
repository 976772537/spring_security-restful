package com.imooc.web.exception;

public class UserNotExistException extends RuntimeException {
    private String id;

    public UserNotExistException(String id) {
        super ("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
