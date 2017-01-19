# proxy-mocket-inspector

Сервис предназначен для эмулирования работы с инспектором

Проксик реализован согласно протоколу [инспектора][1] и в качестве входящих параметров на инспекцию платежа ожидает в настройках проксика параметр `risk_score` со значением установленного из [RiskScore][2] расположенного в `domain.thrift` и возвращает объект [RiskScore][2] с присланным ему значением.
При отсутствии параметра или передаче некорректного значения возвращается `exception InvalidRequest`

Пример передаваемого параметра `domain.ProxyOptions`
```
{"risk_score":"low"}
```


### Developers

- [Anatoly Cherkasov](https://github.com/avcherkasov)


Отправка запросов на сервис:
```
http(s)//{host}:{port}/proxy/mocket/inspector
```

Конфигурация для docker-compose

```
version: '2'
services:

  proxy_mocket_inspector:
    image: dr.rbkmoney.com/rbkmoney/proxy-mocket-inspector:last
    environment:
      - SERVICE_NAME=proxy_mocket_inspector
    command: |
      -Xms64m -Xmx256m
      -jar /opt/proxy-mocket-inspector/proxy-mocket-inspector.jar
      --logging.file=/var/log/proxy-mocket-inspector/proxy-mocket-inspector.json
      --server.port=8022
    working_dir: /opt/proxy-mocket-inspector
    restart: on-failure:3
    
networks:
  default:
    driver: bridge
    driver_opts:
      com.docker.network.enable_ipv6: "true"
      com.docker.network.bridge.enable_ip_masquerade: "true"
```

[1]: https://github.com/rbkmoney/damsel/blob/master/proto/proxy_inspector.thrift
[2]: https://github.com/rbkmoney/damsel/blob/master/proto/domain.thrift#L210
