package io.github.trident.common.exception;

/**
 * @projectName: trident
 * @package: io.github.trident.common.exception
 * @className: TridentException
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 13:31
 * @version: 1.0
 */
public class TridentException extends RuntimeException implements java.io.Serializable {
    protected String errCode;
    public TridentException(final Throwable e) {
        super(e);
    }

    public TridentException(final String message) {
        super(message);
    }

    public TridentException() {

    }
    public TridentException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
