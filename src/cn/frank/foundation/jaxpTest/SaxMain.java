package cn.frank.foundation.jaxpTest;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxMain {

	public static void main(String[] args) {

		InputStream xmlInputStream = null;
		try {
			xmlInputStream = SaxMain.class.getResourceAsStream("SimpleXML.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(xmlInputStream, new DefaultHandler(){
				
				String preflag = null;

				@Override
				public void startDocument() throws SAXException {
					super.startDocument();
					System.out.println("Start Document");
				}

				@Override
				public void endDocument() throws SAXException {
					super.endDocument();
					System.out.println("End Document");
				}

				@Override
				public void startPrefixMapping(String prefix, String uri)
						throws SAXException {
					super.startPrefixMapping(prefix, uri);
				}

				@Override
				public void endPrefixMapping(String prefix) throws SAXException {
					super.endPrefixMapping(prefix);
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (preflag != null) {
                    	System.out.println();
					}  
					System.out.print("<" + qName+">");
					preflag = qName;
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					System.out.println("</" + qName+">");
					preflag = null;
				}

				@Override
				public void characters(char[] ch, int start, int length)
						throws SAXException {
					String content = String.copyValueOf(ch, start, length).trim();
					if (content.length() > 0) {
						System.out.print(content);
					}
				}
			});
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (xmlInputStream != null) {
					xmlInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
