package com.rbkmoney.proxy.test.inspector.handler;

import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.proxy_inspector.Context;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestInspectorServerHandlerTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"low", RiskScore.low, },
                        {"high", RiskScore.high},
                        {"", RiskScore.high}
                }
        );
    }

    @Spy
    private TestInspectorServerHandler handler;

    @Spy
    private Context context;

    private Map<String, String> options = new HashMap<>();

    private String input;

    private RiskScore expected;

    public TestInspectorServerHandlerTest(String input, RiskScore expected) {
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInspectPayment() throws TException {
        options.put("risk_score", input);
        context.setOptions(options);
        assertEquals(expected, handler.inspectPayment(context));
    }

}
