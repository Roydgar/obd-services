package tk.roydgar.obdservices.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.roydgar.obdservices.ObdServicesApplication;

@Slf4j
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.info("Running application");
        return application.sources(ObdServicesApplication.class);
    }
} 