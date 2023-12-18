package com.hh.nobidding;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class HhNonBiddingApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(HhNonBiddingApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
