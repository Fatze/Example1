package by.st.Examples.Example1.MBean;

import by.st.Examples.Example1.Data.ConnectionsFactory;
import by.st.Examples.Example1.Data.ConnectionsService;
import io.hawt.web.plugin.HawtioPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PluginContextListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(PluginContextListener.class);

    private ServletContext context;

    HawtioPlugin plugin = null;
    Connections connections;
    ConnectionsFactory cf;
    ConnectionsService cs;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        context = servletContextEvent.getServletContext();

        plugin = new HawtioPlugin();
        plugin.setContext((String) context.getInitParameter("plugin-context"));
        plugin.setName(context.getInitParameter("plugin-name"));
        plugin.setScripts(context.getInitParameter("plugin-scripts"));
        plugin.setDomain(null);
        plugin.init();

        initMbean();

        LOG.info("Initialized {} plugin", plugin.getName());
    }

    public void initMbean() {
        cf = new ConnectionsFactory();

        cs = new ConnectionsService();
        cs.seConnectionsFactory(cf);

        connections = new Connections();
        connections.setConnectionsService(cs);
        connections.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        plugin.destroy();
        connections.destroy();
        LOG.info("Destroyed {} plugin", plugin.getName());
    }
}
