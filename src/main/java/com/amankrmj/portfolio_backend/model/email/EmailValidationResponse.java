package com.amankrmj.portfolio_backend.model.email;

import java.io.Serializable;

public class EmailValidationResponse implements Serializable {
    private String email;
    private String autocorrect;
    private String deliverability;
    private String quality_score;
    private BoolText is_valid_format;
    private BoolText is_free_email;
    private BoolText is_disposable_email;
    private BoolText is_role_email;
    private BoolText is_catchall_email;
    private BoolText is_mx_found;
    private BoolText is_smtp_valid;

    public EmailValidationResponse() {
    }

    public EmailValidationResponse(String email, String autocorrect, String deliverability, String quality_score,
                                   BoolText is_valid_format, BoolText is_free_email, BoolText is_disposable_email,
                                   BoolText is_role_email, BoolText is_catchall_email, BoolText is_mx_found, BoolText is_smtp_valid) {
        this.email = email;
        this.autocorrect = autocorrect;
        this.deliverability = deliverability;
        this.quality_score = quality_score;
        this.is_valid_format = is_valid_format;
        this.is_free_email = is_free_email;
        this.is_disposable_email = is_disposable_email;
        this.is_role_email = is_role_email;
        this.is_catchall_email = is_catchall_email;
        this.is_mx_found = is_mx_found;
        this.is_smtp_valid = is_smtp_valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAutocorrect() {
        return autocorrect;
    }

    public void setAutocorrect(String autocorrect) {
        this.autocorrect = autocorrect;
    }

    public String getDeliverability() {
        return deliverability;
    }

    public void setDeliverability(String deliverability) {
        this.deliverability = deliverability;
    }

    public String getQuality_score() {
        return quality_score;
    }

    public void setQuality_score(String quality_score) {
        this.quality_score = quality_score;
    }

    public BoolText getIs_valid_format() {
        return is_valid_format;
    }

    public void setIs_valid_format(BoolText is_valid_format) {
        this.is_valid_format = is_valid_format;
    }

    public BoolText getIs_free_email() {
        return is_free_email;
    }

    public void setIs_free_email(BoolText is_free_email) {
        this.is_free_email = is_free_email;
    }

    public BoolText getIs_disposable_email() {
        return is_disposable_email;
    }

    public void setIs_disposable_email(BoolText is_disposable_email) {
        this.is_disposable_email = is_disposable_email;
    }

    public BoolText getIs_role_email() {
        return is_role_email;
    }

    public void setIs_role_email(BoolText is_role_email) {
        this.is_role_email = is_role_email;
    }

    public BoolText getIs_catchall_email() {
        return is_catchall_email;
    }

    public void setIs_catchall_email(BoolText is_catchall_email) {
        this.is_catchall_email = is_catchall_email;
    }

    public BoolText getIs_mx_found() {
        return is_mx_found;
    }

    public void setIs_mx_found(BoolText is_mx_found) {
        this.is_mx_found = is_mx_found;
    }

    public BoolText getIs_smtp_valid() {
        return is_smtp_valid;
    }

    public void setIs_smtp_valid(BoolText is_smtp_valid) {
        this.is_smtp_valid = is_smtp_valid;
    }
}
