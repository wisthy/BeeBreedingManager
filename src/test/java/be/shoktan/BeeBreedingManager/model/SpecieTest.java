package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Specie model class
 * @author Wisthy
 *
 */
public class SpecieTest {

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
		mCommon.derivate();
		
		Mutation mCultivated = new Mutation(gMundane, common, cultivated, false, "");
		mCultivated.derivate();
		
		Collection<Specie> mutationResultWith = forest.mutationResultWith(common);
		for(Specie s : mutationResultWith){
			assertEquals(cultivated, s);
		}
	}

}
