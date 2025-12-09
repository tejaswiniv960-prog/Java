import java.util.regex.*;

public class MaskingUtil {

    private static final Pattern EMAIL = Pattern.compile(
            "([A-Za-z0-9._%+-])([A-Za-z0-9._%+-]*)(@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6})"
    );

    private static final Pattern PHONE = Pattern.compile("(\\d{6})(\\d{4})");

    public static String maskPII(String text) {
        if (text == null) return null;

        text = maskEmail(text);
        text = maskPhone(text);

        return text;
    }

    private static String maskEmail(String input) {
        Matcher m = EMAIL.matcher(input);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            String first = m.group(1);
            String rest = m.group(2);
            String domain = m.group(3);
            m.appendReplacement(sb, first + "***" + domain);
        }

        m.appendTail(sb);
        return sb.toString();
    }

    private static String maskPhone(String input) {
        Matcher m = PHONE.matcher(input);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            String masked = "******" + m.group(2);
            m.appendReplacement(sb, masked);
        }

        m.appendTail(sb);
        return sb.toString();
    }
}
