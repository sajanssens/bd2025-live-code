package com.infosupport.jeedemo.jms;

import com.github.javafaker.Faker;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Singleton @Startup
public class PaymentTransactionsProducer {

    private final Logger log = LoggerFactory.getLogger(PaymentTransactionsProducer.class);

    @Resource(name = "jms/paymentTransaction")
    private Queue queue;

    @Resource(name = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    // @Schedule(hour = "*", minute = "*", second = "*/1")
    public void send() {
        log.debug("About to send 'monthly' payment transactions...");
        for (int i = 0; i < 100; i++) {
            try (var context = connectionFactory.createContext()) {
                PaymentTransactionDto dto = new PaymentTransactionDto(i, new Random().nextInt(100), "Paying for " + new Faker().beer().name());
                context.createProducer().send(queue, dto.toString());
            }
        }
    }
}
