package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.StringTokenizer;
import java.util.UUID;

public class UUIDUtils
{
  private static SecureRandom seederStatic = null;
  private static String netAddrHexStr = null;
  private static String portHexStr = "0000";
  private static byte[] addrBytes = null;
  private static int[] addrIntAry = null;
  private static int nextSeq32767 = 0;
  private static int nextSeq999 = 0;
  private static char[] radixDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  
  static
  {
    try
    {
      seederStatic = new SecureRandom();
      seederStatic.nextInt();
      InetAddress.getLocalHost().getHostAddress();
      addrBytes = InetAddress.getLocalHost().getAddress();
      netAddrHexStr = toHex(toInt(addrBytes), 8);
    }
    catch (Exception ex)
    {
      throw new RuntimeException("get netAddr error" + ex.getMessage());
    }
  }
  
  Logger logger = LoggerFactory.getLogger(UUIDUtils.class);
  
  public static void setNetAddress(String ipAddr, int port)
    throws UnknownHostException
  {
    netAddrHexStr = getIPAddressHexStr(ipAddr);
    portHexStr = toHex(port, 4);
  }
  
  public static String generate()
  {
    StringBuffer uid = new StringBuffer(32);
    
    uid.append(getSystemMillisRadix32());
    
    uid.append(getSeqRadix32());
    
    uid.append(netAddrHexStr);
    
    uid.append(portHexStr);
    
    uid.append(toHex(getRandom(), 8));
    
    return uid.toString();
  }
  
  public static String getIPAddress(String uuid)
    throws UnknownHostException
  {
    String ipHex = uuid.substring(12, 20);
    StringBuffer buf = new StringBuffer();
    buf.append(toInt(ipHex.substring(6, 8), 2)).append(".");
    buf.append(toInt(ipHex.substring(4, 6), 2)).append(".");
    buf.append(toInt(ipHex.substring(2, 4), 2)).append(".");
    buf.append(toInt(ipHex.substring(0, 2), 2));
    return buf.toString();
  }
  
  public static int getPort(String uuid)
  {
    int port = toInt(uuid.substring(20, 24), 4);
    return port;
  }
  
  public static long getTimestamp(String uuid)
  {
    String timestampStr = uuid.substring(0, 9);
    return Long.parseLong(timestampStr, 32);
  }
  
  public static long getUniqueLong()
  {
    long l = System.currentTimeMillis();
    
    l *= 1000000L;
    
    long ipv4_lastNumber = (addrBytes[3] & 0xFF) * 1000;
    
    l = l + ipv4_lastNumber + getSeq999();
    
    return l;
  }
  
  private static String getIPAddressHexStr(String ipAddr)
    throws UnknownHostException
  {
    addrIntAry = getIPAddressIntAry(ipAddr);
    for (int i = 0; i < addrIntAry.length; i++) {
      addrBytes[i] = ((byte)(addrIntAry[i] & 0xFF));
    }
    return toHex(addrIntAry);
  }
  
  private static int[] getIPAddressIntAry(String ipAddr)
  {
    String ipStr = ipAddr.trim();
    if ("localhost".equalsIgnoreCase(ipStr)) {
      ipStr = "127.0.0.1";
    }
    int[] addrInts = new int[4];
    StringTokenizer tokenizer = new StringTokenizer(ipStr, ".");
    int i = 0;
    while (tokenizer.hasMoreTokens())
    {
      String token = tokenizer.nextToken();
      addrInts[(i++)] = Integer.parseInt(token);
    }
    return addrInts;
  }
  
  private static String getSystemMillisRadix32()
  {
    String millisStr = Long.toString(System.currentTimeMillis(), 32).toUpperCase();
    int len = millisStr.length();
    if (len < 9)
    {
      StringBuffer buffer = new StringBuffer(10);
      buffer.append(millisStr);
      int offset = 9 - len;
      for (int i = 0; i < offset; i++) {
        buffer.append("0");
      }
      millisStr = buffer.toString();
    }
    else if (len > 9)
    {
      millisStr = millisStr.substring(len - 9);
    }
    return millisStr;
  }
  
