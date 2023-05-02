package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import jdbc.model.Reservas;
import jdbc.model.Usuarios;

public class UsuarioDao {

	private Connection con;
	private static boolean var = false;

	public UsuarioDao(Connection con) {
		this.con = con;
	}

	public List<Usuarios> vaiidacion(String nombreUsuario, String password) {
		List<Usuarios> listaUsuarios = new ArrayList<>();
		try {
			String sql = "SELECT usuario,clave " + "FROM usuarios " + "WHERE usuario = ? AND clave = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
				preparedStatement.setString(1, nombreUsuario);
				preparedStatement.setString(2, password);
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getResultSet();
				if (resultSet.next()) {
					Usuarios fila = new Usuarios(resultSet.getString("usuario"), resultSet.getString("clave"));
					listaUsuarios.add(fila);

				} else {
					var=true;
					
					return listaUsuarios = null;
				}
				return listaUsuarios;

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Inténtelo más tarde.", "Error al obtener los datos.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	

	public static boolean isVar() {
		return var;
	}

	public void guardar(Usuarios usuario) {

		try {
			PreparedStatement statement;
			statement = con.prepareStatement("INSERT INTO USUARIOS (usuario,clave,correo)" + " VALUES (?, ?,? )",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				// statement.setInt(1, reserva.getId());
				statement.setString(1, usuario.getUsuario());
				statement.setString(2, usuario.getClave());
				statement.setString(3, usuario.getCorreo());
				

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						usuario.setId(resultSet.getInt(1));

					}
					JOptionPane.showInternalMessageDialog(null,
							String.format("Fue insertado el usuario  : %s ", usuario.getId()));

				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
