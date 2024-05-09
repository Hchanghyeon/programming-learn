package util;

import java.util.UUID;

final public class StringUtils {

    private static final String LINE_ENTER = "\r\n";
    private static final String LINE_SEPARATOR = ":";

    public static String getRandomString(final int stringCount) {
        final StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < stringCount; i++){
            stringBuffer.append(getRandomUUID()).append(LINE_SEPARATOR);
        }

        return stringBuffer.append("END").toString();
    }

    private static String getRandomUUID() {
        return UUID.randomUUID().toString(); // 무작위 UUID 생성
    }

    public static String[] parseString(final String inputString){
        return inputString.split(LINE_SEPARATOR);
    }

    public static boolean isBlankOrNull(final String text){
        return text == null || text.isEmpty();
    }
}
