/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.ConfigText;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ConfigTextDao extends AbstractDao<ConfigText, Long>{
	
	@Inject
	public ConfigTextDao(EntityManagerFactory emf) {
		super(emf, ConfigText.class);
	}
	
	/**
	 * Gets a {@link ConfigText} by his mnemonicKey.
	 * @param mnemonicKey the mnemonic key, null if the text is not found.
	 * @return the text.
	 */
	public ConfigText getByKeyword(String mnemonicKey) {
		return getByField("cdMnemonicKey", mnemonicKey);
	}
}
