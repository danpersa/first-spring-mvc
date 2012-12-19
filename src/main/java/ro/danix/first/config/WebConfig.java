/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.danix.first.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import ro.danix.first.config.ApplicationConfig;
import ro.danix.first.controller.converter.UserConverter;
import ro.danix.first.model.service.UserService;

/**
 * Spring MVC Java-based configuration.
 *
 * <p>Extends directly from WebMvcConfigurationSupport in order to plug in
 * sub-classes of Spring MVC infrastructure components like {@code RequestMappingHandlerMapping},
 * {@code RequestMappingHandlerAdapter}, etc.
 *
 * <p>Typically extending from {@link WebMvcConfigurerAdapter} and adding
 * {@code @EnableWebMvc} is sufficient.
 *
 */
//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

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
    public InternalResourceViewResolver javascriptViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setContentType("text/javascript");
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".js.jsp");
        resolver.setOrder(1);
        return resolver;
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
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views");
        freeMarkerConfigurer.setDefaultEncoding("ISO-8859-1");
        return freeMarkerConfigurer;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        Map<String, String> mediaTypes = new HashMap<String, String>();
        mediaTypes.put("html", "text/html");
        mediaTypes.put("json", "application/json");
        mediaTypes.put("js", "text/javascript");
        contentNegotiatingViewResolver.setMediaTypes(mediaTypes);
        contentNegotiatingViewResolver.setDefaultContentType(MediaType.TEXT_HTML);

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        viewResolvers.add(viewResolver());
        viewResolvers.add(javascriptViewResolver());
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
}
