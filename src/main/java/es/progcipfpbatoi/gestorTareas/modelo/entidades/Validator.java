package es.progcipfpbatoi.gestorTareas.modelo.entidades;

import java.util.regex.Pattern;

public class Validator {

    private static final String NUMERIC_REGEXP = "\\d+";
    private static final String ALFABETIC_FIRST_UPPERCASE_REGEXP = "^[A-Z][a-zA-Z]*$";
    private static final String DNI_REGEXP = "\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
    private static final String SPANISH_POSTAL_CODE_REGEXP = "^(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}$";
    private static final String SPANISH_MOBILE_NUMBER_REGEXP = "^(0034|\\+34|34)[6-7]\\d{8}$";
    private static final String EMAIL_REGEXP = "^[A-Za-z][A-Za-z0-9._-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{5,20}$";

    public static boolean isValidNumberOfLength(String param, int maxLength) {
        return isNotEmptyOrNull(param) && param.length() <= maxLength && param.matches(NUMERIC_REGEXP);
    }

    public static boolean isValidText(String param) {
        return isNotEmptyOrNull(param) && param.matches(ALFABETIC_FIRST_UPPERCASE_REGEXP);
    }

    public static boolean isValidNameAndSurname(String param) {
        return isNotEmptyOrNull(param) && param.length() >= 5 && Character.isUpperCase(param.charAt(0));
    }

    public static boolean isValidDNI(String param) {
        return isNotEmptyOrNull(param) && param.matches(DNI_REGEXP);
    }

    public static boolean isValidSpanishPostalCode(String param) {
        return isNotEmptyOrNull(param) && param.matches(SPANISH_POSTAL_CODE_REGEXP);
    }

    public static boolean isValidSpanishMobileNumber(String param) {
        return isNotEmptyOrNull(param) && param.matches(SPANISH_MOBILE_NUMBER_REGEXP);
    }

    public static boolean isValidEmail(String param) {
        return isNotEmptyOrNull(param) && param.matches(EMAIL_REGEXP);
    }

    public static boolean isValidPassword(String param) {
        return isNotEmptyOrNull(param) && param.matches(PASSWORD_REGEXP);
    }

    private static boolean isNotEmptyOrNull(String param) {
        return param != null && !param.isEmpty();
    }
}
