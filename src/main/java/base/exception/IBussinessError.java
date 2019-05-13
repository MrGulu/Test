package base.exception;

public interface IBussinessError {

    public String getMessage();

    public String getCode();

    public String getMessage(String... details);

}
