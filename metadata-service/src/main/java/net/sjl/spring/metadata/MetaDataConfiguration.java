package net.sjl.spring.metadata;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.sjl.spring.metadata")
public class MetaDataConfiguration {

  @Bean
  public AlwaysSampler defaultSampler() {
    return new AlwaysSampler();
  }
}
