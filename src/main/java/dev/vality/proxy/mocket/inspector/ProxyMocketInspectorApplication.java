package dev.vality.proxy.mocket.inspector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ProxyMocketInspectorApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyMocketInspectorApplication.class, args);
    }

}
