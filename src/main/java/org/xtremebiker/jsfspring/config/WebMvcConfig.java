package org.xtremebiker.jsfspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index.xhtml");
    }

    @Override
    public Validator getValidator()
    {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver()
    {
        return null;
    }
}
