package command;

import org.xml.sax.SAXException;
import finder.Pair;
import xmlUtils.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 21:35
 */
public class GetCommand implements Command {
    private final XmlUtils utils;
    private final String elem;
    private final String xmlPath;

    public GetCommand(final XmlUtils utils, final String elem, final String xmlPath) {
        this.utils = utils;
        this.elem = elem;
        this.xmlPath = xmlPath;
    }

    @Override
    public String execute()
            throws SAXException, ParserConfigurationException, IOException, XMLStreamException, TransformerException {
        final Pair ans = utils.find(elem, xmlPath);
        return ans.getCount() == 0 ? "not found" : "count: " + ans.getCount() + "\n" + ans.getAns();
    }
}
