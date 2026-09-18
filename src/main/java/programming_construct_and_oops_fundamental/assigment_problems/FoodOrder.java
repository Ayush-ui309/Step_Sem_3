public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;
    public FoodOrder(String studentName, String dishName) {
        if (!isMeaningful(studentName) || !isMeaningful(dishName)) {
            throw new IllegalArgumentException("Student and dish are required");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }
    private static boolean isMeaningful(String value) {
        return value != null && !value.trim().isEmpty();
    }
    public void markDelivered() {
        if (delivered) {
            System.out.println("Warning: order was already delivered.");
            return;
        }
        delivered = true;
        System.out.println("Order delivered.");
    }
    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;
        if (rawOrders != null) {
            for (String[] raw : rawOrders) {
                if (raw == null || raw.length < 2) {
                    rejected++;
                    continue;
                }
                try {
                    new FoodOrder(raw[0], raw[1]);
                    valid++;
                } catch (IllegalArgumentException ex) {
                    rejected++;
                }
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(orders);
        FoodOrder order = new FoodOrder("Ravi", "Dosa");
        order.markDelivered();
        order.markDelivered();
    }
}