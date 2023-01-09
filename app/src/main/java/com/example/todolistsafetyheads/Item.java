package com.example.todolistsafetyheads;

import java.io.Serializable;

public class Item implements Serializable {

    private String message;
    private int image;

    public Item(String message, int image) {
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
