import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add-order")
    order(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order added successfully");
    }
    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId) {
        orderService.addPartner(partnerId);
        return ResponseEntity.ok("Partner added successfully");
    }
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId) {
        orderService.assignOrderToPartner(orderId, partnerId);
        return ResponseEntity.ok("Order assigned to partner successfully");
    }
    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId) {
        DeliveryPartner partner = orderService.getPartnerById(partnerId);
        return ResponseEntity.ok(partner);
    }
    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId) {
        int orderCount = orderService.getOrderCountByPartnerId(partnerId);
        return ResponseEntity.ok(orderCount);
    }
    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<Order>> getOrdersByPartnerId(@PathVariable String partnerId) {
        List<Order> orders = orderService.getOrdersByPartnerId(partnerId);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders() {
        int unassignedOrderCount = orderService.getCountOfUnassignedOrders();
        return ResponseEntity.ok(unassignedOrderCount);
    }
    @GetMapping("/get-count-of-orders-left-after-given-time/{time}/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId) {
        int orderCount = orderService.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
        return ResponseEntity.ok(orderCount);
    }
    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId) {
        String lastDeliveryTime = orderService.getLastDeliveryTimeByPartnerId(partnerId);
        return ResponseEntity.ok(lastDeliveryTime);
    }
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId) {
        orderService.deletePartnerById(partnerId);
        return ResponseEntity.ok("Partner deleted successfully");
    }
    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
