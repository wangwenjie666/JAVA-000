package org.vijayian.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller.
 *
 * @author ViJay
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * http://localhost:8080/?topic=test01&message=hello
     */
    @GetMapping("/")
    public String sendMsg(String topic, String message) {
        kafkaTemplate.send(topic, message);
        return "success";
    }


}
