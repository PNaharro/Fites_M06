package cat.iesesteveterradas.fites;

import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathConstants;
import javax.xml.transform.OutputKeys;

/**
 * Amb Java, crea un arxiu XML "Exercici4.xml" amb l'estructura especificada.
 *
 * - El document XML haurà de tenir l'estructura i contingut següents:
 *   <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 *   <llista>
 *       <llenguatge dificultat="alta" extensio=".c">
 *           <nom>C</nom>
 *           <any>1972</any>
 *       </llenguatge>
 *       <llenguatge dificultat="mitjana" extensio=".java">
 *           <nom>Java</nom>
 *           <any>1995</any>
 *       </llenguatge>
 *       ... (més elements llenguatge) ...
 *   </llista>
 *
 * - Generar l'estructura XML a partir d'una llista de dades proporcionada.
 * - Guardar l'estructura XML generada en un fitxer a la ruta especificada.
 *
 * Les funcions addicionals proveïdes són per a escriure el document en un fitxer XML,
 * eliminar els salts de línia innecessaris del document (per embellir-lo)
 * i obtenir els nodes d'una expressió XPath.
 *
 * Compilar i executar amb:
 * javac Exercici4.java
 * java Exercici4
 */

public class Exercici4 {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir") + "/data/exercici4/";
        String filePath = basePath + "Exercici4.xml";

        ArrayList<String[]> llista = new ArrayList<String[]>();
        llista.add(new String[] {"C", "1972", ".c", "alta"});
        llista.add(new String[] {"Java", "1995", ".java", "mitjana"});
        llista.add(new String[] {"Javascript", "1995", ".js", "baixa"});
        llista.add(new String[] {"Objective C", "1984", ".m", "baixa"});
        llista.add(new String[] {"Dart", "2011", ".dart", "mitjana"});

        // Genera l'estructura XML a partir de les dades proporcionades
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("llista");
            doc.appendChild(rootElement);

            for (String[] datos : llista) {
                Element lenguaje = doc.createElement("llenguatge");
                lenguaje.setAttribute("dificultat", datos[3]);
                lenguaje.setAttribute("extensio", datos[2]);

                Element nom = doc.createElement("nom");
                nom.appendChild(doc.createTextNode(datos[0]));
                lenguaje.appendChild(nom);

                Element any = doc.createElement("any");
                any.appendChild(doc.createTextNode(datos[1]));
                lenguaje.appendChild(any);

                rootElement.appendChild(lenguaje);
            }
              // Guarda l'estructura generada a l'arxiu 'filePath'
            write(filePath, doc);
            System.out.println("El archivo XML se ha creado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }

      
        
    }

    // Escriu un Document en un fitxer XML
    static public void write (String path, Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            trimWhitespace(doc);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    // Elimina salts de línia innecessaris del XML (per embellir-lo)
    public static void trimWhitespace(Node node) {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
            trimWhitespace(child);
        }
    }

    // Retorna els nodes d'una expressió XPath
    static public NodeList getNodeList (Document doc, String expression) {
        NodeList llista = null;
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            llista = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) { e.printStackTrace();  }
        return llista;
    }
}
