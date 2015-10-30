package by.st.Examples.Example1.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public class Connections extends ArrayList<Connection> implements Serializable {
	private static final long serialVersionUID = 3053616537543296301L;
	private String password;
    public String getPassword(){return password;};
    public void setPassword(String value){password = value;};
}
