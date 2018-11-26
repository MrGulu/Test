package custom.exception;

import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BusinessException extends RuntimeException
{
  private static final long serialVersionUID = -5433056591268198786L;
  private Map<String, String> errorMessages = new HashMap();

  public BusinessException()
  {
  }

  public BusinessException(String message)
  {
    super(message);
  }

  public BusinessException(Throwable cause) {
    super(cause);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public Map<String, String> getErrorMessages() {
    return this.errorMessages;
  }

  public static long getSerialversionuid() {
    return -5433056591268198786L;
  }

  public void setErrorMessages(Set<? extends ConstraintViolation<? extends Object>> violations)
  {
    for (ConstraintViolation violation : violations) {
      String propertyPath = violation.getPropertyPath().toString();
      String message = violation.getMessage();
      this.errorMessages.put(propertyPath, message);
    }
  }

  public void setErrorMessages(Map<String, String> errorMessages)
  {
    this.errorMessages = errorMessages;
  }

  public void addErrorMessage(String fieldName, String message)
  {
    this.errorMessages.put(fieldName, message);
  }

  public void addBindingResultTo(BindingResult result)
  {
    for (String key : this.errorMessages.keySet())
      result.rejectValue(key, "", (String)this.errorMessages.get(key));
  }
}
