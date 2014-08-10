import org.xml.sax.SAXException;
import command.GetCommand;
import command.ValidateCommand;
import validator.SchemaType;
import xmlUtils.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 16:30
 */
public class Main {
    private static final XmlUtils utils = new XmlUtils();

    public static final String HELP = "команды:\n" +
            "validate [xmlPath, schemaPath, schemaType],\n" +
            "\tнапример: validate c:\\\\xml.xml c\\\\xsd.xsd xsd\n" +
            "\tвозможные типы схем: xsd, dtd\n\n" +
            "get elementName xmlPath,\n" +
            "\tнапример: get person c:\\persons.xml\n\n" +
            "exit\n";

    public static void main(String[] args) {
        System.out.println(HELP);
        final Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            final String initialString = sc.nextLine().replaceAll("\\n", "").replaceAll(" +", " ").trim();
            if (initialString.length() == 0) {
                continue;
            }
            final String[] command = initialString.split(" ");
            if (command[0].equals("exit")) {
                System.exit(0);
            }
            executeCommand(command);
        }
    }

    private static void executeCommand(final String[] command) {
        try {
            validateCommand(command);
        } catch (UnknownCommandException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        switch (SupportedCommands.valueOf(command[0].toUpperCase())) {
            case VALIDATE:
                try {
                    System.out.println(new ValidateCommand(utils, command[1], command[2],
                            SchemaType.valueOf(command[3].toUpperCase())).execute());
                } catch (SAXException | ParserConfigurationException | IOException | XMLStreamException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            case GET:
                try {
                    System.out.println(new GetCommand(utils, command[1], command[2]).execute());
                } catch (SAXException | ParserConfigurationException | IOException | XMLStreamException
                        | TransformerException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
        }
    }

    private static void validateCommand(final String[] command) throws UnknownCommandException {
        for (SupportedCommands c : SupportedCommands.values()) {
            if (command[0].toUpperCase().equals(c.name()) && command.length - 1 == c.params()) {
                return;
            }
        }
        throw new UnknownCommandException("Unknown command: "
                + Arrays.toString(command).replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", ""));
    }
}
