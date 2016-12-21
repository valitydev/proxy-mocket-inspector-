# proxy-test-inspector

Сервис предназначен для эмулирования работы с инспектором


### Developers

- [Anatoly Cherkasov](https://github.com/avcherkasov)


Отправка запросов на сервис:
```
http(s)//{host}:{port}/proxy/test/inspector
```

Конфигурация для docker-compose

```
version: '2'
services:

  proxy_test_inspector:
    image: dr.rbkmoney.com/rbkmoney/proxy-test-inspector:last
    environment:
      - SERVICE_NAME=proxy_test_inspector
    command: |
      -Xms64m -Xmx256m
      -jar /opt/proxy-test-inspector/proxy-test-inspector.jar
      --logging.file=/var/log/proxy-test-inspector/proxy-test-inspector.json
      --server.port=8022
    working_dir: /opt/proxy-test-inspector
    restart: on-failure:3
    
networks:
  default:
    driver: bridge
    driver_opts:
      com.docker.network.enable_ipv6: "true"
      com.docker.network.bridge.enable_ip_masquerade: "true"
```