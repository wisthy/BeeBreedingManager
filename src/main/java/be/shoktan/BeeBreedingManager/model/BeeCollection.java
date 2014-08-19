/**
 * 
 */
package be.shoktan.BeeBreedingManager.model;

import javax.persistence.ManyToOne;

/**
 * Model class to represent a collection of bees
 * @author Wisthy
 *
 */
public class BeeCollection extends ANamedEntity{
	@ManyToOne
	private User owner;
	
	
	
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
	
	
	
	// ********** others ********** //
}
