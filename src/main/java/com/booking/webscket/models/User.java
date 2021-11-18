package com.booking.webscket.models;

/**
 * @author pholv
 * @created 2021-11-18 1:44 PM
 */
import java.security.Principal;

public class User implements Principal {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}