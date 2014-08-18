/**
 * 
 */
package be.shoktan.BeeBreedingManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Basic class to hold common methods for all the Entity possessing a name
 * @author Wisthy
 *
 */
@Entity @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class ANamedEntity extends ABaseEntity {
	@Column(unique = true)
	private String name;



	// ********** getters & setters ********** //

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	// ********** Abstract ********** //

	// ********** others ********** //
}
