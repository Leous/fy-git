package com.fy.fyentity.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import org.jboss.logging.Message;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * @description: 基础请求参数，必传，用于鉴定请求的有效性
 * @author: cnc
 * @date: 2019-01-07 1:00
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class BaseRequest {
    @NotNull
    @JsonProperty("signType")
    private String signType;
    @NotNull
    @JsonProperty("sign")
    private String sign;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("timestamp")
    private long timestamp;
    @NotNull
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
