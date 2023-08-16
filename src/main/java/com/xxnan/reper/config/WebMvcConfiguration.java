package com.xxnan.reper.config;

import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.json.JacksonObjectMapper;
import com.xxnan.reper.interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/aaa")
                .excludePathPatterns("/admin/login/status");
    }

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization","Token","x_requested_with")
                .allowCredentials(true);
    }

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Reper项目接口文档")
                .version("2.0")
                .description("Reper项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.xxnan.reper.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        SWAGGER
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

//        本地资源路径处理
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations(PathConstant.AVATOR_IMAGES_PATH);
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations(PathConstant.SINGER_PIC_PATH);
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations(PathConstant.SONG_PIC_PATH);
        registry.addResourceHandler("/song/**")
                .addResourceLocations(PathConstant.SONG_PATH);
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations(PathConstant.SONGLIST_PIC_PATH);
        registry.addResourceHandler("/img/swiper/**")
                .addResourceLocations(PathConstant.BANNER_PIC_PATH);
    }

    /**
     * 扩展SpringMVC消息转化器。对后端返回给前端的数据进行统一的处理
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,converter);
    }
}
