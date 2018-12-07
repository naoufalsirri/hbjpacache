package fr.naf.hbjpa.hbjpacache.firstcache;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;




public class DaoFirstCache {

	private static EntityManagerFactory emf;
	private static EntityManager em1;		
	private static EntityTransaction ts1;
	
	
	
	public DaoFirstCache() {
		
		
		 emf = Persistence.createEntityManagerFactory("persistence");    						         	            
		 em1 = emf.createEntityManager();
		 ts1= em1.getTransaction();

		
	}
	
	public Guide ajouterGuide() {
		Guide guide =new Guide();
		guide.setNom("Farad");
		guide.setSalaire(3000);		
		ts1.begin();
        em1.persist(guide);                 
        ts1.commit(); 
		return guide;
	}
	
	
	
	public void rechercherGuide(long guideid) {
		
		ts1.begin();		
        //requete sql: select guide0_.id as id1_0_0_, guide0_.nom as nom2_0_0_, guide0_.salaire as 
		//salaire3_0_0_, guide0_.version as version4_0_0_ from Guide guide0_ where guide0_.id=?
		
		//recupere l'objet avec l'id guide
		Guide guide =em1.find(Guide.class, guideid);
			
		
		//avec le first cache on accede pas à la base pour recuperer le meme enregistrement 
		//on recupere juste la reference objet qui est deja charge dans la memoire 

		//recupere l'objet 2 avec le meme id guide
		Guide guide1 =em1.find(Guide.class, guideid);
		
		
		//avec le first cache on accede tout de meme à la base pour recuperer le meme enregistrement
		//car avec la methode createQuery(JPQL) on tope toujours la base 
		//SQL: select guide0_.id as id1_0_, guide0_.nom as nom2_0_, guide0_.salaire as salaire3_0_, 
		//guide0_.version as version4_0_ from Guide guide0_ where guide0_.id=?
	    //on recupere juste la reference objet qui est deja charge dans la memoire 
		
		//recupere l'objet 3 avec le meme id guide
		Guide guide2 =(Guide)em1.createQuery("select guide from Guide guide where guide.id=:id").setParameter("id", guideid).getSingleResult();
		
		if(guide == guide1 && guide1 == guide2 && guide == guide2)
		{
			System.out.println("c'est la meme reference");	
		}
		
		ts1.commit(); 
			
		em1.close();
	
	}
	
	
	
}
