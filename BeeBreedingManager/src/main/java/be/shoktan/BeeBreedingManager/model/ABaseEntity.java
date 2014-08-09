package be.shoktan.BeeBreedingManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * Basic class to hold common methods for all the Entity
 * @author Wisthy
 *
 */
@Entity
public abstract class ABaseEntity {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	
}
