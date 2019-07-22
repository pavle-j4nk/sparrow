package com.sparrow.dto;

import java.util.Date;

public class UserTokenState {
    private String accessToken;

    private Date expDate;

    public UserTokenState(String accessToken, Date expDate) {
        this.accessToken = accessToken;
        this.expDate = expDate;
    }

    public UserTokenState() {
        this.accessToken = null;
    }

    public UserTokenState(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
