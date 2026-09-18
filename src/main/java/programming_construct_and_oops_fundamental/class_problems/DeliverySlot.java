public class DeliverySlot {
    private final String orderId;
    private final String timeSlot;
    public DeliverySlot(String orderId, String timeSlot) {
        if (orderId == null || orderId.trim().isEmpty()
                || timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new IllegalArgumentException("Order and time slot are required");
        }
        this.orderId = orderId.trim();
        this.timeSlot = timeSlot.trim();
    }
    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }
    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00")
                || timeSlot.equals("13:00-14:00")
                || timeSlot.equals("19:00-20:00")
                || timeSlot.equals("20:00-21:00");
    }
    public static void main(String[] args) {
        DeliverySlot peak = new DeliverySlot("ORD101", "13:00-14:00");
        DeliverySlot asap = new DeliverySlot("ORD102");
        System.out.println(peak.isPeakHour());
        System.out.println(asap.isPeakHour());
    }
}