package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Class used to represent of bee or a group of bees
 * usefull for mutation representation
 * @author Wisthy
 *
 */
@Entity @Inheritance(strategy=InheritanceType.JOINED)
public abstract class Specification extends ABaseEntity implements Comparable<Specification>{

	@Column(unique = true)
	private String name;

	@ManyToMany
	private Collection<Group> groups;



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

	/**
	 * @return the groups
	 */
	public Collection<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}	



	// ********** Abstract ********** //

	/**
	 * 
	 * @return the type of the instance 
	 */
	protected abstract ESpecificationType getType();



	// ********** others ********** //

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Specification o) {
		return new CompareToBuilder().append(this.getType(), o.getType())
				.append(this.name, o.name)
				.appendSuper(super.compareTo(o))
				.toComparison();
	}


}
