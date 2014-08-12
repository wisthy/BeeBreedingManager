package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * test class for the Group model
 * @author Wisthy
 *
 */
public class GroupTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEmptyIterator() {
		Group group = new Group();
		int i = 0;
		for(Specification s : group){
			i++;
		}
		assertEquals(0, i);
	}
	
	
	@Test
	public void testIterator(){
		Group group = new Group();
		group.addMember(new Specie());
		
		Specie specie2 = new Specie();
		specie2.setName("koin");
		specie2.addGroup(group);
		group.addMember(specie2, false);
		group.addMember(specie2, false);
		
		Group group2 = new Group();
		group2.addMember(specie2);
		group2.addMember(new Group());
		
		int i = 0;
		for(Specification s : group){
			i++;
		}
		assertEquals(2, i);
	}

}
