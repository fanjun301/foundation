package cn.frank.foundation.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMain {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			Class.forName("org.h2.Driver");
			/**
			 * jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>
			 * jdbc:h2:tcp://localhost/~/test
			 * jdbc:h2:tcp://dbserv:8084/~/sample
			 * jdbc:h2:tcp://localhost/mem:test
			 */
			conn  = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/dbdemo;MODE=Oracle", "sa", "");
            pstat = conn.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.TABLES ");			 
            rs = pstat.executeQuery();
            while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
