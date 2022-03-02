package com.ipiecoles.java.mdd324.homepage;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HoroscopeService {

    private static Object Horoscope;

    public static List<Horoscope> getHoroscope() throws Exception, SAXException, ParserConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("https://www.asiaflash.com/horoscope/rss_horojour_taureau.xml");
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("description");
        String horoscope = nList.item(1).getTextContent();

        System.out.println(horoscope);

        //Entité Java => JSON
        Genson g = new GensonBuilder().useRuntimeType(true).create();
        String jsonOutput = g.serialize(horoscope);
        System.out.println(jsonOutput);
        return (List<com.ipiecoles.java.mdd324.homepage.Horoscope>) Horoscope;

    }
}
