package org.vijayian.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * component.
 *
 * @author ViJay
 */
@Component
public class KafkaComponent {

    @KafkaListener(topics = "test01")
    public void listen(ConsumerRecord record) {
        System.out.println(record.topic());//test01
        System.out.println(record.value());//hello
    }
}
