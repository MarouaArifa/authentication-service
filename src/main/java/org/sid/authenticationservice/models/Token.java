package org.sid.authenticationservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Token {
    @JsonProperty("access_token")
    private String accessToken  ;
    @JsonProperty("expires_in")
    private Integer expiresIn ;
    @JsonProperty("token_type")
    private String tokenType ;
    @JsonProperty("scope")
    private String scope ;
    @JsonProperty("refresh_token")
    private String refreshToken ;

    public Token() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return getAccessToken().equals(token.getAccessToken()) &&
                getExpiresIn().equals(token.getExpiresIn()) &&
                getTokenType().equals(token.getTokenType()) &&
                getScope().equals(token.getScope()) &&
                getRefreshToken().equals(token.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getExpiresIn(), getTokenType(), getScope(), getRefreshToken());
    }
}
