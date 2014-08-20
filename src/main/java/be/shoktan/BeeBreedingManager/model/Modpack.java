package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model class to represent a modpack
 * @author Wisthy
 *
 */
public class Modpack extends ANamedEntity {
	private static final Logger LOG = LoggerFactory.getLogger(Modpack.class);
	
	@ManyToMany(mappedBy = "modpack")
	private Collection<PluginVersion> plugins;
	
	@OneToMany(mappedBy = "modpack")
	private Collection<BeeCollection> collections;
	
	
	
	// ********** getters & setters ********** //

	/**
	 * @return the plugins
	 */
	public Collection<PluginVersion> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins the plugins to set
	 */
	public void setPlugins(Collection<PluginVersion> plugins) {
		this.plugins = plugins;
	}
	
	/**
	 * @return the collections
	 */
	public Collection<BeeCollection> getCollections() {
		return collections;
	}

	/**
	 * @param collections the collections to set
	 */
	public void setCollections(Collection<BeeCollection> collections) {
		this.collections = collections;
	}
	
	
	
	// ********** others ********** //
	


	/**
	 * run validations check on the plugins list to ensure it is correct.
	 */
	public boolean validatePluginList(){
		if(LOG.isTraceEnabled())LOG.trace("starting validation of the plugin list");
		boolean isValid = true;
		
		SortedSet<EPlugin> pluginSet = new TreeSet<>(); 
		
		for(PluginVersion pluginVersion : plugins){
			if(pluginVersion == null){
				LOG.error("there is a null plugin in the list");
				isValid = false;
				continue;
			}
			EPlugin plugin = pluginVersion.getPlugin();
			if(pluginSet.contains(plugin)){
				LOG.error("the plugin <"+plugin+"> is present more than one time");
				isValid = false;
			}else{
				pluginSet.add(plugin);
			}
			isValid = pluginVersion.validate() && isValid;
		}
		if(LOG.isTraceEnabled())LOG.trace("end of the validation of the plugin list with result "+isValid);
		return isValid;
	}
}
