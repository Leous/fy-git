package com.fy.fyentity.requests;

import lombok.Data;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-20 14:16
 */
@Data
public class VerifyAccountRequest extends BaseRequest {

    private String account;
}
