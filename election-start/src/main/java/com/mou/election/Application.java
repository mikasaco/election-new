package com.mou.election;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 沈林强(四笠) on 2021/7/2.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/2
 */
@SpringBootApplication(scanBasePackages = {"com.mou.election"})
@MapperScan("com.mou.election.dal.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
