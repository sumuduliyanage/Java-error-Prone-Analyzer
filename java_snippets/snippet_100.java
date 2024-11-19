 @Configuration
 public class MyConfig {
     @Bean
     public RestClient restClient(RetryLoadBalancerInterceptor loadBalancerInterceptor) {
         return RestClient.builder()
               .requestInterceptor(loadBalancerInterceptor)
               .build();
     }
 }
