package com.ipiecoles.java.mdd324.homepage;

import com.ipiecoles.java.mdd324.homepage.utils.Utils;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {


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
    }

}
