package fr.naf.hbjpa.hbjpacache.firstcache;

public class User1Main {

	public static long idguide = 1;
	
	public static void main(String[] args) {
		DaoFirstCache dao =new DaoFirstCache();
		//Guide ajouterGuide = dao.ajouterGuide();
		//idguide =ajouterGuide.getId();
		dao.rechercherGuide(idguide);

	}

}
