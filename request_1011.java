package main_soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rania
 */
public class request_1011 {

    public BufferedReader create_request() throws IOException {
        URL oURL = new URL("http://ec.europa.eu/eurostat/SDMX/diss-ws/NSIStdV20Service?wsdl");
        HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
        // TODO code application logic here
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "text/xml;UTF-8");
        con.setRequestProperty("SOAPAction", "");
        con.setRequestProperty("Accept-Encoding", "application/vnd.sdmx.structurespecificdata+xml ");
        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
        String xmldata = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' "
                + "xmlns:ns='http://ec.europa.eu/eurostat/sri/service/2.0/' "
                + "xmlns:mes='http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message' "
                + "xmlns:quer='http://www.SDMX.org/resources/SDMXML/schemas/v2_0/query'> "
                + "<soapenv:Header/> "
                + "<soapenv:Body> "
                + "<ns:GetGenericData> "
                + "<message:QueryMessage "
                + "xsi:schemaLocation='http://ec.europa.eu/eurostat/sri/service/2.0/SDMXMessage.xsd http://ec.europa.eu/eurostat/sri/service/2.0/%20SDMXMessage.xsd' "
                + "xmlns:generic='http://www.SDMX.org/resources/SDMXML/schemas/v2_0/generic' "
                + "xmlns:message='http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message' "
                + "xmlns:query='http://www.SDMX.org/resources/SDMXML/schemas/v2_0/query' "
                + "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'> "
                + "<message:Header> "
                + "<message:ID>onh14381631849097042</message:ID> "
                + "<message:Test>false</message:Test> "
                + "<message:Prepared>2015-07-03</message:Prepared> "
                + "<message:Sender id='online-help'/> "
                + "</message:Header> "
                + "<message:Query> "
                + "<query:DataWhere> "
                + "<query:And> "
                + "<query:Dimension id='FREQ'>A</query:Dimension> "
                + "<query:Dimension id='UNIT'>EUR_HAB</query:Dimension> "
                + "<query:Time> "
                + "<query:StartTime>2009-07-10</query:StartTime>  "
                + "<query:EndTime>2011-07-17</query:EndTime> "
                + "</query:Time> "
                + "<query:Dataflow>nama_r_e3gdp</query:Dataflow> "
                + "</query:And> "
                + "</query:DataWhere> "
                + "</message:Query> "
                + "</message:QueryMessage> "
                + "</ns:GetGenericData> "
                + "</soapenv:Body> "
                + "</soapenv:Envelope> ";
        con.setDoOutput(true);

        OutputStream reqStream = con.getOutputStream();
        reqStream.write(xmldata.getBytes());
        BufferedReader reader = null;

        if (con.getResponseCode() == 200) {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            System.out.println("Loading Error");
        }
        return reader;
    }
}
