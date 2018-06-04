package com.bolehunt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SprintBootApplication {
    
    private static final Logger LOG = LoggerFactory.getLogger(SprintBootApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(SprintBootApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            LOG.info("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                LOG.info("beanName: {}", beanName);
//            }
//
//        };
//    }

}
