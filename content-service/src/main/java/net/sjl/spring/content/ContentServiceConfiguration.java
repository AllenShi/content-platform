package net.sjl.spring.content;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.sjl.spring.content")
public class ContentServiceConfiguration {
  
  @Bean
  public AlwaysSampler defaultSampler() {
    return new AlwaysSampler();
  }
}
