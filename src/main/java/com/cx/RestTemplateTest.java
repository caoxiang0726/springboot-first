package com.cx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by caoxiang on 2018/3/18.
 */
@SpringBootConfiguration
public class RestTemplateTest {

    @Bean
    public RestTemplate restTemplat(RestTemplateBuilder builder){
        return builder.build();
    }

    /*@Bean
    public CommandLineRunner run (RestTemplate restTemplate){

//        return  args -> {
            String result = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random",
                    String.class);
            System.out.println(result);

//        }

    }*/
}
