package by.st.Examples.Example1.Data;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public class ConnectionsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionsService.class);
    protected ConnectionsFactory cf;
    private Connections connections;

    //setters
    private Connections getConnectionsInstance(String password) {
        return cf.getInstance(password);
    }

    public void seConnectionsFactory(ConnectionsFactory cf) {
        this.cf = cf;
    }

    //logic
    public String ConnectionsInfo() {
        connections = getConnectionsInstance("password");
        Gson gson = new Gson();
        String connectionsJSON = gson.toJson(connections);

        LOGGER.debug(connectionsJSON);
        return connectionsJSON;
    }

    public String ConnectionsInfo(String password) {
        connections = getConnectionsInstance(password);
        Gson gson = new Gson();
        String connectionsJSON = gson.toJson(connections);

        LOGGER.debug(connectionsJSON);
        return connectionsJSON;
    }

}
