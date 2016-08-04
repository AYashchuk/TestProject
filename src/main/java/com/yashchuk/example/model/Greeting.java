package com.yashchuk.example.model;

import java.math.BigInteger;

/**
 * @author Andrii Iashchuk
 */
public class Greeting {
    private BigInteger id;
    private String text;

    public Greeting() {
    }

    public Greeting(BigInteger id, String text) {
        this.id = id;
        this.text = text;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
