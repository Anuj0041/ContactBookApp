package cb.dbinfo;
import java.sql.*;
public class DBConnection
{
	private static Connection con;
	
	public static Connection openConnection()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//MYSQL
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db","root","root");
			//sub protocol://name or IP address of the machine where database has been installed
			//port number of database
			//database name
			//database userId 
			//database password
			
		}
		catch(SQLException | ClassNotFoundException sce) {
			sce.printStackTrace();
		}
		return con;
	}
//public static void main(String[] args) {
	//Connection c=DBConnection.openConnection();
	//System.out.println(c);
//}
}
