package validator.dtd;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 13:24
 */

public class DtdReplacer extends EventReaderDelegate {
    private final XMLEvent dtd;
    private boolean sendDtd = false;

    public DtdReplacer(final XMLEventReader reader, final XMLEvent dtd) {
        super(reader);
        if (dtd.getEventType() != XMLEvent.DTD) {
            throw new IllegalArgumentException("" + dtd);
        }
        this.dtd = dtd;
    }

    @Override
    public XMLEvent nextEvent() throws XMLStreamException {
        if (sendDtd) {
            sendDtd = false;
            return dtd;
        }
        XMLEvent evt = super.nextEvent();
        if (evt.getEventType() == XMLEvent.START_DOCUMENT) {
            sendDtd = true;
        } else if (evt.getEventType() == XMLEvent.DTD) {
            return super.nextEvent();
        }
        return evt;
    }
}
