package fr.naf.hbjpa.hbjpacache.secondecache;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;




public class DaoSecondeCache {

	private static EntityManagerFactory emf;
	private static EntityManager em1;		
	private static EntityTransaction ts1;
	private static EntityManager em2;		
	private static EntityTransaction ts2;
	
	
	public DaoSecondeCache() {
				
		 emf = Persistence.createEntityManagerFactory("persistence");    						         	            

	}
	
	public Pays ajouterPays() {
		Pays pays =new Pays();
		pays.setNom("France");
		pays.setSuperficie("4500km");	
		em1 = emf.createEntityManager();
		 ts1= em1.getTransaction();
		ts1.begin();
        em1.persist(pays);                 
        ts1.commit(); 
		return pays;
	}
	
	
	
	public void rechercherPays(long paysid) {
		
		Statistics stats=emf.unwrap(SessionFactory.class).getStatistics();
		stats.setStatisticsEnabled(true);
		
		 em1 = emf.createEntityManager();
		 ts1= em1.getTransaction();
		 
		ts1.begin();	
        //requete sql: select pays0_.id as id1_1_0_, pays0_.nom as nom2_1_0_, 
		//pays0_.superficie as superfic3_1_0_ from Pays pays0_ where pays0_.id=?
		
		//recupere l'objet avec l'id pays
		Pays pays =em1.find(Pays.class, paysid);
		int sizeVille =pays.getVilles().size();
	    ts1.commit();
	    em1.close();
		
	    
	    

		 em2 = emf.createEntityManager();
		 ts2= em2.getTransaction();
	    ts2.begin();
		//avec le seconde cache on accede pas à la base pour recuperer le meme enregistrement 
		//on recupere juste une copie de  la reference objet qui est deja charge dans la memoire du cache 2 

		//recupere l'objet 2 avec le meme id pays
		Pays pays1 =em2.find(Pays.class, paysid);
		int sizeVille1 =pays1.getVilles().size();
		ts2.commit();
	    em2.close();
	    
	    System.out.println(pays);
	    System.out.println(pays1);		
		if(pays != pays1)
		{
			System.out.println("c'est pas les memes references mais c'est juste une copie");	
		}
	    System.out.println(stats.getSecondLevelCacheStatistics("fr.naf.hbjpa.hbjpacache.secondecache.Pays"));	

		
	}
	
	
	
}

