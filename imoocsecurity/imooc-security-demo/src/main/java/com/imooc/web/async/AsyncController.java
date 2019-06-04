package com.imooc.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Slf4j
@RestController
public class AsyncController {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public Callable<String> order() throws InterruptedException {
        log.info ("main thread start");
        Callable<String> result = () -> {
            log.info ("second thread start");
            Thread.sleep (1000);
            log.info ("second thread stop");
            return "success";
        };
        log.info ("main thread return");
        return result;
    }

    @RequestMapping("/mockOrder")
    public DeferredResult<String> mockOrder() throws Exception {
        log.info ("main thread start");
        String orderNumber = RandomStringUtils.randomNumeric (8);
        mockQueue.setPlaceOrder (orderNumber);
        DeferredResult<String> result = new DeferredResult<> ();
        deferredResultHolder.getMap ().put (orderNumber,result);
        log.info ("main thread stop");
        return result;
    }

}
