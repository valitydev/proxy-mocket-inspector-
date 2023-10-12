package dev.vality.proxy.mocket.inspector.handler;

import dev.vality.damsel.base.InvalidRequest;
import dev.vality.damsel.domain.RiskScore;
import dev.vality.damsel.proxy_inspector.Context;
import dev.vality.damsel.proxy_inspector.InspectorProxySrv;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Map;

@Slf4j
@Component
public class TestInspectorServerHandler implements InspectorProxySrv.Iface {

    @Override
    public RiskScore inspectPayment(Context context) throws TException {
        Map<String, String> options = context.getOptions();
        log.info("inspectPayment options {}", options);
        RiskScore riskScoreResult;
        if (options.containsKey("risk_score")) {
            riskScoreResult = EnumSet.allOf(RiskScore.class)
                    .stream()
                    .filter(riskScore -> riskScore.name().equals(options.get("risk_score")))
                    .findFirst()
                    .orElseThrow(InvalidRequest::new);
        } else {
            throw new InvalidRequest();
        }
        log.info("inspectPayment risk score {}", riskScoreResult);
        return riskScoreResult;
    }

}
