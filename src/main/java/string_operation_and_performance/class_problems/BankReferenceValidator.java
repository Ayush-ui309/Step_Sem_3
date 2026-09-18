public class BankReferenceValidator {
    static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode + rest;
    }
    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }
        String bankCode = reference.substring(0, 3);
        for (int i = 0; i < bankCode.length(); i++) {
            if (Character.isLetter(bankCode.charAt(i)) == false) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        String body = reference.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (Character.isDigit(body.charAt(i)) == false) {
                return "Invalid: date and sequence must be digits";
            }
        }
        String date = body.substring(0, 6);
        String seq = body.substring(6);
        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yy = date.substring(4, 6);
        String result = "[" + bankCode + "] DATE: " + dd + "/" + mm + "/" + yy + " | SEQ: " + seq;
        return result;
    }
    public static void main(String[] args) {
        String raw1 = " hdf03022600042 ";
        System.out.println(validateAndFormat(normalizeReference(raw1)));
        String raw2 = "12F03022600042";
        System.out.println(validateAndFormat(normalizeReference(raw2)));
    }
}