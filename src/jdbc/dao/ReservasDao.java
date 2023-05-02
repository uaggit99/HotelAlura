package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import jdbc.model.Reservas;

public class ReservasDao {

	private Connection con;

	public ReservasDao(Connection con) {
		this.con = con;
	}

	public void guardar(Reservas reserva) {

		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO RESERVAS (FechaEntrada,FechaSalida,valor,FormaPago)" + " VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				// statement.setInt(1, reserva.getId());
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setFloat(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));

					}
					// System.out.println("Fue insertada la reserva: "+numeroid);
					System.out.println(String.format("Fue insertada la reserva: %s ", reserva.getId()));

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Reservas> buscar() {
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT Id, FechaEntrada, FechaSalida, Valor, FormaPago FROM reservas";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reservas> buscarId(String id) {
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {

			String sql = "SELECT Id, FechaEntrada, FechaSalida, Valor, FormaPago FROM reservas WHERE Id = ?";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void Eliminar(Integer id) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM reservas WHERE Id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void Actualizar(Date fechaE, Date fechaS, float valor, String formaPago, int id) {
		try (PreparedStatement stm = con.prepareStatement(
				"UPDATE reservas SET FechaEntrada = ?, FechaSalida = ?, Valor = ?, FormaPago = ? WHERE Id = ?")) {
			stm.setDate(1, (java.sql.Date) fechaE);
			stm.setDate(2, (java.sql.Date) fechaS);
			stm.setFloat(3, valor);
			stm.setString(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEnReserva(List<Reservas> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reservas produto = new Reservas(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getFloat(4),
						rst.getString(5));

				reservas.add(produto);
			}
		}
	}

}
