package com.rbkmoney.proxy.mocket.inspector.handler;

import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.proxy_inspector.Context;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestInspectorServerHandlerTest {

    @Spy
    private TestInspectorServerHandler handler;

    @Spy
    private Context context;

    private Map<String, String> options = new HashMap<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInspectPayment() throws TException {
        options.put("risk_score", "high");
        context.setOptions(options);
        assertEquals(RiskScore.high, handler.inspectPayment(context));

        options.put("risk_score", "low");
        context.setOptions(options);
        assertEquals(RiskScore.low, handler.inspectPayment(context));
    }

    @Test(expected = InvalidRequest.class)
    public void testInspectPaymentException() throws TException {
        options.clear();
        context.setOptions(options);
        handler.inspectPayment(context);
    }

    @Test(expected = InvalidRequest.class)
    public void testInspectPaymentExceptionWithWrongField() throws TException {
        options.put("risk_some_score", "low");
        context.setOptions(options);
        handler.inspectPayment(context);
    }

}
