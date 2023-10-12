package dev.vality.proxy.mocket.inspector.servlet;

import dev.vality.damsel.proxy_inspector.InspectorProxySrv;
import dev.vality.proxy.mocket.inspector.handler.TestInspectorServerHandler;
import dev.vality.woody.thrift.impl.http.THServiceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/proxy/mocket/inspector")
public class ProxyServlet extends GenericServlet {

    @Autowired
    private TestInspectorServerHandler handler;

    private Servlet servlet;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servlet = new THServiceBuilder().build(InspectorProxySrv.Iface.class, handler);
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        servlet.service(request, response);
    }

}
