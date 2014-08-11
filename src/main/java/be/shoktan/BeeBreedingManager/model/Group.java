package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Class to regroup bees or other group of bees to simplify mutation description
 * @author Wisthy
 *
 */
@Entity
public class Group extends Specification {
	
	@ManyToMany(mappedBy = "groups")
	private Collection<Specification> members;
	
	
	
	// ********** getters & setters ********** //

	/**
	 * @return the members
	 */
	public Collection<Specification> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(Collection<Specification> members) {
		this.members = members;
	}
	
	
	
	// ********** others ********** //

	/*
	 * (non-Javadoc)
	 * @see be.shoktan.BeeBreedingManager.model.Specification#getType()
	 */
	@Override
	protected ESpecificationType getType() {
		return ESpecificationType.group;
	}
}
