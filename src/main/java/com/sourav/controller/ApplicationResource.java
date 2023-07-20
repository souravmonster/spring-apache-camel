package com.sourav.controller;

import com.sourav.dto.Order;
import com.sourav.processor.OrderProcessor;
import com.sourav.service.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {

    @Autowired
    private OrderService service;

    @BeanInject
    private OrderProcessor processor;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet")
                .port(9090)
                .host("localhost")
                .bindingMode(RestBindingMode.json);

        rest("/hello-world")
                .get()
                .to("direct:hello-world");
                from("direct:hello-world")
                .transform().constant("Welcome to apache camel demo");

        rest("/getOrders")
                .get()
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .to("direct:getOrders");
                from("direct:getOrders")
                .transform().constant(service.getOrders());

            rest().post("/addOrder")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Order.class)
                .outType(Order.class)
                .to("direct:addOrder");
                from("direct:addOrder")
                .process(processor);
    }
}
