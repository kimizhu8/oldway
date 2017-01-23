package net.iretailer.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations={"classpath:mybatis-config.xml"})
public class IretailerRestApplication extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IretailerRestApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IretailerRestApplication.class, args);
    }
}
