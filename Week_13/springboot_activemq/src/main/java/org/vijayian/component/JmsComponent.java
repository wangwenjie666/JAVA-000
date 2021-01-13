package org.vijayian.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * send & receive.
 *
 * @author ViJay
 */
@Component
public class JmsComponent {
    @Autowired
    JmsMessagingTemplate messagingTemplate;

    @Autowired
    Queue queue;

    @Autowired
    Topic topic;

    public void sendQueue(String message) {
        messagingTemplate.convertAndSend(this.queue, message);
    }

    public void sendTopic(String message) {
        messagingTemplate.convertAndSend(this.topic, message);
    }

    @JmsListener(destination = "my_queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String msg) {
        System.out.println("receive queue:" + msg);
    }

    @JmsListener(destination = "my_topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String msg) {
        System.out.println("receive topic:" + msg);
    }
}

