package main_soap_1011;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rania
 */
public class data_UPD {

    static Connection conn;
    static String connectionUrl;

    /**
     * ******* method convert AT432=---->AT **********
     */
    public String trim(String subgeo) {
        return subgeo.substring(0, 2);
    }

    String create_connection() throws IOException, ClassNotFoundException, SQLException {
        Properties pro = new Properties();
        InputStream is;

        //get data from database_config.ini file
        pro.load(new FileInputStream("database_config.ini"));
        String port = pro.getProperty("port");
        String server = pro.getProperty("server");
        String database = pro.getProperty("databaseName");
        String user = pro.getProperty("user");
        String passwd = pro.getProperty("password");

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connectionUrl = "jdbc:sqlserver://" + server + ":" + port + ";"
                + "databaseName=" + database + ";user=" + user + ";password=" + passwd + ";";

        return connectionUrl;
    }

    void insertquery(ArrayList<String> ar, String GDP, String Year) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        String geo = trim(ar.get(1));
        int year = 0;
        connectionUrl = create_connection();
        conn = DriverManager.getConnection(connectionUrl);
        if (conn != null) {
            //-----------INSERT------------//
            year = Integer.parseInt(Year);
            Statement s2 = conn.createStatement();
            int rs2 = s2.executeUpdate("UPDATE gdp_nuts3 SET GDP= '" + GDP + "' WHERE Year='" + year + "' AND SubGeo='" + ar.get(1) + "'");
        }

        int count = 1;
        conn.close();
        Connection conn2 = DriverManager.getConnection(connectionUrl);
        if (conn2 != null) {
            Statement s3 = conn2.createStatement();
            ResultSet rs1 = s3.executeQuery("SELECT * FROM gdp_nuts3");
            while (rs1.next()) {

                if (year == rs1.getInt("Year")) {
                    if (ar.get(1).equals(rs1.getString("SubGeo"))) {
                        count = 0;
                    }
                }
            }
            if (count != 0) {
                Statement s4 = conn2.createStatement();
                int rs = s4.executeUpdate("INSERT INTO gdp_nuts3"
                        + "(Unit,SubGeo,Geo,Year,GDP) VALUES"
                        + "('" + ar.get(0) + "' ,'" + ar.get(1) + "','" + geo + "','" + year + "','" + GDP + "')");
            } 
        }
        conn2.close();
    }
}
