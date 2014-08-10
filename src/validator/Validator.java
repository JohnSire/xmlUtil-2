package validator;

import org.xml.sax.SAXException;
import validator.exeption.InvalidXmlException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 09.08.2014 17:04
 */
public interface Validator {
    void validate(String xmlPath, String schemaPath) throws SAXException, InvalidXmlException, IOException, ParserConfigurationException, XMLStreamException;
}
