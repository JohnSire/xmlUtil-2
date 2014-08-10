package command;

import org.xml.sax.SAXException;
import validator.SchemaType;
import validator.exeption.InvalidXmlException;
import xmlUtils.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 21:30
 */
public class ValidateCommand implements Command {
    private final XmlUtils utils;
    private final String xmlPath;
    private final String schemaPath;
    private final SchemaType type;

    public ValidateCommand(final XmlUtils utils, final String xmlPath, final String schemaPath, final SchemaType type) {
        this.xmlPath = xmlPath;
        this.schemaPath = schemaPath;
        this.type = type;
        this.utils = utils;
    }

    @Override
    public String execute() throws SAXException, ParserConfigurationException, IOException, XMLStreamException {
        try {
            utils.validate(xmlPath, schemaPath, type);
            return "valid";
        } catch (InvalidXmlException e) {
            return "invalid";
        }
    }
}
