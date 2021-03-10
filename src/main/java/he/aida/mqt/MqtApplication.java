package he.aida.mqt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MqtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqtApplication.class, args);
    }

}
