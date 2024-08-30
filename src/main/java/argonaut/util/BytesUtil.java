package argonaut.util;

public class BytesUtil {
    public static String byteArrayToLogString(byte[] message) {
        StringBuilder stringMessage = new StringBuilder();
        for (Byte byt : message) {
            stringMessage.append(byt.shortValue()).append("|");
        }
        stringMessage.append("||");
        return stringMessage.toString();
    }
}
