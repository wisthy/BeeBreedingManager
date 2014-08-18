package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model class to represent a modpack
 * @author Wisthy
 *
 */
public class Modpack extends ANamedEntity {
	private static final Logger LOG = LoggerFactory.getLogger(Modpack.class);
	private Collection<PluginVersion> plugins;
	
	
	
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
	
	
	
	// ********** others ********** //
	
	/**
	 * run validations check on the plugins list to ensure it is correct.
	 */
	public boolean validatePluginList(){
		if(LOG.isTraceEnabled())LOG.trace("starting validation of the plugin list");
		boolean isValid = true;
		for(PluginVersion pluginVersion : plugins){
			if(pluginVersion == null){
				LOG.error("there is a null plugin in the list");
				isValid = false;
				continue;
			}
			pluginVersion.validate();
		}
		if(LOG.isTraceEnabled())LOG.trace("end of the validation of the plugin list");
		return isValid;
	}
}
