package JDBC_DB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connect {
	private DB_Conf conf = null;
	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//constructor
	public DB_Connect(){
		this.conf = new DB_Conf();
	}
	
	public DB_Connect(DB_Conf conf){
		if(conf != null){
			this.conf = conf;
		}
	}
	
	//set/get functions
	public Connection getConn(){
		if(this.conn != null){
			return this.conn;
		}
		return null;
	}
	
	public Statement getSt(){
		if(this.st != null){
			return this.st;
		}
		return null;
	}
	
	public PreparedStatement getPst(){
		if(this.pst != null){
			return this.pst;
		}
		return null;
	}
	
	public ResultSet getRs(){
		if(rs != null){
			return this.rs;
		}
		return null;
	}
	
	//main functions
	public boolean init(){
		boolean isSuccess = false;
		try{
			if(this.conf != null){
				Class.forName(this.conf.getJDBC_Driver());
				System.out.println(this.conf.getURL());
				this.conn = DriverManager.getConnection(this.conf.getURL(), this.conf.getUsername(), this.conf.getPassword());
				System.out.println("Database connects success.");
				isSuccess = true;
			}
		}catch(ClassNotFoundException e){
			System.err.println("Exception: Can not find dirver.");
		}catch(SQLException e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public boolean statementUpdate(String sql){
		boolean isSuccess = false;
		try{
			this.st = this.conn.createStatement();
			this.st.executeUpdate(sql);
			isSuccess = true;
		}catch(SQLException e){
			System.err.println("Exception:" + e.toString());
		}finally{
			try{
				this.st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	
	public ResultSet statementQuery(String sql){
		try{
			this.st = this.conn.createStatement();
			this.rs = this.st.executeQuery(sql);
		}catch(SQLException e){
			System.err.println("Exception:" + e.toString());			
		}finally{

		}
		return this.rs;
	}
	
	public boolean preparedStatementUpdate(String sql, String[] sqlArgs){
		boolean isSuccess = false;
		try{
			this.pst = this.conn.prepareStatement(sql);
			for(int i=0;i<sqlArgs.length;i++){
				this.pst.setString(i+1, sqlArgs[i]);
			}
			this.pst.executeUpdate();
			this.pst.clearParameters();
			isSuccess = true;
		}catch(SQLException e){
			System.err.println("Exception:" + e.toString());						
		}finally{
			try{
				this.pst.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	
	public ResultSet preparedStatementQuery(String sql, String[] sqlArgs){
		try{
			this.pst = this.conn.prepareStatement(sql);
			for(int i=0;i<sqlArgs.length;i++){
				this.pst.setString(i+1, sqlArgs[i]);
			}
			this.rs = this.pst.executeQuery();
			this.pst.clearParameters();
		}catch(SQLException e){
			System.err.println("Exception:" + e.toString());									
		}finally{
		}
		return this.rs;
	}
	
	public void close(){
		try{
			if(this.rs != null){
				this.rs.close();
				this.rs = null;
			}
			if(this.st != null){
				this.st.close();
				this.st = null;
			}
			if(this.conn != null){
				this.conn.close();
				this.conn = null;
			}
			System.out.println("Database was disconnected.");
		}catch(SQLException e){
			e.printStackTrace();			
		}
	}
}
