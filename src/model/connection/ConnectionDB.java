package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {

/* conexão com banco de dados mySQL através do driver JDBC - Connector/J:
 */ 
	// declaração "static final" define variável imutável.
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	// caminho, porta e nome da base de dados que será conectada.
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud";

	// endereço do driver Connector/J.
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	public static Connection createConnectionToMySQL() throws Exception {
		// indica a classe do driver para o JVM.
		Class.forName(DRIVER_NAME);

		// método de conexão com o banco de dados através do driver.
		Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return conn;
	}
	
/* conexão com banco de dados Access através do driver JDBC - UCanAccess:
 */
	private static final String DATABASE_URL_ACCESS = "jdbc:ucanaccess://C:/Users/"
	+ "Remakker/Documents/Repositórios/exemplosJava/exemplosJava-ArquiteturaMVC/Database1.mdb";
	private static final String DRIVER_NAME_ACCESS = "net.ucanaccess.jdbc.UcanaccessDriver";
	
	public static Connection createConnectionToAccess() throws Exception
	{
		Class.forName(DRIVER_NAME_ACCESS);
		Connection conx = DriverManager.getConnection(DATABASE_URL_ACCESS);
		return conx;
	}
	
	public static void main(String[] args) throws Exception {

/* teste de validade da conexão com banco de dados mySQL:
 */ 
		// teste de validade da conexão.
		System.out.println("Iniciando teste ConnectionFactory.");
		Connection conx = createConnectionToMySQL();
		if (conx != null) {
			System.out.println("Conexão mySQL estabelecida!");
			
			// teste de funcionamento da conexão.
			Statement st = conx.createStatement();
			ResultSet resu = st.executeQuery("SELECT * FROM CONTATOS");
			System.out.println("Exibindo de 'contatos': nome -> idade.");
			while (resu.next()) {
				System.out.println(resu.getString("NOME") + " -> " + resu.getInt("IDADE"));
			}
			st.close();
			conx.close();
			System.out.println("Fim da conexão mySQL.");
		} 
		
/* teste de validade da conexão com banco de dados Access:
*/
		Connection connect = createConnectionToAccess();
		if (connect != null) {
			System.out.println("Conexão Access estabelecida!");
			Statement stm = connect.createStatement();

		/* query CREATE:
			PreparedStatement pdst = connect.prepareStatement("INSERT INTO FRUIT "+
			                          		"(FRUTA, QUANTIDADE) VALUES (?, ?)");
			pdst.setString(1, "Abacaxi");
			pdst.setInt(2, 700);
			pdst.execute();
			pdst.close();
*/
		// query READ:
			ResultSet rset =  stm.executeQuery("SELECT * FROM FRUIT");
			while(rset.next()) {
				System.out.println(rset.getString("FRUTA") + " : " 
									+ rset.getInt("QUANTIDADE"));
			}
			stm.close();
			
		/* query DELETE:
			PreparedStatement pds = conx.prepareStatement(
                    "DELETE FROM FRUIT WHERE FRUTA = ?");
			pds.setString(1, "Abacaxi");
			pds.execute();
			pds.close();
*/			
			connect.close();
			System.out.println("Fim da conexão Access.");
		}
	}
}