package com.example.bug.springbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringCloudReportBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudReportBugApplication.class, args);
    }

    @Bean
    /**
     * Splits the input string by delimiter `-`
     * Ex:
     * Input: square-circle-rectangle
     * Output: [square, circle, rectangle]
     */
    public Function<Message<String>, List<Message<String>>> splitter() {
        return value ->
                Arrays.stream(value.getPayload().split("-"))
                        .map(message ->
                                MessageBuilder
                                        .withPayload(message)
                                        .setHeader("spring.cloud.stream.sendto.destination", "topic-dynamic").build())
                        .collect(Collectors.toList());
    }

}
