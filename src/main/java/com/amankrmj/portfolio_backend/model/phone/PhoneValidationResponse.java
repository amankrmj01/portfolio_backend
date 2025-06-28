package com.amankrmj.portfolio_backend.model.phone;

import java.io.Serializable;

public class PhoneValidationResponse implements Serializable {
    private String phone;
    private boolean valid;
    private PhoneFormat format;
    private CountryInfo country;
    private String location;
    private String type;
    private String carrier;

    public PhoneValidationResponse() {}

    public PhoneValidationResponse(String phone, boolean valid, PhoneFormat format, CountryInfo country, String location, String type, String carrier) {
        this.phone = phone;
        this.valid = valid;
        this.format = format;
        this.country = country;
        this.location = location;
        this.type = type;
        this.carrier = carrier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public PhoneFormat getFormat() {
        return format;
    }

    public void setFormat(PhoneFormat format) {
        this.format = format;
    }

    public CountryInfo getCountry() {
        return country;
    }

    public void setCountry(CountryInfo country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
