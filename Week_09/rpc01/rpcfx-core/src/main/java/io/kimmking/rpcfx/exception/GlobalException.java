package io.kimmking.rpcfx.exception;

import io.kimmking.rpcfx.api.RpcfxResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * global exception handler.
 *
 * @author Owen
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public RpcfxResponse exceptionHandle(Exception ex){
        ex.printStackTrace();
        RpcfxResponse response = new RpcfxResponse();
        response.setException(ex);
        response.setStatus(false);
        return response;
    }

    @ExceptionHandler(RpcfxException.class)
    public RpcfxResponse rpcfxExceptionHandle(RpcfxException ex){
        ex.printStackTrace();
        RpcfxResponse response = new RpcfxResponse();
        response.setException(ex);
        response.setStatus(false);
        return response;
    }
}
