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
	
	public ConfigText getByKeyword(String keyword) {
		return getByField("cdMnemonicKey", keyword);
	}
}
