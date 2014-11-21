package JDBC_DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DB_Conf {
	
	//parameters setup
	private String JDBC_Driver = "com.mysql.jdbc.Driver";
	private String DB_TYPE = "mysql";
	private String domain = "192.168.59.103";
	private String port = "3306";
	private String useDB = "web_service_hw2";
	private String username = "root";
	private String password = "abc123";
	private String encoding = "utf-8";
	private String table = "book";
	private boolean isUnicode = true;
	
	//constructor
	public DB_Conf(){
		Properties properties = new Properties();
		String configFile = "config.properties";
		try {
			InputStream in = this.getClass().getResourceAsStream("/" + configFile);
			InputStreamReader isrProperties = new InputStreamReader(in);
		    properties.load(isrProperties);
		    in.close();
			this.domain = properties.getProperty("domain", "jdbc:mysql://localhost/default");
			this.port = properties.getProperty("port");
			this.useDB = properties.getProperty("useDB");
			this.username = properties.getProperty("username");
			this.password = properties.getProperty("password");
			this.table = properties.getProperty("table");		    
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		    return;
		} catch (IOException ex) {
		    ex.printStackTrace();
		    return;
		}
		
	}
	
	public DB_Conf(String driver, String type, String d, String port, String dbName, String usr, String pw){
		this.JDBC_Driver = driver;
		this.DB_TYPE = type;
		this.domain = d;
		this.port = port;
		this.useDB = dbName;
		this.username = usr;
		this.password = pw;
	}
	
	//set/get functions
	public void setJDBC_Driver(String driver){
		if(driver != ""){
			this.JDBC_Driver = driver;
		}
	}
	
	public String getJDBC_Driver(){
		if(this.JDBC_Driver != ""){
			return this.JDBC_Driver;
		}
		return "";
	}
	
	public void setDB_TYPE(String type){
		if(type != ""){
			this.DB_TYPE = type;
		}
	}
	
	public String getDB_TYPE(){
		if(this.DB_TYPE != ""){
			return this.DB_TYPE;
		}
		return "";
	}
	
	public void setDomain(String d){
		if(d != ""){
			this.domain = d;
		}
	}
	
	public String getDomain(){
		if(this.domain != ""){
			return this.domain;
		}
		return "";
	}
	
	public void setPort(String port){
		if(port != ""){
			this.port = port;
		}
	}
	
	public String getPort(){
		if(this.port != ""){
			return this.port;
		}
		return "";
	}
	
	public void setUseDB(String dbName){
		if(dbName != ""){
			this.useDB = dbName;
		}
	}
	
	public String getUseDB(){
		if(this.useDB != ""){
			return this.useDB;
		}
		return "";
	}
	
	public void setUsername(String usr){
		if(usr != ""){
			this.username = usr;
		}
	}
	
	public String getUsername(){
		if(this.username != ""){
			return this.username;
		}
		return "";
	}
	
	public void setPassword(String pw){
		if(pw != ""){
			this.password = pw;
		}
	}
	
	public String getPassword(){
		if(this.password != ""){
			return this.password ;
		}
		return "";
	}
	
	public void setEncoding(String encode){
		if(encode != ""){
			this.encoding = encode;
		}
	}
	
	public String getEncoding(){
		if(this.encoding != ""){
			return this.encoding;
		}
		return "";
	}
	
	public void setTable(String tb){
		if(tb != ""){
			this.table = tb;
		}
	}
	
	public String getTable(){
		if(this.table != ""){
			return this.table;
		}
		return "";
	}
	
	public void setIsUnicode(boolean flag){
		this.isUnicode = flag;
	}
	
	public boolean getUseUnicode(){
		return this.isUnicode;
	}
	
	public String getURL(){
		return "jdbc:" + this.DB_TYPE + "://" + this.domain + ":" + this.port +  "/" + this.useDB + "?" + "useUnicode=" + this.isUnicode + "&" + "characterEncoding=" + this.encoding;
	}
}
