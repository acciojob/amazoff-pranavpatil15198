package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    HashMap<Order, DeliveryPartner> orderDeliveryPartner = new HashMap<>();

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(String id){
        deliveryPartnerRepository.addPartner(id);
    }

    public void setOrderDeliveryPartner(String orderId, String deliveryId){
        HashMap<String, Order> orders = orderRepository.getOrders();
        HashMap<String, DeliveryPartner> deliveryPartners = deliveryPartnerRepository.getDeliveryPartner();

        Order order = orders.getOrDefault(orderId, new Order());
        DeliveryPartner deliveryPartner = deliveryPartners.getOrDefault(deliveryId, new DeliveryPartner());

        Integer numberOfOrders = deliveryPartner.getNumberOfOrders();
        numberOfOrders++;
        deliveryPartner.setNumberOfOrders(numberOfOrders);
        orderDeliveryPartner.put(order, deliveryPartner);
    }

    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }

    public DeliveryPartner getDeliveryPartnerById(String id){
        return deliveryPartnerRepository.getDeliveryPartner().getOrDefault(id, new DeliveryPartner());
    }

    public int getOrdersByPartner(String id){
        for(Order order : orderDeliveryPartner.keySet()){
            if(orderDeliveryPartner.get(order).equals(id)){
                return orderDeliveryPartner.get(order).getNumberOfOrders();
            }
        }

        return 0;
    }

    public List<String> getOrdersByPartnerId(String id){
        List<String> ans = new ArrayList<>();

        for(Order order : orderDeliveryPartner.keySet()){
            if(orderDeliveryPartner.get(order).equals(id)){
                ans.add(orderDeliveryPartner.get(order).getId());
            }
        }

        return ans;
    }

    public List<String> getAllOrders(){
        HashMap<String, Order> orders = orderRepository.getOrders();
        List<String> ans = new ArrayList<>();
        for(String order : orders.keySet()){
            ans.add(order);
        }

        return ans;
    }

    public int getUnassignedOrders(){
        return orderRepository.getOrders().size() - orderDeliveryPartner.size();
    }

    public int orderLeftAfterGivenTime(String deliveryTime, String deliveryPartnerId){
        int cnt = 0;

        int time = Integer.parseInt(deliveryTime.substring(0, 2))*60 + Integer.parseInt(deliveryTime.substring(3,5));

        for(Order order : orderDeliveryPartner.keySet()){
            if(orderDeliveryPartner.get(order).getId().equals(deliveryPartnerId) && order.getDeliveryTime() > time){
                cnt++;
            }
        }

        return cnt;
    }

    public String getFinalDeliveryTime(String deliveryPartnerId){
        int curr = 0;
        for(Order order : orderDeliveryPartner.keySet()){
            if(orderDeliveryPartner.get(order).getId().equals(deliveryPartnerId)){
                curr = Math.max(order.getDeliveryTime(),curr);
            }
        }
        int mins = curr % 60;
        int hrs = curr / 60;
        String ans = "";
        ans = hrs + ":" + mins;
        return  ans;
    }

    public void deleteDeliveryPartner(String id){

        for(Order order : orderDeliveryPartner.keySet()){
            if(orderDeliveryPartner.get(order).equals(id)){
                orderDeliveryPartner.remove(order);
            }
        }
        deliveryPartnerRepository.deletePartnerById(id);
    }

    public void deleteOrder(String id){
        for(Order order : orderDeliveryPartner.keySet()){
            if(order.getId().equals(id)){
                orderDeliveryPartner.remove(order);
            }
        }
        orderRepository.deleteOrder(id);
    }

}
