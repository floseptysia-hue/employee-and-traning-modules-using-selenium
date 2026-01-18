package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            throw new RuntimeException("ENV " + key + " is not set");
        }
        return value;
    }
}
