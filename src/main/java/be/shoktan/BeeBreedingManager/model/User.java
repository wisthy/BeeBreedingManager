package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;

import javax.persistence.OneToMany;


/**
 * Model class to represent a user of the application
 * @author Wisthy
 *
 */
public class User extends ANamedEntity {
	
	@OneToMany(mappedBy="owner")
	private Collection<BeeCollection> collections;

	
	
	// ********** getters & setters ********** //
	
	/**
	 * @return the collections
	 */
	public Collection<BeeCollection> getCollections() {
		return collections;
	}

	/**
	 * @param collections the collections to set
	 */
	public void setCollections(Collection<BeeCollection> collections) {
		this.collections = collections;
	}
	
	
	
	// ********** others ********** //
}
