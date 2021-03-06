package be.shoktan.BeeBreedingManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * Basic class to hold common methods for all the Entity
 * @author Wisthy
 *
 */
@Entity @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
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


	public int compareTo(ABaseEntity o) {
		return new CompareToBuilder().append(this.id, o.id)
				.toComparison();
	}
	
	
}
