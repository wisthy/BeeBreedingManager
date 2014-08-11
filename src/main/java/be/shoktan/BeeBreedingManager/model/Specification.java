package be.shoktan.BeeBreedingManager.model;

import javax.persistence.Entity;

/**
 * Class used to represent of bee or a group of bees
 * usefull for mutation representation
 * @author Wisthy
 *
 */
@Entity @Inheritance(strategy=InheritanceType.JOINED)
public class Specification {
	
}
