package be.shoktan.BeeBreedingManager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Model class to represent mutation between to species or group of species resulting in a new specie
 * @author Wisthy
 *
 */
@Entity
public class Mutation extends ABaseEntity{
	private boolean identicalAllowed;
	
	@ManyToMany
	private Collection<Specification> parents;
	
	@ManyToMany
	private SortedSet<Specie> result;
	
	private String remark;
	
	@ManyToOne
	private Mutation derivatedFrom;
	
	
	
	// ********** constructors ********** //
	
	/**
	 * default constructor
	 */
	public Mutation(){}
	
	/**
	 * general purpose constructor
	 * @param identicalAllowed can the mutation occurred if the two parents are of the same specie?
	 * @param parents the parents of the mutation, it can be precise species or groups of several species
	 * @param result the resulting species that can be obtained when breeding the two parents
	 * @param remark any remark/additional information about the mutation. Like, for example, the need to be in a particular biome for the mutation to succeed
	 * @param derivatedFrom the mutation from which this one is derivated. 
	 */
	private Mutation(boolean identicalAllowed, Collection<Specification> parents, SortedSet<Specie> result, String remark, Mutation derivatedFrom) {
		super();
		this.identicalAllowed = identicalAllowed;
		this.parents = parents;
		this.result = result;
		this.remark = remark;
		this.derivatedFrom = derivatedFrom;
	}
	
	/**
	 * create a new mutation
	 * @param firstParent one of the parent of the mutation, it can be a precise specie or a group of several species
	 * @param secondParent the other parent of the mutation, it can be a precise specie or a group of several species
	 * @param result the resulting specie that can be obtained when breeding the two parents
	 * @param isIdenticalAllowed can the mutation occurred if the two parents are of the same specie?
	 * @param remark any remark/additional information about the mutation. Like, for example, the need to be in a particular biome for the mutation to succeed
	 */
	public Mutation(Specification firstParent, Specification secondParent, Specie result, boolean isIdenticalAllowed, String remark){
		this(isIdenticalAllowed, toSpecificationCollection(firstParent, secondParent), toSpecieSet(result), remark, null);
	}
	
	
	
	// ********** getters & setters ********** //
	
	

	/**
	 * @return the identicalAllowed
	 */
	public boolean isIdenticalAllowed() {
		return identicalAllowed;
	}

	/**
	 * @param identicalAllowed the identicalAllowed to set
	 */
	public void setIdenticalAllowed(boolean identicalAllowed) {
		this.identicalAllowed = identicalAllowed;
	}

	/**
	 * @return the result
	 */
	public Collection<Specie> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(SortedSet<Specie> result) {
		this.result = result;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the derivatedFrom
	 */
	public Mutation getDerivatedFrom() {
		return derivatedFrom;
	}

	/**
	 * @param derivatedFrom the derivatedFrom to set
	 */
	public void setDerivatedFrom(Mutation derivatedFrom) {
		this.derivatedFrom = derivatedFrom;
	}

	/**
	 * @return the parents
	 */
	public Collection<Specification> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(Collection<Specification> parents) {
		if(this.parents != null && this.parents.size() == 2){
			this.parents = parents;
		}else{
			// TODO - 20140821 - @Wisthy - replace by a throw error "illegalArgument"?
			this.parents = null;
		}
	}


	
	// ********** others ********** //
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mutation [identicalAllowed=" + identicalAllowed + ", parents="
				+ parents + ", result=" + result + ", remark=" + remark
				+ ", getId()=" + getId() + "]";
	}
	
	/**
	 * helper method to encapsulate a varargs of Specification into a Collection
	 * @param specifications the specifications to encapsulate
	 * @return a collection with all the specifications from the varargs
	 */
	private static Collection<Specification> toSpecificationCollection(Specification... specifications){
		ArrayList<Specification> result = new ArrayList<>();
		for(Specification s : specifications){
			result.add(s);
		}
		return result;
	}
	
	/**
	 * helper method to encapsulate a varargs of Species into a Set
	 * @param species the species to encapsulate
	 * @return a set with all the species from the varargs
	 */
	private static SortedSet<Specie> toSpecieSet(Specie... species){
		SortedSet<Specie> result = new TreeSet<>();
		for(Specie s : species){
			result.add(s);
		}
		return result;
	}
	
	/**
	 * check if the provided specification is a parent in this mutation
	 * @param specification the specification to check for
	 * @return true if the provided specification is one of the parents of this mutation, false elsewhere
	 */
	public boolean isParent(Specification specification){
		return this.parents.contains(specification);
	}
	
	/**
	 * 
	 * @param specification one of the parent of this mutation
	 * @return the first specification that is not the one provided in input
	 */
	public Specification getOther(Specification specification){
		for(Specification s : this.parents){
			if(! s.equals(specification)) return s;
		}
		return null;
	}
	
	private void derivate(){
		// add the reverse mutation 
	}
}
