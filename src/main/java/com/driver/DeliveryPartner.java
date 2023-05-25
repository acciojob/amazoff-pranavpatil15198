public class DeliveryPartner {
    private String partnerId;
    private List<Order> orders;

    // Constructors, getters, and setters

    public DeliveryPartner(String partnerId) {
        this.partnerId = partnerId;
        this.orders = new ArrayList<>();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int getOrderCount() {
        return orders.size();
    }

    public int getOrdersLeftAfterGivenTime(String time) {
        int count = 0;
        for (Order order : orders) {
            if (order.getDeliveryTime().compareTo(time) > 0) {
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTime() {
        if (orders.isEmpty()) {
            return "";
        }
        Order lastOrder = orders.get(orders.size() - 1);
        return lastOrder.getDeliveryTime();
    }
}
