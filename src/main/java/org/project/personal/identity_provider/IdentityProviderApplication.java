package org.project.personal.identity_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class IdentityProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityProviderApplication.class, args);
    }

}
