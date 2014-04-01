package cn.frank.foundation.jaxpTest;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class TransformMain {

	public static void main(String[] args) {

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(/*style sheet xsl*/);
			
			/**
			 * Source:
			 * DOMSource, JAXBSource, SAXSource, StAXSource, StreamSource
			 * Result:
			 * DOMResult, JAXBResult, SAAJResult, SAXResult, StAXResult, StreamResult
			 */
			transformer.transform(null, null);
			
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
