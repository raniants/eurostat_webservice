package main_soap_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rania
 */
public class retrieve_data_1011 {

    public void retrieve(BufferedReader reader) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException, SQLException {
        String line, GDP = null, Year = null;
        int temp = 0, count = 0;

        ArrayList<String> ar = new ArrayList<>();

        while ((line = reader.readLine()) != null) {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(line));

            org.w3c.dom.Document doc = builder.parse(src);

            NodeList nList = doc.getElementsByTagName("generic:Obs");
            NodeList extras = doc.getElementsByTagName("generic:Value");
            NodeList nodeList = doc.getElementsByTagName("generic:ObsValue");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                Element eElement = (Element) nNode;
                int y = i % 2;
                if (y == 0) {
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (null != extras.item(temp).getAttributes().getNamedItem("value").getNodeValue()) {
                            switch (extras.item(temp).getAttributes().getNamedItem("value").getNodeValue()) {
                                case "na":
                                    count = 4;
                                    break;
                                case "s":
                                    count = 4;
                                    break;
                                default:
                                    count = 2;
                                    break;
                            }
                        }
                        for (int j = 0; j <= count; j++) {
                            if (j != 0) {
                                temp = temp + 1;
                            }
                            if ("c".equals(extras.item(temp).getAttributes().getNamedItem("value").getNodeValue()) || "b".equals(extras.item(temp).getAttributes().getNamedItem("value").getNodeValue()) || "p".equals(extras.item(temp).getAttributes().getNamedItem("value").getNodeValue())) {
                                count = count + 1;
                            }
                            if (!"OBS_STATUS".equals(extras.item(temp).getAttributes().getNamedItem("concept").getNodeValue()) && !"OBS_FLAG".equals(extras.item(temp).getAttributes().getNamedItem("concept").getNodeValue())) {
                                String Freq = extras.item(temp).getAttributes().getNamedItem("value").getNodeValue();
                                ar.add(Freq);
                            
                            }
                        }
                        temp = temp + 1;
                    }
                }
                GDP = nodeList.item(i).getAttributes().getNamedItem("value").getNodeValue();

                Year = eElement.getElementsByTagName("generic:Time").item(0).getTextContent();
                data_UPD DI = new data_UPD();
            
                DI.insertquery(ar, GDP, Year);
                if (y != 0) {
                    ar.clear();
                }
            }
        }
    }
}
