package command;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 21:30
 */
public interface Command {
    String execute() throws SAXException, ParserConfigurationException, IOException, XMLStreamException, TransformerException;
}
