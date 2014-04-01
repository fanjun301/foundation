package cn.frank.foundation.jaxpTest;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomMain {

	public static void main(String[] args) {

		InputStream xmlInputStream = null;
		try {
			xmlInputStream = DomMain.class.getResourceAsStream("SimpleXML.xml");
			//use default JDK embedded DOM parser
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse xml
			Document document = db.parse(xmlInputStream);
			//get ROOT
			Element root = document.getDocumentElement();
			System.out.println(String.format("Root element name : %s", root.getTagName()));
			//遍历XML节点
			trace(root);
			
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

	private static void trace(Node node) {
		
		NodeList childNodes = node.getChildNodes();
		//1 如果是叶子节点，打印
		if (childNodes.getLength()  == 1 && childNodes.item(0).getNodeType() == Node.TEXT_NODE) {
			System.out.println(String.format("<%s>%s</%s>", node.getNodeName(), node.getTextContent(), node.getNodeName()));
		}
		//2 非叶子节点递归
		if (node.getNodeType() == Node.ELEMENT_NODE && childNodes.getLength() > 1) {
			System.out.println(String.format("<%s>", node.getNodeName()));
			for (int i = 0 ; i < childNodes.getLength() ; i++) {
				Node tmpNode = childNodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					trace(tmpNode);
				}
			}
			System.out.println(String.format("</%s>", node.getNodeName()));			
		}
	}

}
