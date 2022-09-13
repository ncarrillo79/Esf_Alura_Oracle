package user_aplication;

public class reservas {
	private int id;
	private int fechaEntrada;
	private int fechaSalida;
	private int valor;
	private String FormaPago;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(int fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public int getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(int fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getFormaPago() {
		return FormaPago;
	}
	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}
	
	
}
