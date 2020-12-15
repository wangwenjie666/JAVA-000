package io.kimmking.rpcfx.exception;

/**
 * exception.
 *
 * @author Owen
 */
public class RpcfxException extends RuntimeException{

    public RpcfxException() {
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }
}
