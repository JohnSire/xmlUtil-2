package xmlUtils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import finder.Finder;
import finder.Pair;
import validator.GeneralValidator;
import validator.SchemaType;
import validator.exeption.InvalidXmlException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 16:16
 */
public class XmlUtils {
    private final GeneralValidator validator = new GeneralValidator();

    public void validate(final String xmlPath, final String schemaPath, final SchemaType type)
            throws SAXException, XMLStreamException, ParserConfigurationException, InvalidXmlException, IOException {
        validator.validate(xmlPath, schemaPath, type);
    }

    public Pair find(final String elementName, final String xmlPath)
            throws TransformerException, ParserConfigurationException, IOException, SAXException {
        return Finder.find(elementName, parseDoc(xmlPath));
    }

    private Document parseDoc(final String xmlPath) throws IOException, SAXException, ParserConfigurationException {
        File stocks = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        dbFactory.setValidating(false);
        return dBuilder.parse(stocks);
    }
}
