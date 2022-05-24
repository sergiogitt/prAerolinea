package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the director database table.
 * 
 */
@Entity
@Table(name="director")
@NamedQuery(name="Director.findAll", query="SELECT d FROM Director d")
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identificadorDirector;

	private String ape1Director;

	private String ape2Director;

	private String correoPersonal;

	private String nomDirector;

	private String telefonoPersonal;

	//bi-directional one-to-one association to Companyia

	@OneToOne(mappedBy="director", fetch=FetchType.LAZY)
	private Companyia companyia;

	public Director() {
	}

	public int getIdentificadorDirector() {
		return this.identificadorDirector;
	}

	public void setIdentificadorDirector(int identificadorDirector) {
		this.identificadorDirector = identificadorDirector;
	}

	public String getApe1Director() {
		return this.ape1Director;
	}

	public void setApe1Director(String ape1Director) {
		this.ape1Director = ape1Director;
	}

	public String getApe2Director() {
		return this.ape2Director;
	}

	public void setApe2Director(String ape2Director) {
		this.ape2Director = ape2Director;
	}

	public String getCorreoPersonal() {
		return this.correoPersonal;
	}

	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}

	public String getNomDirector() {
		return this.nomDirector;
	}

	public void setNomDirector(String nomDirector) {
		this.nomDirector = nomDirector;
	}

	public String getTelefonoPersonal() {
		return this.telefonoPersonal;
	}

	public void setTelefonoPersonal(String telefonoPersonal) {
		this.telefonoPersonal = telefonoPersonal;
	}

	public Companyia getCompanyia() {
		return this.companyia;
	}

	public void setCompanyia(Companyia companyia) {
		this.companyia = companyia;
	}

}