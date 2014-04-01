package cn.frank.foundation.jaxpTest;

import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXMain {

	public static void main(String[] args) {

		XMLInputFactory xis = XMLInputFactory.newFactory();
		parseByStreamReader(xis,StAXMain.class.getResourceAsStream("SimpleXML.xml"));
		System.out.println("==========================");
		parseByEventReader(StAXMain.class.getResourceAsStream("SimpleXML.xml"), xis);
	}

	private static void parseByEventReader(InputStream inputStream, XMLInputFactory xis) {
		XMLEventReader eventReader = null;
		try {
			eventReader = xis.createXMLEventReader(inputStream);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
//				event.getEventType()

				if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    startElement.getName();
                    startElement.getAttributes();
				}

				if (event.isEndElement()) {
                     EndElement endElement = event.asEndElement();
                     endElement.getName();
				}
				
				if (event.isCharacters()) {
					Characters character = event.asCharacters();
					String data = character.getData();
					if (data.trim().length() > 0) {
						System.out.println(data);
					}
				}
				
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			try {
				if (eventReader != null) {
					eventReader.close();
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

	private static void parseByStreamReader(XMLInputFactory xis,
			InputStream inputStream) {
		XMLStreamReader reader = null;
		try {
			String preflag = null;
			reader = xis.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					if (preflag != null) {
						System.out.println();
					}
					System.out.print(String.format("<%s>", reader.getName()));
					preflag = reader.getName().toString();
					break;
				case XMLStreamConstants.CHARACTERS:
					if ("".equals(reader.getText().trim())) {
						break;
					}
					System.out.print(reader.getText());
					break;
				case XMLStreamConstants.END_ELEMENT:
					System.out.println(String.format("</%s>", reader.getName()));
					preflag = null;
					break;
				default:
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

}
