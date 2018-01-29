package bookmanager.config.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Author: spider_hgyi
 * @Date: Created in 上午11:21 17-11-20.
 * @Modified By:
 * @Description:
 */
@Configuration
//启用Spring MVC
@EnableWebMvc
@ComponentScan("bookmanager.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    // 配置ViewResolver视图解析器具体解析view名字的规则
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);

        return resolver;
    }

    /**
     * @param:
     * @return:
     * @description: 配置对静态资源的处理,通过调用DefaultServletHandlerConfigurer
     * 的enable()方法,要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上,
     * 而不是DispatcherServelt本身来处理这类请求.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/resources/upload-book.properties");
        messageSource.setCacheSeconds(10);

        return messageSource;
    }
}

