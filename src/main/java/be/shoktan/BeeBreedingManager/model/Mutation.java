package be.shoktan.BeeBreedingManager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Model class to represent mutation between to species or group of species resulting in a new specie
 * @author Wisthy
 *
 */
@Entity
public class Mutation extends ABaseEntity implements Comparable<Mutation>{
	private boolean identicalAllowed;

	@ManyToMany
	private List<Specification> parents;

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
	private Mutation(boolean identicalAllowed, List<Specification> parents, SortedSet<Specie> result, String remark, Mutation derivatedFrom) {
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
	public List<Specification> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(List<Specification> parents) {
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
		return "Mutation [#"+getId()+", identicalAllowed=" + identicalAllowed + ", parents="
				+ parents + ", result=" + result + ", remark=" + remark
				+ "]";
	}

	/**
	 * helper method to encapsulate a varargs of Specification into a Collection
	 * @param specifications the specifications to encapsulate
	 * @return a collection with all the specifications from the varargs
	 */
	private static List<Specification> toSpecificationCollection(Specification... specifications){
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

	/**
	 * generate all the derivated mutations on this one
	 * There are two kind of derivation:
	 * 
	 * - mutation with parents in reverse order
	 * 
	 * - in case one of the parents is a group, create a new mutation for each member of this group instead of the group itself
	 */
	public void derivate(){
		System.out.println(">>> "+this.getId()+" derivating");
		// add the reverse mutation 
		if(this.parents == null || this.parents.size() < 2){
			System.out.println("ko for "+this.getId());
			return;
		}

		Specification first = this.parents.get(0);
		for(Specification s1 : first){
			System.out.println(this.getId()+"-> "+s1);
			Specification second = this.parents.get(1);
			for(Specification s2 : second){
				System.out.println(this.getId()+"\t -> "+s2);
				if(s1 != first || s2 != second){
					if(s1 != s2 || this.identicalAllowed){
						Mutation m = new Mutation(this.identicalAllowed, toSpecificationCollection(s1, s2), this.result, this.remark, this);
						s1.addMutation(m);
						s2.addMutation(m);
						System.out.println("D: "+m);
						m.derivate();
					}
					if(s1 != s2){
						Mutation m = new Mutation(this.identicalAllowed, toSpecificationCollection(s2, s1), this.result, this.remark, this);
						System.out.println("RD: "+m);
						s1.addMutation(m);
						s2.addMutation(m);
						//m.derivate();
					}
				}
			}
		}
		System.out.println("<<< "+this.getId()+" derivating");
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Mutation o) {
		return new CompareToBuilder().append(this.parents.get(0), o.parents.get(0)).append(this.parents.get(1), o.parents.get(1)).append(this.result, o.result).toComparison();
	}
}
