package tacos.kitchen.messaging.kafka.listener;

//@Configuration
//@EnableKafka
public class KafkaConsumerConfiguration {
//    @Bean
//    ConsumerFactory<String, Order> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        properties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
//        properties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "java.lang.String");
//        properties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        properties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "tacos.Order");
//        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "tacos");
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "tacocloud_kitchen");
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, Order> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory
//                = new ConcurrentKafkaListenerContainerFactory<>();
//        kafkaListenerContainerFactory.setConcurrency(2);
//        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
//        return kafkaListenerContainerFactory;
//    }
}
