package com.example.zuuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.zuuldemo.filters.ErrorFilter;
import com.example.zuuldemo.filters.PostFilter;
import com.example.zuuldemo.filters.PreFilter;
import com.example.zuuldemo.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuuldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuuldemoApplication.class, args);
	}
	@Bean
	public PreFilter preFilter() {
	    return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
	    return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
	    return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
	    return new RouteFilter();
	}
	
	/*@Bean
	public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
	    UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
	    factory.addDeploymentInfoCustomizers(new UndertowDeploymentInfoCustomizer() {
	        @Override
	        public void customize(DeploymentInfo deploymentInfo) {
	            deploymentInfo.setAllowNonStandardWrappers(true);
	        }
	    });
	    return factory;
	}*/
}
