package domain;

public class Reserva {

	public static int id;
	private int fechaEntrada;
	private int fechaSalida;
	private int Valor;
	private String FormadePago;
	
	@Override
	public String toString() {
		return 	Reserva.id +
		this.fechaEntrada +
		this.fechaSalida +
		this.Valor +
		this.FormadePago;
	}
	
	public Reserva() {
	}
	public Reserva(int id, int fechaEntrada, int fechaSalida, int valor, String formadePago) {
		Reserva.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.Valor = valor;
		this.FormadePago = formadePago;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		Reserva.id = id;
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
		return Valor;
	}
	public void setValor(int valor) {
		Valor = valor;
	}
	public String getFormadePago() {
		return FormadePago;
	}
	public void setFormadePago(String formadePago) {
		FormadePago = formadePago;
	}	
}
