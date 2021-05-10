package cn.zsh.config;

import cn.zsh.constants.Constants;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author luoli
 * @date 2021/5/7 10:11
 * Swagger配置组件
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    Boolean swaggerEnabled = true;

    @Value("${server.port}")
    private String port;

    @Value("${server.domain}")
    private String domain;



    @Bean("user")
    public Docket createRestApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .host(domain)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("cn.zsh"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(adminPathsAnt())
                .build()
                .securitySchemes(security())
                .securityContexts(securityContexts())
//                .globalOperationParameters(pars) // 针对单个url的验证 如果需要的话
                .pathMapping("/");
    }

    @Bean("upload")
    public Docket create1RestApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("upload")
                .host(domain)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("cn.zsh"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(frontPathsAnt()) //只监听
                .build()
                .securitySchemes(security())
                .securityContexts(securityContexts())
//                .globalOperationParameters(pars) // 针对单个url的验证 如果需要的话
                .pathMapping("/");
    }

    @Bean("public")
    public Docket create2RestApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public")
                .host(domain)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("cn.zsh"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(publicPathsAnt()) //只监听
                .build()
                .securitySchemes(security())
                .securityContexts(securityContexts())
//                .globalOperationParameters(pars) // 针对单个url的验证 如果需要的话
                .pathMapping("/");
    }

    private Predicate<String> adminPathsAnt() {
        return PathSelectors.ant("/user/**");
    }

    private Predicate<String> frontPathsAnt() {
        return PathSelectors.ant("/upload/**");
    }

    private Predicate<String> publicPathsAnt() {
        return PathSelectors.ant("/api/public/**");
    }

    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey(Constants.HEADER_AUTHORIZATION_KEY, Constants.HEADER_AUTHORIZATION_KEY, "header")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("中石化爱心助学接口文档")
                .description("ZSH")
                .termsOfServiceUrl("http://host:port")
                .version("1.0.0").build();
    }


    private List<SecurityContext> securityContexts() {
        List<SecurityContext> res = new ArrayList<>();
        res.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build());
        return res;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> res = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", Constants.HEADER_AUTHORIZATION_KEY);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        res.add(new SecurityReference(Constants.HEADER_AUTHORIZATION_KEY, authorizationScopes));
        return res;
    }
}
