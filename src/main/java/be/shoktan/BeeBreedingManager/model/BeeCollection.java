package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Model class to represent a collection of bees
 * @author Wisthy
 *
 */
public class BeeCollection extends ANamedEntity{
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Modpack modpack;
	
	@ManyToMany
	private SortedSet<Specie> ownedSpecies;
	
	
	
	// ********** getters & setters ********** //
	
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the modpack
	 */
	public Modpack getModpack() {
		return modpack;
	}

	/**
	 * @param modpack the modpack to set
	 */
	public void setModpack(Modpack modpack) {
		this.modpack = modpack;
	}

	/**
	 * @return the ownedSpecies
	 */
	public Collection<Specie> getOwnedSpecies() {
		return ownedSpecies;
	}

	/**
	 * @param ownedSpecies the ownedSpecies to set
	 */
	public void setOwnedSpecies(SortedSet<Specie> ownedSpecies) {
		this.ownedSpecies = ownedSpecies;
	}
	
	
	// ********** others ********** //
	
	/**
	 * do the initialization of the ownedSpecies set if necessary
	 */
	private void initOwnedSpecies(){
		if(this.ownedSpecies == null){
			this.ownedSpecies = new TreeSet<>();
		}
	}
	
	/**
	 * add the specie to the set of owned species
	 * @param specie the specie to add
	 * @return true if the specie has been added, false elsewhere
	 */
	public boolean addSpecie(Specie specie){
		initOwnedSpecies();
		return this.ownedSpecies.add(specie);
	}
}
