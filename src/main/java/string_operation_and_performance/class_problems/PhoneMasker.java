public class PhoneMasker {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }
        for (int i = 0; i < phone.length(); i++) {
            if (Character.isDigit(phone.charAt(i)) == false) {
                return "Invalid phone number";
            }
        }
        String masked = "XXXXXX-" + phone.substring(6);
        return masked;
    }
    public static void main(String[] args) {
        System.out.println(maskPhoneNumber("9876543210"));
        System.out.println(maskPhoneNumber("98765"));
    }
}