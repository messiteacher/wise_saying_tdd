package app.global;

public class AppConfig {

    private static String mode;

    private static String dbMode;

    static {
        setDevMode();
        setFileDb();
    }

    public static void setProdMode() {
        mode = "prod";
    }

    public static void setDevMode() {
        mode = "dev";
    }

    public static void setTestMode() {
        mode = "test";
    }

    public static boolean isProdMode() {
        return mode.equals("prod");
    }

    public static boolean isDevMode() {
        return mode.equals("dev");
    }

    public static boolean isTestMode() {
        return mode.equals("test");
    }

    public static void setFileDb() {
        dbMode = "file";
    }

    public static void setMemDbe() {
        dbMode = "mem";
    }

    public static boolean isFileDb() {
        return dbMode.equals("file");
    }

    public static boolean isMemDb() {
        return dbMode.equals("mem");
    }

    public static String getDBPath() {
        return "db/" + mode + "/";
    }
}
