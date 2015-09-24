/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_soap_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author rania
 */
public class Main_soap_1011 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // TODO code application logic here
        BufferedReader reader1011; 
       
        request_1011 new_soap_request1011 = new request_1011();
        reader1011 = new_soap_request1011.create_request();
        retrieve_data_1011 new_retrieve_data1011 = new retrieve_data_1011();
        new_retrieve_data1011.retrieve(reader1011);
        
     }
    
}
