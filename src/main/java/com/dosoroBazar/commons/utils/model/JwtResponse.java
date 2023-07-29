package com.dosoroBazar.commons.utils.model;

import lombok.Builder;

@Builder
public class JwtResponse {

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JwtResponse(String jwtToken, String userName) {
        this.jwtToken = jwtToken;
        this.userName = userName;
    }

    private String jwtToken;
    private String userName;

}
