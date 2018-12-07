package fr.naf.hbjpa.hbjpacache.secondecache;

public class User1Main {

	public static long idpays = 1;
	
	public static void main(String[] args) {
		DaoSecondeCache dao =new DaoSecondeCache();
		//Pays ajouterPays = dao.ajouterPays();
		//idpays =ajouterPays.getId();
		dao.rechercherPays(idpays);

	}

}
