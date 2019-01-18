package com.fy.fyentity.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import org.joda.time.DateTime;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-07 1:00
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class BaseRequest {
    @NotBlank
    @JsonProperty("signType")
    private String signType;
    @NotBlank
    @JsonProperty("sign")
    private String sign;
    @NotBlank
    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("language")
    private String language;

    public BaseRequest() {
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime date) {
        this.timestamp = date.getMillis();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