  private static synchronized int getRandom()
  {
    return seederStatic.nextInt();
  }
  
  private static String toHex(int value, int length)
  {
    StringBuffer buffer = new StringBuffer(length);
    int shift = length - 1 << 2;
    int i = -1;
    for (;;)
    {
      i++;
      if (i >= length) {
        break;
      }
      buffer.append(radixDigits[(value >> shift & 0xF)]);
      value <<= 4;
    }
    return buffer.toString();
  }
  
  private static String toHex(int[] intAry)
  {
    StringBuffer buffer = new StringBuffer(intAry.length * 2);
    for (int i = 0; i < intAry.length; i++)
    {
      int int1 = intAry[i];
      int int2 = int1 >> 4;
      buffer.append(radixDigits[(int2 & 0xF)]);
      buffer.append(radixDigits[(int1 & 0xF)]);
    }
    return buffer.toString();
  }
  
  private static int toInt(byte[] bytes)
  {
    int value = 0;
    int aryLen = bytes.length;
    for (int i = aryLen - 1; i >= 0; i--)
    {
      value <<= 8;
      value |= bytes[i] & 0xFF;
    }
    return value;
  }
  
  private static int toInt(String hexStr, int len)
  {
    int value = 0;
    for (int i = 0; i < len; i += 2)
    {
      char c = hexStr.charAt(i);
      int high;

      if (c <= '9') {
        high = c - '0';
      } else {
        high = '\n' + c - 65;
      }
      c = hexStr.charAt(i + 1);
      int low;

      if (c <= '9') {
        low = c - '0';
      } else {
        low = '\n' + c - 65;
      }
      value <<= 8;
      value |= (0xFF & high) << 4 | 0xFF & low;
    }
    return value;
  }
  
  public static byte[] toBytes(int value)
  {
    byte[] bytes = new byte[4];
    bytes[0] = ((byte)(value & 0xFF));
    value >>>= 8;
    bytes[1] = ((byte)(value & 0xFF));
    value >>>= 8;
    bytes[2] = ((byte)(value & 0xFF));
    value >>>= 8;
    bytes[3] = ((byte)(value & 0xFF));
    return bytes;
  }
  
  public static String toHex(byte[] byteAry)
  {
    StringBuffer buffer = new StringBuffer(byteAry.length * 2);
    for (int i = byteAry.length - 1; i >= 0; i--)
    {
      int b1 = byteAry[i] & 0xF;
      int b2 = byteAry[i] >>> 4 & 0xF;
      buffer.append(radixDigits[b2]);
      buffer.append(radixDigits[b1]);
    }
    return buffer.toString();
  }
  
  private static synchronized int getSeq32767()
  {
    int retv = nextSeq32767;
    nextSeq32767 += 1;
    if (nextSeq32767 >= 32768) {
      nextSeq32767 = 0;
    }
    return retv;
  }
  
  private static String getSeqRadix32()
  {
    String seqStr = Long.toString(getSeq32767(), 32).toUpperCase();
    int len = seqStr.length();
    if (len < 3)
    {
      StringBuffer buffer = new StringBuffer(3);
      int offset = 3 - len;
      for (int i = 0; i < offset; i++) {
        buffer.append("0");
      }
      buffer.append(seqStr);
      seqStr = buffer.toString();
    }
    else if (len > 3)
    {
      seqStr = seqStr.substring(len - 3);
    }
    return seqStr;
  }
  
  private static synchronized int getSeq999()
  {
    int retv = nextSeq999;
    nextSeq999 += 1;
    if (nextSeq999 >= 1000) {
      nextSeq999 = 0;
    }
    return retv;
  }
  
  public static String toShortString(UUID u)
  {
    long mostSigBits = u.getMostSignificantBits();
    long leastSigBits = u.getLeastSignificantBits();
    return digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4) + digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12);
  }
  
  private static String digits(long val, int digits)
  {
    long hi = 1L << digits * 4;
    return Long.toString(hi | val & hi - 1L, 36).substring(1);
  }
}
