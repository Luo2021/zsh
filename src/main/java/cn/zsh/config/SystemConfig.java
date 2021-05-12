package cn.zsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author luoli
 * @date 2021/5/12 17:22
 */
@Component
@ConfigurationProperties(prefix = "system.config")
public class SystemConfig {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
