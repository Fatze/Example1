package by.st.Examples.Example1.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public class ConnectionsFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionsFactory.class);
    private Connections connections;

    public Connections getInstance(String password) {
    	LOG.debug("new instance with param: {}",password);
        if (connections != null)
            if (connections.getPassword().compareTo(password) != 0)
                connections = null;
        if (connections == null) {
            connections = new Connections();
            initInstance(connections,password);
        }
        return connections;
    }

    public void initInstance(Connections connections) {
    	
        initInstance(connections,"password");
    }

    public void initInstance(Connections connections,String password) {
        connections.setPassword(password);
        for (Integer i = 0; i < 10; i++) {
            //postfix
            String si = i.toString();
            connections.add(new Connection("name" + si,"adress"+si,"login"+si,password+"-"+si));
        }
    }

}
