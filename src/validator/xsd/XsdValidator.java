package validator.xsd;

import org.xml.sax.SAXException;
import validator.Validator;
import validator.exeption.InvalidXmlException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 13:17
 */

public class XsdValidator implements Validator {
    @Override
    public void validate(final String xmlSource, final String schemaSource)
            throws SAXException, InvalidXmlException, IOException {
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new StreamSource(new File(schemaSource)));
        javax.xml.validation.Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new File(xmlSource)));
        } catch (SAXException e) {
            throw new InvalidXmlException("Reason: " + e.getLocalizedMessage());
        }
    }
}
