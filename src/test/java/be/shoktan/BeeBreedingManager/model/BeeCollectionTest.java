package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BeeCollection model
 * @author Wisthy
 *
 */
public class BeeCollectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAvailableSimple() {
		BeeCollection collection = new BeeCollection();
		collection.setName("test modpack");
		
		Group gMundane = new Group();
		gMundane.setName("mundane");
		
		Group gNatural = new Group();
		gNatural.setName("natural");
		gNatural.addGroup(gMundane);
		
		Specie forest = new Specie();
		forest.setName("forest");
		forest.addGroup(gNatural);
		collection.addSpecie(forest);
		
		Specie meadow = new Specie();
		meadow.setName("meadow");
		meadow.addGroup(gNatural);
		collection.addSpecie(meadow);
		
		Specie mystical = new Specie();
		mystical.setName("mystical");
		mystical.addGroup(gMundane);
		collection.addSpecie(mystical);
		
		Specie common = new Specie();
		common.setName("common");
		//collection.addSpecie(common);
		
		Specie cultivated = new Specie();
		cultivated.setName("cultivated");
		//collection.addSpecie(cultivated);
		
		Mutation mCommon = new Mutation(gNatural, gMundane, common, false, "");
		mCommon.derivate();
		
		Mutation mCultivated = new Mutation(gMundane, common, cultivated, false, "");
		mCultivated.derivate();
		
		Collection<Specie> availableBees = collection.available();
		assertTrue(availableBees.contains(common));
		assertTrue(availableBees.contains(forest));
		assertTrue(availableBees.contains(meadow));
		assertTrue(availableBees.contains(mystical));
		assertFalse(availableBees.contains(cultivated));
		assertEquals(4, availableBees.size());
	}
}
