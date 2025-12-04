package com.infosupport.jeedemo.jms;

import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "salaryProcessor")
public class SalaryProcessor implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(SalaryProcessor.class);

    @Override
    public void onMessage(Message message) {
        try {
            String m = message.getBody(String.class);
            log.trace("Message received: {}", m);
        } catch (JMSException e) {
            log.error("Something went wrong..", e);
        }
    }
}
