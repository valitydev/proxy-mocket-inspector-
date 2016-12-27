package com.rbkmoney.proxy.mocket.inspector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.rbkmoney.proxy.mocket.inspector"})
public class ProxyMocketInspectorApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProxyMocketInspectorApplication.class, args);
    }
}
