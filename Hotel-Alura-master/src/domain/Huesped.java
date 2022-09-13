package domain;

public class Huesped{
	private int id;
	private String nombre;
	private String apellido;
	private int fechaNacimiento;
	private String Nacionalidad;
	private int telefono;
	private int idReserva;
	
	@Override
	public String toString() {
		
		return "Id:" + this.id + " Nombre: " + this.nombre + " Apellido: " + this.apellido +
				" FechaNacimiento: " + this.fechaNacimiento + " Nacionalidad: "  + this.Nacionalidad + " Telefono: " + this.telefono + " Id Reserva: " + this.idReserva;
	}
	public Huesped() {
		
	}
	public Huesped(int id, String nombre, String apellido, int fechaNacimiento, String nacionalidad, int telefono,
			int idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.Nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	public Huesped(String nombre, String apellido, int fechaNacimiento, String nacionalidad, int telefono, int idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.Nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(int fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getIdReserva() {
		return this.idReserva;
	}

	

	
	
}
