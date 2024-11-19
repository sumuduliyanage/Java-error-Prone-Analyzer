@Configuration
public class WebConfig implements WebMvcConfigurer {
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META- 
 INF/resources/webjars/");
   }
}
