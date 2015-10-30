package by.st.Examples.Example1.MBean;

import by.st.Examples.Example1.Data.ConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

//import com.sun.jmx.mbeanserver.MBeanSupport;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public class Connections /*extends MBeanSupport */ implements ConnectionsMBean {
    private static final transient Logger LOG = LoggerFactory.getLogger(Connections.class);

    private MBeanServer mBeanServer;
    private ObjectName objectName;
    private ConnectionsService connService;

    //setters
    public void setConnectionsService(ConnectionsService connService) {
        this.connService = connService;
    }

    //
    protected ObjectName getObjectName() throws Exception {
//        return new ObjectName("hawtio:type=Connections");
        return new ObjectName("hawtio:type=plugin,name=Connections");
    }

    public void init() {
        try {
            if (objectName == null) {
                objectName = getObjectName();
            }
            if (mBeanServer == null) {
                mBeanServer = ManagementFactory.getPlatformMBeanServer();
            }
            try {
                mBeanServer.registerMBean(this, objectName);
            } catch (InstanceAlreadyExistsException iaee) {
                // Try to remove and re-register
                LOG.info("Re-registering Social MBean");
                mBeanServer.unregisterMBean(objectName);
                mBeanServer.registerMBean(this, objectName);
            }
        } catch (Exception e) {
            LOG.warn("Exception during initialization: ", e);
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        try {
            if (objectName != null && mBeanServer != null) {
                mBeanServer.unregisterMBean(objectName);
            }
        } catch (Exception e) {
            LOG.warn("Exception unregistering mbean: ", e);
            throw new RuntimeException(e);
        }
    }

    // facade
    public String ConnectionsInfo(String password) {
        return connService.ConnectionsInfo(password);
    }
    /*public String ConnectionsInfo() throws IntrospectionException, InstanceNotFoundException, ReflectionException {
        Set<ObjectInstance> mbeans = mBeanServer.queryMBeans(null, null);
        Gson gson = new Gson();
        MBeanInfo info ;
        MBeanInfo i;
        String result = "";
        for (ObjectInstance mbean : mbeans)
        {
            info = mBeanServer.getMBeanInfo(mbean.getObjectName());
            result = result + gson.toJson(info);
        }
        return  result;
    }*/

}
