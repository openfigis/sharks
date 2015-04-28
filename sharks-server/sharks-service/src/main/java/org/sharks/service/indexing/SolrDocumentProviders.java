/**
 * 
 */
package org.sharks.service.indexing;

import static org.sharks.service.util.ReflectionUtils.getPropertyDescriptors;
import static org.sharks.service.util.ReflectionUtils.getPropertyValue;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.apache.solr.common.SolrInputDocument;
import org.sharks.service.indexing.SolrIndexingService.SolrDocumentProvider;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SolrDocumentProviders {

	public static final String ID_FIELD = "id";
	public static final String TITLE_FIELD = "title";
	public static final String DESCRIPTION_FIELD = "description";
	public static final String TYPE_FIELD = "documentType";

	public static SolrDocumentProvider<Measure> MEASURE = new SolrDocumentProvider<Measure>() {

		@Override
		public Class<Measure> getType() {
			return Measure.class;
		}

		@Override
		public SolrInputDocument getDocument(Measure measure) {
			SolrInputDocument document = new SolrInputDocument();

			document.addField(TYPE_FIELD, getType().getSimpleName());
			document.addField(ID_FIELD, measure.getCode());
			document.addField(TITLE_FIELD, measure.getTitle());
			
			return document;
		}
	};

	public static SolrDocumentProvider<PoA> POA = new SolrDocumentProvider<PoA>() {

		@Override
		public Class<PoA> getType() {
			return PoA.class;
		}

		@Override
		public SolrInputDocument getDocument(PoA poa) {
			SolrInputDocument document = new SolrInputDocument();

			document.addField(TYPE_FIELD, getType().getSimpleName());
			document.addField(ID_FIELD, poa.getCode());
			document.addField(TITLE_FIELD, poa.getTitle());

			return document;
		}
	};

	public static <T> SolrDocumentProvider<T> buildGenericDocumentProvider(Class<T> type) {

		List<PropertyDescriptor> descriptors = getPropertyDescriptors(type);

		return new SolrDocumentProvider<T>() {

			@Override
			public Class<T> getType() {
				return type;
			}

			@Override
			public SolrInputDocument getDocument(T item) {
				SolrInputDocument document = new SolrInputDocument();

				document.addField(TYPE_FIELD, getType().getSimpleName());

				for (PropertyDescriptor descriptor:descriptors) {
					document.addField(descriptor.getName(), getPropertyValue(descriptor, item));
				}

				return document;
			}
		};
	}

}
