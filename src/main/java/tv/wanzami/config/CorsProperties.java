package tv.wanzami.config;


import java.util.List;
import java.util.Arrays;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private String allowedOrigins;

    /**
     * Get allowed origin url from application.properties
     * @return
     */
    public List<String> getAllowedOrigins() {
        return Arrays.asList(allowedOrigins.split(","));
    }

    /**
     * Set allowed origin url to application.properties
     * @return
     */
    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}