package Exception;

import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -1976554664892410672L;

    private String errorCode = null;

    private Map<String, String> errorMessages = new HashMap<>();

    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(ICustomError customError, String... details) {
        super(customError.getMessage(details));
        this.errorCode = customError.getCode();
    }

    public CustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(ICustomError customError) {
        super(customError.getMessage());
        this.errorCode = customError.getCode();
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getErrorMessages() {
        return this.errorMessages;
    }

    public static long getSerialversionuid() {
        return -5433056591268198786L;
    }

    public void setErrorMessages(Set<? extends ConstraintViolation<? extends Object>> violations) {
        for (ConstraintViolation violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            this.errorMessages.put(propertyPath, message);
        }
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String fieldName, String message) {
        this.errorMessages.put(fieldName, message);
    }

    public void addBindingResultTo(BindingResult result) {
        for (String key : this.errorMessages.keySet()) {
            result.rejectValue(key, "", (String) this.errorMessages.get(key));
        }
    }
}
