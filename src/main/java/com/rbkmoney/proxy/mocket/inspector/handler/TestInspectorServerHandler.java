package com.rbkmoney.proxy.mocket.inspector.handler;

import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;

@Component
public class TestInspectorServerHandler implements InspectorProxySrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestInspectorServerHandler.class);

    private Map<String, String> options = Collections.emptyMap();

    @Override
    public RiskScore inspectPayment(Context context) throws TException {
        options = context.getOptions();
        LOGGER.info("inspectPayment options {}", options);
        RiskScore riskScoreResult = RiskScore.high;

        if (options.containsKey("risk_score")) {
            riskScoreResult = EnumSet.allOf(RiskScore.class)
                    .stream()
                    .filter(riskScore -> riskScore.name().equals(options.get("risk_score")))
                    .findFirst()
                    .orElse(RiskScore.high);
        }

        LOGGER.info("inspectPayment risk score {}", riskScoreResult);
        return riskScoreResult;
    }

}
