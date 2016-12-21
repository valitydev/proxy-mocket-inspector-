package com.rbkmoney.proxy.test.inspector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.rbkmoney.proxy.test.inspector"})
public class ProxyTestInspectorApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProxyTestInspectorApplication.class, args);
    }
}
