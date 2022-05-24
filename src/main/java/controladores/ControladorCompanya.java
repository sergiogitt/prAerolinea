package controladores;

import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entidades.Avion;
import entidades.Companyia;
public class ControladorCompanya {
	// Factoria para obtener objetos EntityManager
		private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_aerolinea");
		private EntityManager em;
		private Query consulta;
		
		public void borrarCompanya(Companyia c) {
			this.em = entityManagerFactory.createEntityManager();
			Companyia aux = null;
			this.em.getTransaction().begin();
			// Si v no es un objeto gestionado por el contexto de persistencia
			if (!this.em.contains(c)) {
				// Carga v en el contexto de persistencia y se guarda en aux
				aux = this.em.merge(c);
			}
			// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
			// caché
			this.em.remove(aux);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public void modifyCompanya(Companyia v) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacción en la base de datos
			// porque vamos a persistir información en la misma
			this.em.getTransaction().begin();
			// merge(Objeto) - Si una entidad con el mismo identificador que v existe en el
			// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
			// como entidad gestionada
			// Si el objeto v no existe en la base de datos, se comporta como persist() y la
			// entidad gestionada es la devuelta por merge(), por lo que v es una entidad desconectada
			this.em.merge(v);
			this.em.getTransaction().commit();
			this.em.close();

		}

		public void createCompanyia(Companyia c) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacción en la base de datos
			// porque vamos a persistir información en la misma
			this.em.getTransaction().begin();
			// Se guarda el objeto en el contexto de persistencia (caché intermedia)
			// v es una entidad conectada
			this.em.persist(c);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public Companyia findByPK(int pk) {
			this.em = entityManagerFactory.createEntityManager();
			Companyia aux = null;
			// Se crea el objeto Query a partir de una SQL nativa
			this.consulta = em.createNativeQuery("Select * from companya where codcompanyia = ?", Avion.class);
			this.consulta.setParameter(1, pk);
			aux = (Companyia) consulta.getSingleResult();
			this.em.close();
			return aux;

		}
		public Companyia findByNombre(String nombre) {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNamedQuery("Companyia.findNomcompanyia");
			this.consulta.setParameter("nombre", nombre);
			Companyia v = (Companyia) consulta.getSingleResult();
			this.em.close();
			return v;
		}


		public List<Companyia> findAll() {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNamedQuery("Companyia.findAll");
			List<Companyia> listaVehiculos = (List<Companyia>) consulta.getResultList();
			this.em.close();
			return listaVehiculos;
		}
		public List<Companyia> findByDireccion(String direccion) {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNativeQuery("Select * from companyia where direccionSucursalCentral=?", Companyia.class);
			this.consulta.setParameter(1, direccion);
			List<Companyia> listaVehiculos = (List<Companyia>) consulta.getResultList();
			this.em.close();
			return listaVehiculos;
		}

		

}
