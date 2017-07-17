package com.rbkmoney.proxy.mocket.inspector.handler;

import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Map;

@Component
public class TestInspectorServerHandler implements InspectorProxySrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestInspectorServerHandler.class);

    @Override
    public RiskScore inspectPayment(Context context) throws InvalidRequest, TException {
        Map<String, String> options = context.getOptions();
        LOGGER.info("inspectPayment options {}", options);
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
        LOGGER.info("inspectPayment risk score {}", riskScoreResult);
        return riskScoreResult;
    }
}
