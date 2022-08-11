package Base_de_datos;

import java.sql.*;

public class BD {

	private static final String url = "jdbc:postgresql://localhost/tpDIED";
	private static final String user = "postgres";
	private static final String password = "pgelefante";

	// -----------------------------CREATES-----------------------------

	private static final String CREATE_ENUM_TIPO_SERVICIO = "DROP TYPE IF EXISTS TipoServicio CASCADE;"
			+ "CREATE TYPE TipoServicio AS ENUM('Wifi', 'AirConditioner');";

	private static final String ELIMINAR_SCHEMA = "DROP SCHEMA public CASCADE;" + "CREATE SCHEMA public;"
			+ "SET search_path TO public;";

	private static final String CREATE_TABLA_PARADA = "CREATE TABLE IF NOT EXISTS parada ("
			+ "nroParada INT PRIMARY KEY," + "numeroCalle INT," + "nombreCalle VARCHAR(40)," + "estado BOOLEAN" + ");";

	private static final String CREATE_TABLA_BOLETO = "CREATE TABLE IF NOT EXISTS boleto (" + "id INT PRIMARY KEY,"
			+ "MONTO_POR_KM FLOAT NOT NULL," + "precio FLOAT NOT NULL," + "fechaViaje DATE,"
			+ "id_bus INT REFERENCES Bus(numero)," + "id_linea INT REFERENCES Linea(id)" + ");";

	private static final String CREATE_TABLA_BUS = "CREATE TABLE IF NOT EXISTS bus (" + "numero INT PRIMARY KEY,"
			+ "id_linea INT REFERENCES Linea(id)," + "parada_inicio INT NOT NULL REFERENCES Parada(nroParada),"
			+ "parada_fin INT NOT NULL REFERENCES Parada(nroParada),"
			+ "parada_auxiliar INT REFERENCES Parada(nroParada)" + ");";

	private static final String CREATE_TABLA_LINEA = "CREATE TABLE IF NOT EXISTS linea (" + "id INT PRIMARY KEY,"
			+ "color VARCHAR(40)," + "nombre VARCHAR(40)," + "capacidad INT," + "velocidad FLOAT" + ");";

	private static final String CREATE_TABLA_LINEA_ECONOMICA = "CREATE TABLE IF NOT EXISTS linea_economica ("
			+ "id INT REFERENCES Linea(id)," + "porcentaje FLOAT CHECK (porcentaje > 0 AND porcentaje <= 4.0),"
			+ "COSTO_ECONOM FLOAT," + "PRIMARY KEY (id)" + ");";

	private static final String CREATE_TABLA_LINEA_SUPERIOR = "CREATE TABLE IF NOT EXISTS linea_superior ("
			+ "id INT REFERENCES Linea(id)," + "tipo_servicio tiposervicio," + "COSTO_WIFI FLOAT," + "COSTO_AC FLOAT,"
			+ "COSTO_SUP FLOAT" + ");";

	private static final String CREATE_TABLA_CALLE = "CREATE TABLE IF NOT EXISTS calle (" + "id INT PRIMARY KEY,"
			+ "parada_inicial INT REFERENCES Parada(nroParada)," + "parada_final INT REFERENCES Parada(nroParada),"
			+ "longitud FLOAT NOT NULL" + ");";

	private static final String CREATE_TABLA_INCIDENTE = "CREATE TABLE IF NOT EXISTS incidente ("
			+ "id INT PRIMARY KEY," + "nroParada INT REFERENCES Parada(nroParada)," + "fecha_inicio DATE NOT NULL,"
			+ "fecha_fin DATE," + "descripcion VARCHAR(512)," + "activa BOOLEAN NOT NULL" + ");";

	private static final String CREATE_TABLA_RUTA = "CREATE TABLE IF NOT EXISTS ruta (" + "id INT PRIMARY KEY,"
			+ "tiempo_por_km FLOAT," + "parada_inicial INT REFERENCES Parada(nroParada),"
			+ "parada_final INT REFERENCES Parada(nroParada)," + "cantidad_paradas INT," + "tiempo FLOAT,"
			+ "distancia FLOAT" + ");";

	private static final String CREATE_TABLA_PARADA_RUTA = "CREATE TABLE IF NOT EXISTS parada_ruta ("
			+ "id_parada INT REFERENCES Parada(nroParada)," + "id_ruta INT REFERENCES Ruta(id)" + ");";
	// -----------------------------------------------------------------

	// -----------------------------INSERTS-----------------------------

	private static final String INSERT_TABLA_PARADA = "INSERT INTO parada VALUES" + "(1, 150, 'Zeballos', true),"
			+ "(2, 200, 'Urquiza', true)," + "(3, 300, 'Brown', false)," + "(4, 350, 'Suipacha', true),"
			+ "(5, 230, 'Paz', true)," + "(6, 130, 'Riobamba', true)," + "(7, 850, 'Yrigoyen', true),"
			+ "(8, 550, 'Hernandarias', false)" + ";";

	private static final String INSERT_TABLA_CALLE = "INSERT INTO calle VALUES" + "(1, 1, 2, 1)," + "(2, 2, 3, 2),"
			+ "(3, 3, 4, 9)," + "(4, 4, 5, 3.1)," + "(5, 5, 6, 4)," + "(6, 6, 7, 2)," + "(7, 7, 8, 3.5)" + ";";

	private static final String INSERT_TABLA_BOLETO = "INSERT INTO boleto VALUES" + "(1, 20, 200, '01/02/2022', 1, 1),"
			+ "(2, 22, 300, '17/03/2022', 2, 1)," + "(3, 24, 400, '21/04/2022', 3, 2)" + ";";

