import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final Map<String, Order> orders;
    private final Map<String, DeliveryPartner> partners;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orders = new HashMap<>();
        this.partners = new HashMap<>();
    }
     public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public void addPartner(String partnerId) {
        partners.put(partnerId, new DeliveryPartner(partnerId));
    }

    public void assignOrderToPartner(String orderId, String partnerId) {
        Order order = orders.get(orderId);
        DeliveryPartner partner = partners.get(partnerId);
        if (order != null && partner != null) {
            order.setDeliveryPartner(partner);
            partner.addOrder(order);
        }
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return partners.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        DeliveryPartner partner = partners.get(partnerId);
        if (partner != null) {
            return partner.getOrderCount();
        }
        return 0;
    }

    public List<Order> getOrdersByPartnerId(String partnerId) {
        DeliveryPartner partner = partners.get(partnerId);
        if (partner != null) {
            return partner.getOrders();
        }
        return new ArrayList<>();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public int getCountOfUnassignedOrders() {
        int count = 0;
        for (Order order : orders.values()) {
            if (order.getDeliveryPartner() == null) {
                count++;
            }
        }
        return count;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        DeliveryPartner partner = partners.get(partnerId);
        if (partner != null) {
            return partner.getOrdersLeftAfterGivenTime(time);
        }
        return 0;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        DeliveryPartner partner = partners.get(partnerId);
        if (partner != null) {
            return partner.getLastDeliveryTime();
        }
        return "";
    }

    public void deletePartnerById(String partnerId) {
        DeliveryPartner partner = partners.get(partnerId);
        if (partner != null) {
            partners.remove(partnerId);
            for (Order order : partner.getOrders()) {
                order.setDeliveryPartner(null);
            }
        }
    }

    public void deleteOrderById(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setDeliveryPartner(null);
            orders.remove(orderId);
        }
    }
}
