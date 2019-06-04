package com.imooc.web.async;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ToString
@Slf4j
@AllArgsConstructor
@Component
public class MockQueue {
    private String placeOrder;
    private String completeOrder;

    public MockQueue() {
        log.info("create a new MockQueue");
    }
    public void setPlaceOrder(String placeOrder)  {
        new Thread(()-> {
            log.info ("get placeOrder : {}",placeOrder);
            try {
                Thread.sleep (1000);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            this.completeOrder = placeOrder;
            log.info ("placeOrder complete: {}",placeOrder);
        }).start ();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
