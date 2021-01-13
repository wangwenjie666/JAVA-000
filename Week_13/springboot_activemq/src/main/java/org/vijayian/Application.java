package org.vijayian;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.vijayian.component.JmsComponent;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * active mq.
 *
 * @author ViJay
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Queue queue() {
        return new ActiveMQQueue("my_queue");
    }

    @Bean
    Topic topic() {
        return new ActiveMQTopic("my_topic");
    }

    @Autowired
    public JmsComponent jmsComponent;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            jmsComponent.sendQueue("queue message");

            jmsComponent.sendTopic("topic message");
        };
    }
}