	private static final String INSERT_TABLA_BUS = "INSERT INTO bus VALUES" + "(1, 1, 2, 8, null),"
			+ "(2, 1, 2, 8, null)," + "(3, 2, 1, 4, null)" + ";";
	private static final String INSERT_TABLA_INCIDENTE = "INSERT INTO incidente VALUES"
			+ "(1, 3, '01/02/2022', null, '', true)," + "(2, 8, '15/03/2022', '19/03/2022', '', true),"
			+ "(3, 6, '01/01/2022', '10/01/2022', '', false)" + ";";
	private static final String INSERT_TABLA_LINEA = "INSERT INTO linea VALUES" + "(1, 'Azul', 'Linea1', 30, 20),"
			+ "(2, 'Verde', 'Linea2', 40, 25)" + ";";

	private static final String INSERT_TABLA_LINEA_ECONOMICA = "INSERT INTO linea_economica VALUES" + "(1, 2.5, 0.02)"
			+ ";";

	private static final String INSERT_TABLA_LINEA_SUPERIOR = "INSERT INTO linea_superior VALUES"
			+ "(1, 'Wifi', 0.5, 0.5, 0.1)" + ";";

	private static final String INSERT_TABLA_RUTA = "INSERT INTO ruta VALUES" + "(1, 5, 3, 6, 4, 20, 15)" + ";";

	private static final String INSERT_TABLA_PARADAS_RUTA = "INSERT INTO parada_ruta VALUES" + "(3, 1)," + "(4, 1),"
			+ "(5, 1)," + "(6, 1)" + ";";

	// ----------------------------------------------------------------

	public static Connection connect() {

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Class.forName("org.postgresql.Driver");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static void inicializar() {

		try {
			Connection conn = BD.connect();
			BD.crearTablas(conn);
			BD.inicializarTablas(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void crearTablas(Connection conn) {

		Statement st1 = null;
		Statement st2 = null;
		Statement st3 = null;
		Statement st4 = null;
		Statement st5 = null;
		Statement st6 = null;
		Statement st7 = null;
		Statement st8 = null;
		Statement st9 = null;
		Statement st10 = null;
		Statement st11 = null;

		try {
			st1 = conn.createStatement();
			st2 = conn.createStatement();
			st3 = conn.createStatement();
			st4 = conn.createStatement();
			st5 = conn.createStatement();
			st6 = conn.createStatement();
			st7 = conn.createStatement();
			st8 = conn.createStatement();
			st9 = conn.createStatement();
			st10 = conn.createStatement();
			st11 = conn.createStatement();

			boolean tipoServicioEnum = st1.execute(CREATE_ENUM_TIPO_SERVICIO);
			boolean tablaParada = st2.execute(CREATE_TABLA_PARADA);
			boolean tablaLinea = st3.execute(CREATE_TABLA_LINEA);
			boolean tablaBus = st4.execute(CREATE_TABLA_BUS);
			boolean tablaBoleto = st5.execute(CREATE_TABLA_BOLETO);
			boolean tablaLineaEconom = st6.execute(CREATE_TABLA_LINEA_ECONOMICA);
			boolean tablaLineaSup = st7.execute(CREATE_TABLA_LINEA_SUPERIOR);
			boolean tablaIncidente = st8.execute(CREATE_TABLA_INCIDENTE);
			boolean tablaRuta = st9.execute(CREATE_TABLA_RUTA);
			boolean tablaRutasParada = st10.execute(CREATE_TABLA_PARADA_RUTA);
			boolean tablaCalle = st11.execute(CREATE_TABLA_CALLE);

			st1.close();
			st2.close();
			st3.close();
			st4.close();
			st5.close();
			st6.close();
			st7.close();
			st8.close();
			st9.close();
			st10.close();
			st11.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st1.close();
				st2.close();
				st3.close();
				st4.close();
				st5.close();
				st6.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void inicializarTablas(Connection conn) {

		Statement st1;
		Statement st2;
		Statement st3;
		Statement st4;
		Statement st5;
		Statement st6;
		Statement st7;
		Statement st8;
		Statement st9;
		Statement st10;

		try {
			st1 = conn.createStatement();
			st2 = conn.createStatement();
			st3 = conn.createStatement();
			st4 = conn.createStatement();
			st5 = conn.createStatement();
			st6 = conn.createStatement();
			st7 = conn.createStatement();
			st8 = conn.createStatement();
			st9 = conn.createStatement();
			st10 = conn.createStatement();

			boolean insertParada = st1.execute(INSERT_TABLA_PARADA);
			boolean insertLinea = st5.execute(INSERT_TABLA_LINEA);
			boolean insertLineaEco = st6.execute(INSERT_TABLA_LINEA_ECONOMICA);
			boolean insertLineaSup = st7.execute(INSERT_TABLA_LINEA_SUPERIOR);
			boolean insertBus = st4.execute(INSERT_TABLA_BUS);
			boolean insertBoleto = st2.execute(INSERT_TABLA_BOLETO);
			boolean insertCalle = st3.execute(INSERT_TABLA_CALLE);
			boolean insertIncidente = st8.execute(INSERT_TABLA_INCIDENTE);
			boolean insertRuta = st9.execute(INSERT_TABLA_RUTA);
			boolean insertParadaRuta = st10.execute(INSERT_TABLA_PARADAS_RUTA);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void eliminar() {

		try {
			Connection conn = BD.connect();
			BD.eliminarSchema(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void eliminarSchema(Connection conn) {

		Statement st1 = null;

		try {
			st1 = conn.createStatement();
			st1.execute(ELIMINAR_SCHEMA);
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
//				st1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
