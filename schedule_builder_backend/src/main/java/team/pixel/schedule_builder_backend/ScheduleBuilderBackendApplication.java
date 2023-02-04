package team.pixel.schedule_builder_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ScheduleBuilderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleBuilderBackendApplication.class, args);
    }

}
