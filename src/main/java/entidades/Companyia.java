package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the companyia database table.
 * 
 */
@Entity
@Table(name="companyia")
@NamedQueries({
@NamedQuery(name="Companyia.findAll", query="SELECT c FROM Companyia c"),
@NamedQuery(name = "Companyia.findNomcompanyia", query = "SELECT v FROM Companyia v WHERE v.nomcompanyia = :nombre"),
})
public class Companyia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codcompanyia;

	private String correroContacto;

	private String direccionSucursalCentral;

	private String nomcompanyia;

	private String telefonoContacto;

	//bi-directional many-to-one association to Avion
	@OneToMany(mappedBy="companyia")
	private List<Avion> avionesCompanya;

	//bi-directional one-to-one association to Director

	@OneToOne()
	@JoinColumn(name="director")
	private Director director;

	public Companyia() {
	}

	public int getCodcompanyia() {
		return this.codcompanyia;
	}

	public void setCodcompanyia(int codcompanyia) {
		this.codcompanyia = codcompanyia;
	}

	public String getCorreroContacto() {
		return this.correroContacto;
	}

	public void setCorreroContacto(String correroContacto) {
		this.correroContacto = correroContacto;
	}

	public String getDireccionSucursalCentral() {
		return this.direccionSucursalCentral;
	}

	public void setDireccionSucursalCentral(String direccionSucursalCentral) {
		this.direccionSucursalCentral = direccionSucursalCentral;
	}

	public String getNomcompanyia() {
		return this.nomcompanyia;
	}

	public void setNomcompanyia(String nomcompanyia) {
		this.nomcompanyia = nomcompanyia;
	}

	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public List<Avion> getAvionesCompanya() {
		return this.avionesCompanya;
	}

	public void setAvionesCompanya(List<Avion> avionesCompanya) {
		this.avionesCompanya = avionesCompanya;
	}

	public Avion addAvionesCompanya(Avion avionesCompanya) {
		getAvionesCompanya().add(avionesCompanya);
		avionesCompanya.setCompanyia(this);

		return avionesCompanya;
	}

	public Avion removeAvionesCompanya(Avion avionesCompanya) {
		getAvionesCompanya().remove(avionesCompanya);
		avionesCompanya.setCompanyia(null);

		return avionesCompanya;
	}

	public Director getDirectorBean() {
		return this.director;
	}

	public void setDirectorBean(Director directorBean) {
		this.director = directorBean;
	}

	@Override
	public String toString() {
		return "Companyia [codcompanyia=" + codcompanyia + ", correroContacto=" + correroContacto
				+ ", direccionSucursalCentral=" + direccionSucursalCentral + ", nomcompanyia=" + nomcompanyia
				+ ", telefonoContacto=" + telefonoContacto + ", avionesCompanya=" + avionesCompanya + ", director="
				+ director + "]";
	}
	

}