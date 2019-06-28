package exception;

public interface ICustomError {
    String getCode();

    String getMessage();

    String getMessage(String... details);
}
