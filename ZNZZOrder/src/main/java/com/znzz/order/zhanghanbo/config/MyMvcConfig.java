package com.znzz.order.zhanghanbo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        //registry.addViewController("/atguigu").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/main.html").setViewName("frame");
        registry.addViewController("/modifyPassword").setViewName("tgls/modify_password");
        registry.addViewController("/preOrderAdd").setViewName("tgls/preOrder/preOrder_add");


        //registry.addViewController("/test1").setViewName("tgls/returnOrder/returnOrder_list");
        registry.addViewController("/test2").setViewName("tgls/goodsManage/goods_list");
        registry.addViewController("/goodsUpdate").setViewName("tgls/goodsManage/goods_update");
        registry.addViewController("/specificationsList").setViewName("tgls/goodsManage/specifications_list");
        registry.addViewController("/frameindex").setViewName("tgls/index");

    }

  /*
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }*/



}
