package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MutationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Group gMundane = new Group();
		gMundane.setName("mundane");
		
		Group gNatural = new Group();
		gNatural.setName("natural");
		gNatural.addGroup(gMundane);
		
		Specie forest = new Specie();
		forest.setName("forest");
		forest.addGroup(gNatural);
		
		Specie meadow = new Specie();
		meadow.setName("meadow");
		meadow.addGroup(gNatural);
		
		Specie mystical = new Specie();
		mystical.setName("mystical");
		mystical.addGroup(gMundane);
		
		Specie common = new Specie();
		common.setName("common");
		
		Specie cultivated = new Specie();
		cultivated.setName("cultivated");
		
		Mutation mCommon = new Mutation(gNatural, gMundane, common, false, "");
		System.out.println(mCommon);
		
		Mutation mCultivated = new Mutation(gMundane, common, cultivated, false, "");
		System.out.println(mCultivated);
		
		mCommon.derivate();
		
		
		ArrayList<Specification> result = new ArrayList<>();
		for(Mutation m : forest.getMutations()){
			if(m.getParents().get(0).equals(forest)){
				result.add(m.getParents().get(1));
				System.out.println("ok: "+m);
			}else{
				System.out.println("ko: "+m);
			}
		}
		assertEquals(3, result.size());
		assertTrue(result.contains(meadow));
		assertTrue(result.contains(gNatural));
		assertTrue(result.contains(mystical));
		
		
		
		mCultivated.derivate();result = new ArrayList<>();
		for(Mutation m : forest.getMutations()){
			if(m.getParents().get(0).equals(forest)){
				result.add(m.getParents().get(1));
				System.out.println("ok: "+m);
			}else{
				System.out.println("ko: "+m);
			}
		}
		assertEquals(4, result.size());
		assertTrue(result.contains(meadow));
		assertTrue(result.contains(gNatural));
		assertTrue(result.contains(mystical));
		assertTrue(result.contains(common));
		assertFalse(result.contains(cultivated));
	}

}
