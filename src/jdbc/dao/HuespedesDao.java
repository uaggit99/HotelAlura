package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import jdbc.model.Huespedes;
import jdbc.model.Reservas;

public class HuespedesDao {
	private Connection con;

	public HuespedesDao(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes cliente) {

		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO Huespedes (Nombre,Apellido,FechaNacimiento,Nacionalidad,Telefono,Id_Reserva)"
							+ " VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				// statement.setInt(1, reserva.getId());
				statement.setString(1, cliente.getNombre());
				statement.setString(2, cliente.getApellido());
				statement.setDate(3, cliente.getFechaNacido());
				statement.setString(4, cliente.getNacionalidad());
				statement.setString(5, cliente.getTelefono());
				statement.setInt(6, cliente.getReserva_id());
				;
				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						cliente.setId(resultSet.getInt(1));

					}
					// System.out.println("Fue insertada la reserva: "+numeroid);
					System.out.println(String.format("Fue insertada la reserva: %s ", cliente.getId()));

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Huespedes> listarHuespedes() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT Id, Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, Id_Reserva FROM huespedes";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEnHuesped(List<Huespedes> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huespedes huespedes = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getString(6), rst.getInt(7));
				reservas.add(huespedes);
			}
		}
	}

	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {

			String sql = "SELECT Id, Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, Id_Reserva FROM huespedes WHERE Id_Reserva=?";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void Eliminar(Integer id) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM huespedes WHERE Id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void Actualizar(String nombre, String apellido, Date fechaNacido, String nacionalidad, String telefono,
			 Integer id) {
		try (PreparedStatement stm = con.prepareStatement(
				"UPDATE huespedes SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, Nacionalidad = ?, Telefono = ? WHERE Id = ?")) {
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, (java.sql.Date) fechaNacido);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			//stm.setInt(6, reserva_id);
			stm.setInt(6, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
