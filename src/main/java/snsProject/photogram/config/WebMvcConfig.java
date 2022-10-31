package snsProject.photogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //web 설정 파일

        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**") // jsp페이지에서 /instaUpload/** 주소 패턴이 나오면 발동
                .addResourceLocations("file:///" + uploadFolder) // 위 효과 발동. "C:/workspace/springbootwork/instaUpload/"
                .setCachePeriod(60 * 10 * 6) // 캐쉬 지속시간 1시간
                .resourceChain(true) // 발동
                .addResolver(new PathResourceResolver()); // 등록
    }
}
