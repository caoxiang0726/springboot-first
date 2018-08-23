//package com.cx.kafka.case1;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
////@EnableAutoConfiguration
//public class KafkaController {
//
//    public static Logger logger = LoggerFactory.getLogger(KafkaController.class);
//
//    @Autowired
//    private KafkaTemplate<String, String> template;
//
//    //http://localhost:8080/send?topic=t2&key=test1&data=hello122
//    @RequestMapping("/send")
//    @ResponseBody
//    String send(String topic, String key, String data) {
//        template.send(topic, key, data);
//        return "success";
//    }
//
//   /* public static void main(String[] args) throws Exception {
//        SpringApplication.run(KafkaController.class, args);
//    }*/
//
//    @KafkaListener(id = "t1", topics = "test")
//    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
//    }
//
//    @KafkaListener(id = "t2", topics = "t2")
//    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
//    }
//
//}