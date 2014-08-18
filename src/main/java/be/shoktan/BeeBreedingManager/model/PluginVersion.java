package be.shoktan.BeeBreedingManager.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model class to represent a particular version of a plugin providing bees
 * @author Wisthy
 *
 */
@Entity
public class PluginVersion extends ANamedEntity {
	private static final Logger LOG = LoggerFactory.getLogger(PluginVersion.class);
	
	@Enumerated(EnumType.STRING)
	private EPlugin plugin;
	
	@ManyToMany
	private Collection<Modpack> modpacks;
	
	// ********** getters & setters ********** //
	
	public String getVersionNumber(){
		return this.getName();
	}

	/**
	 * @return the plugin
	 */
	public EPlugin getPlugin() {
		return plugin;
	}

	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(EPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * @return the modpacks
	 */
	public Collection<Modpack> getModpacks() {
		return modpacks;
	}

	/**
	 * @param modpacks the modpacks to set
	 */
	public void setModpacks(Collection<Modpack> modpacks) {
		this.modpacks = modpacks;
	}


	
	// ********** others ********** //
	
	/**
	 * validate if this PluginVersion is correctly formed
	 */
	public boolean validate() {
		boolean isValid = true;
		if(plugin == null) {
			LOG.error("the plugin type is not provided");
			isValid = false;
		}
		
		// TODO wisthy - 20140818 - 11:36 - add reverse check on modpack from here?
		
		return isValid;
	}
}
