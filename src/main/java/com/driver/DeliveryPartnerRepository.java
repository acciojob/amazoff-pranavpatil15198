package com.driver;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Data
@Repository
public class DeliveryPartnerRepository {
    private HashMap<String, DeliveryPartner> deliveryPartner = new HashMap<>();

    public void addPartner(String id) {
        deliveryPartner.put(id, new DeliveryPartner(id));
    }

    public void deletePartnerById(String id){
        deliveryPartner.remove(id);
    }
}
