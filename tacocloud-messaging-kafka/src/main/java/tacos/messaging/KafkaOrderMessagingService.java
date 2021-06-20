package tacos.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

@Service
@Slf4j
public class KafkaOrderMessagingService implements OrderMessagingService {
    final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(
            KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void sendOrder(Order order) {
        log.info("Order: " + order.toString());
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
