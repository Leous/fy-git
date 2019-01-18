package com.fy.fycontroller.common;

import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.exceptions.ApiException;
import com.fy.fycommon.exceptions.AuthorizedException;
import com.fy.fyentity.results.ResponseEntry;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @description: 异常捕获处理类
 * @author: cnc
 * @date: 2019-01-10 21:39:12
 */
@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ SQLException.class })
    @ResponseBody
    public ResponseEntry<Object> databaseError(SQLException exception) {
        exception.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), RespCodeEnum.SERVER_RUNTIME_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 运行时异常
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntry<Object> runtimeExceptionHandler(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), RespCodeEnum.SERVER_RUNTIME_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntry<Object> nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_NULL_POINT_EXCEPTION.code(), RespCodeEnum.SERVER_NULL_POINT_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 类型转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResponseEntry<Object> classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_CLASS_CAST_EXCEPTION.code(), RespCodeEnum.SERVER_CLASS_CAST_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * IO异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseEntry<Object> iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_IO_EXCEPTION.code(), RespCodeEnum.SERVER_IO_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 未知方法异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public ResponseEntry<Object> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_NO_SUCH_METHOD_EXCEPTION.code(), RespCodeEnum.SERVER_NO_SUCH_METHOD_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 数组越界异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResponseEntry<Object> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.SERVER_INDEX_OUT_BOUNDS_EXCEPTION.code(), RespCodeEnum.SERVER_INDEX_OUT_BOUNDS_EXCEPTION.getReasonCNPhrase(), "", null);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntry<Object> requestNotReadable(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.HTTP_BAD_REQUEST.code(), RespCodeEnum.HTTP_BAD_REQUEST.getReasonCNPhrase(), "", null);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResponseEntry<Object> requestTypeMismatch(TypeMismatchException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.HTTP_BAD_REQUEST.code(), RespCodeEnum.HTTP_BAD_REQUEST.getReasonCNPhrase(), "", null);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntry<Object> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        return new ResponseEntry(RespCodeEnum.HTTP_BAD_REQUEST.code(), RespCodeEnum.HTTP_BAD_REQUEST.getReasonCNPhrase(), "", null);
    }

    /**
     * 405错误
     *
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResponseEntry<Object> request405() {
        return new ResponseEntry(RespCodeEnum.HTTP_METHOD_NOT_ALLOWED.code(), RespCodeEnum.HTTP_METHOD_NOT_ALLOWED.getReasonCNPhrase(), "", null);
    }

    /**
     * 406错误
     *
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResponseEntry<Object> request406() {
        return new ResponseEntry(RespCodeEnum.HTTP_NOT_ACCEPTABLE.code(), RespCodeEnum.HTTP_NOT_ACCEPTABLE.getReasonCNPhrase(), "", null);
    }

    /**
     * 500错误
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public ResponseEntry<Object> server500(RuntimeException runtimeException) {
        return new ResponseEntry(RespCodeEnum.HTTP_INTERNAL_SERVER_ERROR.code(), RespCodeEnum.HTTP_INTERNAL_SERVER_ERROR.getReasonCNPhrase(), "", null);
    }

    @ExceptionHandler(value = {ApiException.class})
    @ResponseBody
    public ResponseEntry<Object> apiException(ApiException apiException) {
        if (apiException.getErrCode() == null || apiException.getErrCode().length() == 0) {
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), apiException.getMessage(), "", null);
        }
        return new ResponseEntry(apiException.getErrCode(), apiException.getMessage(), "", null);
    }

    @ExceptionHandler(value = {AuthorizedException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntry<Object> unAuthorized(AuthorizedException excep) {
        return new ResponseEntry(RespCodeEnum.HTTP_NOT_AUTHORIZATION.code(), "身份认证失效，请重新认证", "", null);
    }
}

