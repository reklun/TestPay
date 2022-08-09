package exception;

/**
 * For denoting an exception during transformation
 */
public class TransformException extends Exception {
    public TransformException(String errorMessage) {
        super(errorMessage);
    }
}
