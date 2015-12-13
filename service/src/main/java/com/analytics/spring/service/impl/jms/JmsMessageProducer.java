package com.analytics.spring.service.impl.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by Dmitry Natalenko on 27.07.2015.
 */
@Component("topicMessageProducer")
public class JmsMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageProducer.class);
    protected static final String MESSAGE_COUNT = "messageCount";

    @Autowired
    private JmsTemplate jmsTopicTemplate;

    private int messageCount = 10;

    /**
     * Generates JMS messages
     */
    @Scheduled(fixedRate = 10000)
    public void generateMessages() throws JMSException {
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            final String text = "Message number is " + i + ".";

            jmsTopicTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(text);
                    message.setIntProperty("messageCount", index);
                    logger.info("Sending message: " + text);
                    return message;
                }
            });
        }
    }
}
