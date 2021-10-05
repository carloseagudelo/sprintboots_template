package co.com.common.encrypt;

public class EncryptionException extends Exception{

	private static final long serialVersionUID = -6776357702249774521L;

	public EncryptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		    super(message, cause, enableSuppression, writableStackTrace);
		  }

		  public EncryptionException(String message, Throwable cause) {
		    super(message, cause);
		  }

		  public EncryptionException(String message) {
		    super(message);
		  }

		  public EncryptionException(Throwable cause) {
		    super(cause);
		  }
}
