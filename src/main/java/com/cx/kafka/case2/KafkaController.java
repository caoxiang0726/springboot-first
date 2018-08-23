package com.cx.kafka.case2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class KafkaController {

    /**
     * 注入kafkaTemplate
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * 发送消息的方法
     *
     * @param key  推送数据的key
     * @param data 推送数据的data
     */
    private void send(String key, String data) {
        kafkaTemplate.send("test", key, data);
    }

    @RequestMapping("/kafka")
    public String testKafka() {
        int iMax = 6;
        for (int i = 1; i < iMax; i++) {
            send("key" + i, "data" + i);
        }
        return "success";
    }

//    public static void main(String[] args) {
//        SpringApplication.run(KafkaController.class, args);
//    }

    /**
     * 使用日志打印消息
     *
     * Spring boot会自动创建KafkaListenerContainerFactory。
     * Error creating bean with name 'kafkaListenerContainerFactory'
     *
     * 如果要定义KafkaListen，可以在application.properties设置，相关配置项如下：
     *
     * Failed to instantiate [org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory]:
     * Factory method 'kafkaListenerContainerFactory' threw exception; nested exception is
     * java.lang.BootstrapMethodError: java.lang.NoSuchMethodError:
     * org.springframework.kafka.listener.config.ContainerProperties.setClientId(Ljava/lang/String;)V
     *
     * 确实类中没有这个方法.. 2.1.0 版本的bug。

     spring.kafka.listener.ack-count= # Number of records between offset commits when ackMode is "COUNT" or "COUNT_TIME".
     spring.kafka.listener.ack-mode= # Listener AckMode. See the spring-kafka documentation.
     spring.kafka.listener.ack-time= # Time between offset commits when ackMode is "TIME" or "COUNT_TIME".
     spring.kafka.listener.concurrency= # Number of threads to run in the listener containers.
     spring.kafka.listener.poll-timeout= # Timeout to use when polling the consumer.
     spring.kafka.listener.type=single # Listener type.
     */
    private static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @KafkaListener(topics = "test")
    public void receive(ConsumerRecord<?, ?> consumer) {
        logger.info("------------------{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }


}