package finder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 09.08.2014 21:36
 */
public class Finder {
    public static Pair find(final String elementName, final Document xml) throws TransformerException {
        final NodeList soughtNodes = xml.getElementsByTagName(elementName);
        final StringBuilder ans = new StringBuilder();
        for (int i = 0; i < soughtNodes.getLength(); i++) {
            ans.append(nodeToString(soughtNodes.item(i)));
            ans.append("\n");
        }
        return new Pair(soughtNodes.getLength(), ans.toString().trim());
    }

    private static String nodeToString(final Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }
}
