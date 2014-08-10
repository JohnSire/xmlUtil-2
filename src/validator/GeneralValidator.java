package validator;

import org.xml.sax.SAXException;
import validator.exeption.InvalidXmlException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 13:13
 */
public class GeneralValidator {
    public void validate(final String xmlPath, final String schemaPath, final SchemaType type)
            throws SAXException, IOException, InvalidXmlException, ParserConfigurationException, XMLStreamException {
        type.getValidator().validate(xmlPath, schemaPath);
    }
}
