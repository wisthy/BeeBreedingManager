package be.shoktan.BeeBreedingManager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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

	/*
	 * (non-Javadoc)
	 * @see be.shoktan.BeeBreedingManager.model.Specification#iterator()
	 */
	@Override
	public Iterator<Specification> iterator() {
		return new SpecieIterator(this);
	}
	
	public Collection<Specie> mutationResultWith(Specie second) {
		if(second == null){
			return null;
		}
		
		Collection<Specie> result = new ArrayList<>();
		
		for(Mutation m : this.getMutations()){
			Specification other = m.getOther(this);
			if(second.equals(other)){
				result.addAll(m.getResult());
			}
		}
		
		return result;
	}
	
	private class SpecieIterator implements Iterator<Specification>{
		private boolean done = false;
		private Specie root;
		
		/**
		 * @param root
		 */
		public SpecieIterator(Specie root) {
			super();
			this.root = root;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return !done;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Specification next() {
			done = true;
			return root;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
		
	}

	

}
