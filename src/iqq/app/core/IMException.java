package iqq.app.core;

/**
 * IM异常，所有的接口都需要声明抛出IM异常
 *
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 10:04 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMException extends Exception {
    private static final long serialVersionUID = 1L;
    private IMErrorCode errorCode;

    public IMException(IMErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public IMException(IMErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public IMException(IMErrorCode errorCode, Throwable e) {
        super(errorCode.toString(), e);
        this.errorCode = errorCode;
    }

    public IMErrorCode getError() {
        return errorCode;
    }
}
