package jdbc.model;

import java.sql.Date;

public class Huespedes {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacido;
	private String nacionalidad;
	private String telefono;
	private int reserva_id;

	public Huespedes() {

	}

	public Huespedes(Integer id, String nombre, String apellido, Date fechaNacido, String nacionalidad, String telefono,
			Integer reserva_id) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacido = fechaNacido;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reserva_id = reserva_id;
	}
	

	public Huespedes(String nombre, String apellido, Date fechaNacido, String nacionalidad, String telefono,
			int reserva_id) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacido = fechaNacido;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reserva_id = reserva_id;
	}
	public Huespedes( String nombre, String apellido, Date fechaNacido, String nacionalidad, String telefono,
		int reserva_id, Integer id) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacido = fechaNacido;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reserva_id = reserva_id;
	}
	
	
	

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacido() {
		return fechaNacido;
	}

	public void setFechaNacido(Date fechaNacido) {
		this.fechaNacido = fechaNacido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	

	public int getReserva_id() {
		return reserva_id;
	}

	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
	}

	@Override
	public String toString() {
		return "Huespedes [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacido=" + fechaNacido
				+ ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", reserva_id=" + reserva_id + "]";
	}

}
