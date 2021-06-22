package com.meli.prizedraw.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class DatabaseConfiguration {
  @Bean
  public Jackson2RepositoryPopulatorFactoryBean populator() {
    Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();

    factory.setResources(new Resource[]{
      new ClassPathResource("database.json")
    });

    return factory;
  }
}
