package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the avion database table.
 * 
 */
@Entity
@Table(name="avion")
@NamedQuery(name="Avion.findAll", query="SELECT a FROM Avion a")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identificadorAvion;

	private String modelo;

	private int numEmpleados;

	private int numPasajeros;

	private int velocidadMaxima;

	//bi-directional many-to-one association to Companyia
	@ManyToOne()
	@JoinColumn(name="perteneceA")
	private Companyia companyia;

	public Avion() {
	}

	public int getIdentificadorAvion() {
		return this.identificadorAvion;
	}

	public void setIdentificadorAvion(int identificadorAvion) {
		this.identificadorAvion = identificadorAvion;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumEmpleados() {
		return this.numEmpleados;
	}

	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	public int getNumPasajeros() {
		return this.numPasajeros;
	}

	public void setNumPasajeros(int numPasajeros) {
		this.numPasajeros = numPasajeros;
	}

	public int getVelocidadMaxima() {
		return this.velocidadMaxima;
	}

	public void setVelocidadMaxima(int velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	public Companyia getCompanyia() {
		return this.companyia;
	}

	public void setCompanyia(Companyia companyia) {
		this.companyia = companyia;
	}

}