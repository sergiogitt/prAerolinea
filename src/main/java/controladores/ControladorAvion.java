package controladores;

import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entidades.Avion;
public class ControladorAvion {
	// Factoria para obtener objetos EntityManager
		private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_aerolinea");
		private EntityManager em;
		private Query consulta;
		
		public void borrarAvion(Avion a) {
			this.em = entityManagerFactory.createEntityManager();
			Avion aux = null;
			this.em.getTransaction().begin();
			// Si v no es un objeto gestionado por el contexto de persistencia
			if (!this.em.contains(a)) {
				// Carga v en el contexto de persistencia y se guarda en aux
				aux = this.em.merge(a);
			}
			// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
			// cach�
			this.em.remove(aux);
			// Se vuelca la informaci�n del contexto (cach� intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public void modifyAvion(Avion v) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacci�n en la base de datos
			// porque vamos a persistir informaci�n en la misma
			this.em.getTransaction().begin();
			// merge(Objeto) - Si una entidad con el mismo identificador que v existe en el
			// contexto de persistencia (cach�), se actualizan sus atributos y se devuelve
			// como entidad gestionada
			// Si el objeto v no existe en la base de datos, se comporta como persist() y la
			// entidad gestionada es la devuelta por merge(), por lo que v es una entidad desconectada
			this.em.merge(v);
			this.em.getTransaction().commit();
			this.em.close();

		}

		public void createAvion(Avion v) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacci�n en la base de datos
			// porque vamos a persistir informaci�n en la misma
			this.em.getTransaction().begin();
			// Se guarda el objeto en el contexto de persistencia (cach� intermedia)
			// v es una entidad conectada
			this.em.persist(v);
			// Se vuelca la informaci�n del contexto (cach� intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public Avion findByPK(int pk) {
			this.em = entityManagerFactory.createEntityManager();
			Avion aux = null;
			// Se crea el objeto Query a partir de una SQL nativa
			this.consulta = em.createNativeQuery("Select * from avion where identificadorAvion = ?", Avion.class);
			this.consulta.setParameter(1, pk);
			aux = (Avion) consulta.getSingleResult();
			this.em.close();
			return aux;

		}


		public List<Avion> findAll() {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNamedQuery("Avion.findAll");
			List<Avion> listaAviones = (List<Avion>) consulta.getResultList();
			this.em.close();
			return listaAviones;
		}
		

		// En este caso se va a utilizar una nativeQuery, que permite pasar c�digo
		// SQL directamente a la base de datos
		public List<Avion> findByModelo(String modelo) {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNativeQuery("Select * from avion where modelo=?", Avion.class);
			this.consulta.setParameter(1, modelo);
			List<Avion> listaVehiculos = (List<Avion>) consulta.getResultList();
			this.em.close();
			return listaVehiculos;
		}

}
