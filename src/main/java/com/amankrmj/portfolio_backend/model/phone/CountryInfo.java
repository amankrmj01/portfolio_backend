package com.amankrmj.portfolio_backend.model.phone;

import java.io.Serializable;

public class CountryInfo implements Serializable {
    private String code;
    private String name;
    private String prefix;

    public CountryInfo() {}

    public CountryInfo(String code, String name, String prefix) {
        this.code = code;
        this.name = name;
        this.prefix = prefix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
