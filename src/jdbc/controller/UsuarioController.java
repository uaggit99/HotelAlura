package jdbc.controller;

import java.util.List;

import jdbc.dao.UsuarioDao;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Usuarios;

public class UsuarioController {

	private UsuarioDao usuarioDao;

	public UsuarioController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.usuarioDao = new UsuarioDao(factory.recuperaConexion());
	}

	public List<Usuarios> validacion(String nombreUsuario, String password) {
		return usuarioDao.vaiidacion(nombreUsuario, password);
	}

	public void guardar(Usuarios usuario) {
	      usuarioDao.guardar(usuario);
	}

}
