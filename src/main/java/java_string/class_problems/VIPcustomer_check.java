public class VIPcustomer_check {
    static String validateCustomerId(String customerId) {
        if (customerId.startsWith("VIP-")) {
            return "VIP Customer";
        }
        return "Regular Customer";
    }

    public static void main(String[] args) {
        String customerId = "VIP-4589";
        String result = validateCustomerId(customerId);
        System.out.println(result);
    }
}