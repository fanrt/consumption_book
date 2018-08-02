package cn.fanrt.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-06-25 15:39
 **/
public class ConsumptionBookWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        registerFilter(container);
    }

    private void registerFilter(ServletContext container) {
        this.initializeEncodeingFilter(container);
        this.initializeSecurityFilter(container);
    }

    private void initializeEncodeingFilter(ServletContext container) {
        FilterRegistration.Dynamic filterRegistration = container.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration.setAsyncSupported(true);
    }

    private void initializeSecurityFilter(ServletContext container) {
        FilterRegistration.Dynamic filterRegistration = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }
}
