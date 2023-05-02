package jdbc.controller;

import java.util.Date;
import java.util.List;

import jdbc.dao.ReservasDao;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reservas;

public class ReservasController {
	
private ReservasDao reservasDao;
    
    public ReservasController() {
    	ConnectionFactory factory = new ConnectionFactory();
        this.reservasDao = new ReservasDao(factory.recuperaConexion());
    }
    public void guardar(Reservas reserva) {
    	reservasDao.guardar(reserva);
    
    }
    public List<Reservas> buscar(){
    	return this.reservasDao.buscar();
    }
    public List<Reservas> buscarId(String id) {
		return this.reservasDao.buscarId(id);
		
	}
    public void Eliminar(Integer id) {
		this.reservasDao.Eliminar(id);
	}
    public void Actualizar(Date fechaE, Date fechaS, float valor, String  formaPago, int id){
    	this.reservasDao.Actualizar(fechaE, fechaS, valor, formaPago, id);
    }

}
