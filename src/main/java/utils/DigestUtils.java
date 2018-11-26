package utils;

import org.apache.commons.lang3.Validate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class DigestUtils
{
  private static final String SHA1 = "SHA-1";
  private static final String MD5 = "MD5";
  private static SecureRandom random = new SecureRandom();

  public static byte[] sha1(byte[] input)
  {
    return digest(input, "SHA-1", null, 1);
  }

  public static byte[] sha1(byte[] input, byte[] salt)
  {
    return digest(input, "SHA-1", salt, 1);
  }

  public static byte[] sha1(byte[] input, byte[] salt, int iterations)
  {
    return digest(input, "SHA-1", salt, iterations);
  }

  private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations)
  {
    try
    {
      MessageDigest digest = MessageDigest.getInstance(algorithm);

      if (salt != null) {
        digest.update(salt);
      }

      byte[] result = digest.digest(input);

      for (int i = 1; i < iterations; i++) {
        digest.reset();
        result = digest.digest(result);
      }
      return result;
    } catch (GeneralSecurityException e) {
      throw ExceptionUtils.unchecked(e);
    }
  }

  public static byte[] generateSalt(int numBytes)
  {
    Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

    byte[] bytes = new byte[numBytes];
    random.nextBytes(bytes);
    return bytes;
  }

  public static byte[] md5(InputStream input)
    throws IOException
  {
    return digest(input, "MD5");
  }

  public static byte[] sha1(InputStream input)
    throws IOException
  {
    return digest(input, "SHA-1");
  }

  private static byte[] digest(InputStream input, String algorithm)
    throws IOException
  {
    try
    {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
      int bufferLength = 8192;
      byte[] buffer = new byte[bufferLength];
      int read = input.read(buffer, 0, bufferLength);
      while (read > -1) {
        messageDigest.update(buffer, 0, read);
        read = input.read(buffer, 0, bufferLength);
      }
      return messageDigest.digest();
    } catch (GeneralSecurityException e) {
      throw ExceptionUtils.unchecked(e);
    }
  }
  /**@Param String a
   * @return String signature
   * @Description(功能描述) :  SHA-1加密
   * @author(作者) ：  延子恒
   * @date (开发日期)          :  2016-12-20 下午6:53:55
   */
  public static String sha1(String input) throws IOException
  {
    InputStream a = new ByteArrayInputStream(input.getBytes());
    byte[] b= digest(a, "SHA-1");
    StringBuffer c = new StringBuffer();
    // 字节数组转换为 十六进制 数
    for (int i = 0; i < b.length; i++) {
      String shaHex = Integer.toHexString(b[i] & 0xFF);
      if (shaHex.length() < 2) {
        c.append(0);
      }
      c.append(shaHex);
    }
    return c.toString();
  }

}
