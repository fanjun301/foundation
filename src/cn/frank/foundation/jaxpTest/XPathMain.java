package cn.frank.foundation.jaxpTest;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XPathMain {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		InputStream xmlInputStream = DomMain.class.getResourceAsStream("SimpleXML.xml");
		//use default JDK embedded DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		//parse xml
		Document document = db.parse(xmlInputStream);
		
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		try {
			//元素
			System.out.println(xpath.evaluate("/note/to", document));
			//属性
			System.out.println(xpath.evaluate("/note/body/@test", document));
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
