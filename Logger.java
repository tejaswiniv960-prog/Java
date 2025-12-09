import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final String LOG_FILE = "application.log";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static synchronized void log(String level, String message) {

        message = MaskingUtil.maskPII(message);

        String time = sdf.format(new Date());
        String finalMessage = "[" + time + "] [" + level + "] " + message;

        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(finalMessage);

        } catch (Exception e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }

    public static void info(String msg) { log("INFO", msg); }
    public static void error(String msg) { log("ERROR", msg); }
}
