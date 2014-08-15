package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

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
public abstract class Specification extends ABaseEntity implements Comparable<Specification>, Iterable<Specification>{

	@Column(unique = true)
	private String name;

	@ManyToMany
	private Collection<Group> groups;
	
	@ManyToMany(mappedBy = "parents")
	private Collection<Mutation> mutations;



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
		this.groups = new TreeSet<>();
		for(Group group : groups){
			this.addGroup(group);
		}
	}	
	
	/**
	 * @return the mutations
	 */
	public Collection<Mutation> getMutations() {
		return mutations;
	}

	/**
	 * @param mutations the mutations to set
	 */
	public void setMutations(Collection<Mutation> mutations) {
		this.mutations = mutations;
	}	


	// ********** Abstract ********** //



	/**
	 * 
	 * @return the type of the instance 
	 */
	protected abstract ESpecificationType getType();
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public abstract Iterator<Specification> iterator();



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

	/**
	 * Add a group to this specification 
	 * @param group the group to add
	 */
	public void addGroup(Group group){
		addGroup(group, true);
	}
	
	
	/**
	 * Add a group to this specification 
	 * @param group the group to add
	 * @param reverse if true, add this specification to the group too. 
	 */
	public void addGroup(Group group, boolean reverse){
		if(this.groups == null){
			this.groups = new TreeSet<>();
		}
		this.groups.add(group);
		if(reverse){
			group.addMember(this, false);
		}
	}

	/**
	 * Add a mutation to this specification
	 * @param m the mutation to add
	 */
	public void addMutation(Mutation m) {
		if(this.mutations == null){
			this.mutations = new TreeSet<>();
		}
		this.mutations.add(m);
	}

}
