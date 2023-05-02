package jdbc.controller;

import java.util.Date;
import java.util.List;

import jdbc.dao.HuespedesDao;
import jdbc.dao.ReservasDao;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Huespedes;
import jdbc.model.Reservas;

public class HuespedesController {

	private HuespedesDao huespedesDao;

	public HuespedesController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedesDao = new HuespedesDao(factory.recuperaConexion());
	}

	public void guardar(Huespedes cliente) {
		huespedesDao.guardar(cliente);

	}

	public List<Huespedes> listarHuespedes() {
		return this.huespedesDao.listarHuespedes();
	}

	public List<Huespedes> listarHuespedesId(String id) {
		return this.huespedesDao.buscarId(id);
	}

	public void Eliminar(Integer id) {
		this.huespedesDao.Eliminar(id);
	}

	public void Actualizar(String nombre, String apellido, Date fechaNacido, String nacionalidad, String telefono,
			int reserva_id, Integer id) {
		this.huespedesDao.Actualizar(nombre, apellido, fechaNacido, nacionalidad, telefono,reserva_id, id);

	}
}
