package by.st.Examples.Example1.Data;

import java.io.Serializable;

/**
 * Created by Dostanko_VL on 21.10.2015.
 */
public class Connection implements Serializable {	
	private static final long serialVersionUID = -2856946075794689434L;
	private String name;
    private String adress;
    private String login;
    private String password;
    //getters
    public String getName(){return name;}
    public String getAdress(){return adress;}
    public String getLogin(){return login;}
    public String getPassword(){return password;}
    //setters
    public void setName(String value){name = value;}
    public void setAdress(String value){adress = value;}
    public void setLogin(String value){login = value;}
    public void setPassword(String value){password = value;}
    //construct
    public Connection(){}
    public Connection(String name,String adress,String login,String password){
        setName(name);
        setAdress(adress);
        setLogin(login);
        setPassword(password);
    }
}
