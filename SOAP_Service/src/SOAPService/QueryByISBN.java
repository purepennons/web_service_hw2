package SOAPService;

import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC_DB.DB_Conf;
import JDBC_DB.DB_Connect;

public class QueryByISBN {
	public String queryISBN(String isbn) throws SQLException{
		if(isbn.length() != 13) {
			return "{'status': 'error', 'result': 'wrong args'}";
		}else {
			DB_Conf dBconf = new DB_Conf();
			DB_Connect db = new DB_Connect();
			String sql = "SELECT * FROM " + dBconf.getTable() + " WHERE isbn = " + isbn;
			System.out.println(sql);
			String resultJSON = null;
			String returnJSON = null;
			db.init();
			
			ResultSet rs = db.statementQuery(sql);
						
			while(rs.next()){
				int id = rs.getInt("Id");
				String title = rs.getString("title");
				String queryIsbn = rs.getString("isbn");
				String author = rs.getString("author");
				String queryAbstract = rs.getString("abstract");
				resultJSON = "{" +
								"'Id': " + "'" + id + "', " +
								"'title': " + "'" + title + "', " +
								"'isbn': " + "'" + queryIsbn + "', " +
								"'author':" + "'" + author + "', " +
								"'abstract':" + "'" + queryAbstract + "'" +
							 "}";

			}
			
			db.close();
			returnJSON = "{'status': 'success', " + "'result': " + "[" + resultJSON + "]}";
			return returnJSON;					
		}
	}
}
