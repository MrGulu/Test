package Exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils
{
  public static RuntimeException unchecked(Exception e)
  {
    if ((e instanceof RuntimeException)) {
      return (RuntimeException)e;
    }
    return new RuntimeException(e);
  }

  public static String getStackTraceAsString(Exception e)
  {
    StringWriter stringWriter = new StringWriter();
    e.printStackTrace(new PrintWriter(stringWriter));
    return stringWriter.toString();
  }

  public static boolean isCausedBy(Exception ex, Class<? extends Exception>[] causeExceptionClasses)
  {
    Throwable cause = ex;
    while (cause != null) {
      for (Class causeClass : causeExceptionClasses) {
        if (causeClass.isInstance(cause)) {
          return true;
        }
      }
      cause = cause.getCause();
    }
    return false;
  }
}
