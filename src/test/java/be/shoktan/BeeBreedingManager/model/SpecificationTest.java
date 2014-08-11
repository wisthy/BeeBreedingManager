package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpecificationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Specification group = new Group();
		group.setName("a");
		
		Specification spec = new Specie();
		spec.setName("b");
		
		assertTrue(spec.compareTo(group) < 0); 
		assertTrue(spec.compareTo(spec) == 0); 
		assertTrue(group.compareTo(group) == 0); 
		assertTrue(group.compareTo(spec) > 0); 
		
		Specification spec2 = new Specie();
		spec2.setName("c");
		assertTrue(spec2.compareTo(spec2) == 0); 
		
		assertTrue(spec2.getType().compareTo(spec.getType()) == 0);
		assertTrue(spec2.getName().compareTo(spec.getName()) > 0);
		
		assertTrue(spec2.compareTo(spec) > 0); 
	}

}
