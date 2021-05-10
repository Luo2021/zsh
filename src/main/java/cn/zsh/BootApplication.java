package cn.zsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author luoli
 * @date 2021/3/30 10:10
 */
@EnableScheduling
@EnableAsync
@SpringBootApplication
@MapperScan("cn.zsh.mapper")
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        System.out.println("启动成功！");
        // 启动本地服务，然后hold住本地服务
        synchronized (BootApplication.class) {
            while (true) {
                try {
                    System.out.println("-----------------------------------");
                    BootApplication.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
