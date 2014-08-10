package validator.dtd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import validator.Validator;
import validator.exeption.InvalidXmlException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 13:16
 */

public class DtdValidator implements Validator {
    @Override
    public void validate(final String xmlPath, final String schemaPath)
            throws IOException, ParserConfigurationException, SAXException, XMLStreamException, InvalidXmlException {
        validateXml(formXml(xmlPath, schemaPath));
    }

    public void validateXml(final String xml) throws ParserConfigurationException, IOException, InvalidXmlException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new SimpleErrorHandler());
        try {
            builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
        } catch (SAXException e) {
            throw new InvalidXmlException("Reason: " + e.getLocalizedMessage());
        }
    }


    private String formXml(final String xmlPath, final String schemaPath)
            throws XMLStreamException, IOException, ParserConfigurationException, SAXException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent dtd = eventFactory
                .createDTD("\n<!DOCTYPE "
                        + getRootElemName(xmlPath)
                        + " SYSTEM \""
                        + schemaPath
                        + "\">\n");

        XMLInputFactory inFactory = XMLInputFactory.newInstance();
        XMLOutputFactory outFactory = XMLOutputFactory.newInstance();
        XMLEventReader reader = inFactory
                .createXMLEventReader(new StreamSource(new File(xmlPath)));
        reader = new DtdReplacer(reader, dtd);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        XMLEventWriter writer = outFactory.createXMLEventWriter(os);
        writer.add(reader);
        writer.flush();

        return os.toString();
    }

    private String getRootElemName(final String xmlPath)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.
                newInstance();
        dbFactory.setValidating(false);

        Document doc = dbFactory.newDocumentBuilder().parse(new File(xmlPath));
        Element root = doc.getDocumentElement();
        return root.getNodeName();
    }
}
