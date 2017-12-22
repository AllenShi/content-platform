package net.sjl.gatewayservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class GatewayServiceApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(GatewayServiceApplication.class).web(true).run(args);
  }

  @Bean
  public AlwaysSampler defaultSampler() {
    return new AlwaysSampler();
  }
}
