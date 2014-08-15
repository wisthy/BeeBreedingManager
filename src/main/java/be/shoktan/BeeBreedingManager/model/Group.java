package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

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
		this.members = new TreeSet<Specification>();
		for(Specification member : members){
			this.addMember(member);
		}
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
	
	/**
	 * Add a specification to this group 
	 * @param member the specification to add
	 */
	public void addMember(Specification member){
		addMember(member, true);
	}
	
	/**
	 * Add a specification to this group 
	 * @param member the specification to add
	 * @param reverse if true, add this specification to the group too. 
	 */
	public void addMember(Specification member, boolean reverse){
		if(this.members == null){
			this.members = new TreeSet<Specification>();
		}
		this.members.add(member);
		if(reverse){
			member.addGroup(this, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see be.shoktan.BeeBreedingManager.model.Specification#iterator()
	 */
	@Override
	public Iterator<Specification> iterator() {
		if(this.members == null){
			this.members = new TreeSet<>();
		}
		return this.members.iterator();
	}
}
