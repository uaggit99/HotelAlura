package jdbc.model;

import java.sql.Date;

public class Reservas {
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float valor;
	private String formaPago;

	public Reservas(Date date, Date date2, float val, String tipopago) {
		this.fechaEntrada = date;
		this.fechaSalida = date2;
		this.valor = val;
		this.formaPago = tipopago;
	}
	

	public Reservas(int id, Date fechaEntrada, Date fechaSalida, float valor, String formaPago) {
		
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	public Reservas(Date date, Date date2, float val,  String tipopago, int id) {
		this.fechaEntrada = date;
		this.fechaSalida = date2;
		this.valor = val;
		this.formaPago = tipopago;
		this.id=id;
	}
	


	public int getId() {
		return id;

	}
	

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	

	@Override
	public String toString() {
		return "Reservas [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "]";
	}

}
