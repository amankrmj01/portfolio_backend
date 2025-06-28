package com.amankrmj.portfolio_backend.model.phone;

import java.io.Serializable;

public class PhoneFormat implements Serializable {
    private String international;
    private String local;

    public PhoneFormat() {}

    public PhoneFormat(String international, String local) {
        this.international = international;
        this.local = local;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
