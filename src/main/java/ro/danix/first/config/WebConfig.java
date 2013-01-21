package ro.danix.first.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import ro.danix.first.controller.converter.UserConverter;
import ro.danix.first.model.service.user.UserService;

/**
 * Spring MVC Java-based configuration.
 *
 * <p>Extends directly from WebMvcConfigurationSupport in order to plug in
 * sub-classes of Spring MVC infrastructure components like {@code RequestMappingHandlerMapping},
 * {@code RequestMappingHandlerAdapter}, etc.
 *
 * <p>Typically extending from {@link WebMvcConfigurerAdapter} and adding  {@code
 *
 * @EnableWebMvc} is sufficient.
 *
 */
@Configuration
@ComponentScan(basePackages = {"ro.danix.first.controller", "ro.danix.first.model.config"})
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter;

    @Autowired
    StringHttpMessageConverter stringHttpMessageConverter;

    @Autowired
    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private UserService userService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login").setViewName("login");
    }

//    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setCache(false);
        resolver.setSuffix(".xhtml");
        resolver.setContentType("text/html");
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        return resolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        Map<String, String> mediaTypes = new HashMap<String, String>();
        mediaTypes.put("plain", MediaType.TEXT_PLAIN_VALUE);
        mediaTypes.put("json", MediaType.APPLICATION_JSON_VALUE);
        mediaTypes.put("xml", MediaType.APPLICATION_XML_VALUE);
        mediaTypes.put("html", MediaType.TEXT_HTML_VALUE);
        contentNegotiatingViewResolver.setMediaTypes(mediaTypes);
        contentNegotiatingViewResolver.setDefaultContentType(MediaType.TEXT_PLAIN);

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        contentNegotiatingViewResolver.setViewResolvers(viewResolvers);

        List<View> defaultViews = new ArrayList<View>();
        defaultViews.add(new MappingJacksonJsonView());
        contentNegotiatingViewResolver.setDefaultViews(defaultViews);

        return contentNegotiatingViewResolver;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // Configuration for CRUD controller
    // See ~.crudcontroller
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        UserConverter userConverter = new UserConverter(userService);
        registry.addConverter(userConverter);
    }

    // Configuration for the interceptor logging HandlerMethod information
    // See ~.handlermethodinterceptor
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggingHandlerMethodInterceptor());
    }

    // Configuration for custom @RequestMapping condition
    // See ~.requestcondition package
//    @Override
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new ExtendedRequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(getInterceptors());
//        return handlerMapping;
//    }
    // Configuration for "global" @ExceptionHandler methods
    // See ~.exceptionhandler package
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        ExtendedExceptionHandlerExceptionResolver customResolver = new ExtendedExceptionHandlerExceptionResolver();
//        customResolver.setExceptionHandler(new GlobalExceptionHandler());
//        customResolver.setMessageConverters(getMessageConverters());
//        customResolver.afterPropertiesSet();
//
//        exceptionResolvers.add(customResolver);
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJacksonHttpMessageConverter);
        converters.add(stringHttpMessageConverter);
        converters.add(byteArrayHttpMessageConverter);
    }
}
