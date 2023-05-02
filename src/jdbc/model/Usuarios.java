package jdbc.model;

public class Usuarios {
	private Integer id;
	private String usuario;
	private String clave;
	private String correo;

	public Usuarios(String usuario, String clave, String correo) {
		this.usuario = usuario;
		this.clave = clave;
		this.correo = correo;
	}
	

	public Usuarios(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
