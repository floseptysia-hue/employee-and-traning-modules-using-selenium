package utils;

public class TestDataUtil {
    // VALID DATA
    public static String validEmployeeId() {
        return "QA" + System.currentTimeMillis();
    }

    public static String validName() {
        return "Florida Septysia";
    }

    public static String validEmail() {
        return "florida.septysia+" + System.currentTimeMillis() + "@mail.com";
    }

    public static String validPhone() {
        return "08" + (long)(Math.random() * 1_000_000_000L);
    }

    // INVALID
    public static String invalidEmail() {
        return "invalid-email-format";
    }

    public static String nonNumericPhone() {
        return "ABCDEF123";
    }

    public static String longName() {
        return "floridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambunfloridaseptysiatambun";
    }

    public static String empty() {
        return "";
    }

    public static String specialCharacter() {
        return "###@@@!!!";
    }

    public static String invalidDivision() {
        return "INVALID_DIVISION";
    }
}