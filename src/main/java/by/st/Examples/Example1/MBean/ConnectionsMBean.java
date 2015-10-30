package by.st.Examples.Example1.MBean;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.ReflectionException;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public interface ConnectionsMBean {
    /* Attributes */

    /* Operations */
    String ConnectionsInfo(String password) throws IntrospectionException, InstanceNotFoundException, ReflectionException;
}
