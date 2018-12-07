package fr.naf.hbjpa.hbjpacache.secondecache;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Ville {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private int nbrHabitant;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pays_id")
	private Pays pays;	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbrHabitant() {
		return nbrHabitant;
	}

	public void setNbrHabitant(int nbrHabitant) {
		this.nbrHabitant = nbrHabitant;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}
	
		
}
