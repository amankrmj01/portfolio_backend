package com.amankrmj.portfolio_backend.model.email;

import java.io.Serializable;

public class BoolText implements Serializable {
    private boolean value;
    private String text;

    public BoolText() {}

    public BoolText(boolean value, String text) {
        this.value = value;
        this.text = text;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
