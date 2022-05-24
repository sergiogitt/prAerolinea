package controladores;

import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entidades.Avion;
import entidades.Director;
public class ControladorDirector {
	// Factoria para obtener objetos EntityManager
		private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_aerolinea");
		private EntityManager em;
		private Query consulta;
		
		public void borrarDirector(Director d) {
			this.em = entityManagerFactory.createEntityManager();
			Director aux = null;
			this.em.getTransaction().begin();
			// Si v no es un objeto gestionado por el contexto de persistencia
			if (!this.em.contains(d)) {
				// Carga v en el contexto de persistencia y se guarda en aux
				aux = this.em.merge(d);
			}
			// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
			// caché
			this.em.remove(aux);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public void modifyDirector(Director v) {
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

		public void createDirector(Director v) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacción en la base de datos
			// porque vamos a persistir información en la misma
			this.em.getTransaction().begin();
			// Se guarda el objeto en el contexto de persistencia (caché intermedia)
			// v es una entidad conectada
			this.em.persist(v);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}

		public Director findByPK(int pk) {
			this.em = entityManagerFactory.createEntityManager();
			Director aux = null;
			// Se crea el objeto Query a partir de una SQL nativa
			this.consulta = em.createNativeQuery("Select * from director where identificadorDirector = ?", Director.class);
			this.consulta.setParameter(1, pk);
			aux = (Director) consulta.getSingleResult();
			this.em.close();
			return aux;

		}


		public List<Director> findAll() {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNamedQuery("Director.findAll");
			List<Director> listaDirectores = (List<Director>) consulta.getResultList();
			this.em.close();
			return listaDirectores;
		}

		// En este caso se va a utilizar una nativeQuery, que permite pasar código
		// SQL directamente a la base de datos
		public List<Director> findByNombre(String modelo) {
			this.em = entityManagerFactory.createEntityManager();
			this.consulta = em.createNativeQuery("Select * from director where nombre=?", Director.class);
			this.consulta.setParameter(1, modelo);
			List<Director> listaDirectores = (List<Director>) consulta.getResultList();
			this.em.close();
			return listaDirectores;
		}

}
