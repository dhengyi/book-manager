package bookmanager.config.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @Author: spider_hgyi
 * @Date: Created in 上午11:16 17-11-20.
 * @Modified By:
 * @Description:
 */
public class BookManagerWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 创建ContextLoaderListener应用上下文
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class};
    }

    // 创建DispatcherServlet应用上下文
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class};
    }

    // 将DispatcherServlet映射到/
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
