package com.sourav.processor;

import com.sourav.dto.Order;
import com.sourav.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements Processor {

    @Autowired
    private OrderService service;
    @Override
    public void process(Exchange exchange) throws Exception {
        service.addOrder(exchange.getIn().getBody(Order.class));
    }
}
