package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The main class of the project.
 * By running the main class of {@link Application} then you start the Spring Boot system
 */
//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"project"})
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);
    }

    public static void main(String[] args) {
        // Should not be used, but was only way we found to make sure user was logged in after redirection
        SecurityContextHolder.setStrategyName("MODE_GLOBAL");
        SpringApplication.run(Application.class,args);
    }

}
