package be.shoktan.BeeBreedingManager.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutationTest {
	static final Logger LOG = LoggerFactory.getLogger(MutationTest.class);

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
		if(LOG.isDebugEnabled())LOG.debug(mCommon.toString());
		
		Mutation mCultivated = new Mutation(gMundane, common, cultivated, false, "");
		if(LOG.isDebugEnabled())LOG.debug(mCultivated.toString());
		
		mCommon.derivate();
		
		
		ArrayList<Specification> result = new ArrayList<>();
		for(Mutation m : forest.getMutations()){
			if(m.getParents().get(0).equals(forest)){
				result.add(m.getParents().get(1));
				if(LOG.isDebugEnabled())LOG.debug("ok: "+m);
			}else{
				if(LOG.isDebugEnabled())LOG.debug("ko: "+m);
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
				if(LOG.isDebugEnabled())LOG.debug("ok: "+m);
			}else{
				if(LOG.isDebugEnabled())LOG.debug("ko: "+m);
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
