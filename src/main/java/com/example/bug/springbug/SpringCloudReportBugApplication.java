package com.example.bug.springbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class SpringCloudReportBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudReportBugApplication.class, args);
    }

    @Bean
    public Function<Message<String>, List<Message<String>>> shapes() {
        return value ->
                Arrays.asList(MessageBuilder
                        .withPayload(value.getPayload())
                        .setHeader("spring.cloud.stream.sendto.destination", "topic-dynamic")
                        .build());
    }

}
