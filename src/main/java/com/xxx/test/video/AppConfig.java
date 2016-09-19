package com.xxx.test.video;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.xxx.test.video.*")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/video/*.html").addResourceLocations("/WEB-INF/**/*.html");
		registry.addResourceHandler("/video/media/**").addResourceLocations("/WEB-INF/media/*.*");
		registry.addResourceHandler("/video/images/**").addResourceLocations("/WEB-INF/media/*.*");
	}

}
