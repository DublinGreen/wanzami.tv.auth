package tv.wanzami.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String baseUrl;

    /**
     * get base url to be used for email sending (account activation and password reset)
     * @return
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * set base url from application properties
     * @return
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}