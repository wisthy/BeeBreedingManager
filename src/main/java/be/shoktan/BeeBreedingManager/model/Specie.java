package be.shoktan.BeeBreedingManager.model;

import javax.persistence.Entity;

/**
 * Model class representing a specie of a bee
 * @author Wisthy
 *
 */
@Entity
public class Specie extends Specification {

	/*
	 * (non-Javadoc)
	 * @see be.shoktan.BeeBreedingManager.model.Specification#getType()
	 */
	@Override
	protected ESpecificationType getType() {
		return ESpecificationType.specie;
	}

}
