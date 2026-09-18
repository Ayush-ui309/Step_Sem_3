public class ISBNValidator {
    static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }
        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return pubCode + rest;
    }
    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }
        String pubCode = code.substring(0, 3);
        for (int i = 0; i < pubCode.length(); i++) {
            if (Character.isLetter(pubCode.charAt(i)) == false) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        String body = code.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (Character.isDigit(body.charAt(i)) == false) {
                return "Invalid: year and catalog number must be digits";
            }
        }
        String year = body.substring(0, 4);
        String catalog = body.substring(4);
        String result = "[" + pubCode + "] YEAR: " + year + " | CATALOG: " + catalog;
        return result;
    }
    public static void main(String[] args) {
        String raw1 = " pen2026004251 ";
        System.out.println(validateAndFormat(normalizeCode(raw1)));
        String raw2 = "12N2026004251";
        System.out.println(validateAndFormat(normalizeCode(raw2)));
    }
}