/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinMax;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;

/**
 *
 * @author AluCiclesGS1
 */
public class Practica3 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcio;

        do {
            System.out.println("Select an exercise to execute:");
            System.out.println("1. Exercise 1 - Show data of Clash players.");
            System.out.println("2. Exercise 2 - Find the player with the most trophies in Clash.");
            System.out.println("3. Exercise 3 - Find the maximum and minimum temperature in 2015.");
            System.out.println("4. Exercise 4 - Find the maximum and minimum temperature in 2016.");
            System.out.println("5. Exercise 5 - Find the maximum and minimum temperature in 2017.");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    System.out.println("Showing data of Clash players.");
                    platerStats();
                    break;
                case 2:
                    System.out.println("Finding the player with the most trophies in Clash.");
                    maxThropies();
                    break;
                case 3:
                    System.out.println("Finding the maximum and minimum temperature in 2015.");
                    temp2015();
                    break;
                case 4:
                    System.out.println("Finding the maximum and minimum temperature in 2016.");
                    temp2016();
                    break;
                case 5:
                    System.out.println("Finding the maximum and minimum temperature in 2017.");
                    temp2017();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (opcio != 0);
    }

    // TODO code application logic here

    public static Document openXMLFile(String xmlFile) throws Exception {
        File fxml = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = (Document) dBuilder.parse(fxml);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private static void platerStats() throws Exception {
    Document doc = openXMLFile("./src/MinMax/clash.xml");
    NodeList players = doc.getElementsByTagName("Player");

    for (int i = 0; i < players.getLength(); i++) {
        Element player = (Element) players.item(i);
        
        System.out.println("Name: " + player.getElementsByTagName("name").item(0).getTextContent());
        System.out.println("Stars: " + player.getElementsByTagName("stars").item(0).getTextContent());
        System.out.println("Trophies: " + player.getElementsByTagName("trophies").item(0).getTextContent());
        System.out.println("Level: " + player.getElementsByTagName("level").item(0).getTextContent());
        System.out.println("Gold: " + player.getElementsByTagName("gold").item(0).getTextContent());
        System.out.println("Gems: " + player.getElementsByTagName("gems").item(0).getTextContent());

        NodeList gameList = player.getElementsByTagName("game");
        for (int x = 0; x < gameList.getLength(); x++) {
            Element game = (Element) gameList.item(x);
            System.out.println("Match: " + game.getElementsByTagName("result").item(0).getTextContent());
            System.out.println("Duration: " + game.getElementsByTagName("duration").item(0).getTextContent());
            System.out.println("Date played: " + game.getElementsByTagName("date").item(0).getTextContent());
        }
    }
}


    private static void maxThropies() throws Exception {
    Document doc = openXMLFile("./src/MinMax/clash.xml");
    NodeList players = doc.getElementsByTagName("Player");

    double maxTrophies = 0;
    Element playerFinal = (Element) players.item(0); // Initialize playerFinal to the first player

    for (int i = 0; i < players.getLength(); i++) {
        Element player = (Element) players.item(i);

        double trophies = Double.parseDouble(player.getElementsByTagName("trophies").item(0).getTextContent());

        if (trophies > maxTrophies) {
            maxTrophies = trophies;
            playerFinal = player;
        }
    }

    System.out.println("The player with the most trophies is: " + playerFinal.getElementsByTagName("name").item(0).getTextContent());
    System.out.println("Stars: " + playerFinal.getElementsByTagName("stars").item(0).getTextContent());
    System.out.println("Trophies: " + playerFinal.getElementsByTagName("trophies").item(0).getTextContent());
    System.out.println("Level: " + playerFinal.getElementsByTagName("level").item(0).getTextContent());
    System.out.println("Gold: " + playerFinal.getElementsByTagName("gold").item(0).getTextContent());
    System.out.println("Gems: " + playerFinal.getElementsByTagName("gems").item(0).getTextContent());

    NodeList gameList = playerFinal.getElementsByTagName("game");
    for (int x = 0; x < gameList.getLength(); x++) {
        Element game = (Element) gameList.item(x);
        System.out.println("Match: " + game.getElementsByTagName("result").item(0).getTextContent());
        System.out.println("Duration: " + game.getElementsByTagName("duration").item(0).getTextContent());
        System.out.println("Date played: " + game.getElementsByTagName("date").item(0).getTextContent());
    }
}


    private static void temp2015() throws Exception {
        Document doc = openXMLFile("./src/MinMax/meteo2015.xml");
        NodeList cds = doc.getElementsByTagName("element");
        double maxTemp = 0;
        Element finalElement = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmax").getLength() > 0) { //check if it isn't empty so it doesn't crash
                double temp = NumberFormat.getInstance().parse(cde.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();

                if (temp > maxTemp) {
                    maxTemp = temp;
                    finalElement = cde;
                }
            }
        }
        System.out.println("Max Temperature[ "+finalElement.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalElement.getElementsByTagName("horatmax").item(0).getTextContent()+" ] = "+finalElement.getElementsByTagName("tmax").item(0).getTextContent()+"C");
        
        double minTemp = 0;
        Element finalMinTemp = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmin").getLength() > 0) {
                double temp = NumberFormat.getInstance().parse(cde.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();

                if (temp > minTemp) {
                    minTemp = temp;
                    finalMinTemp = cde;
                }
            }
        }
        System.out.println("Min Temperature[ "+finalMinTemp.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalMinTemp.getElementsByTagName("horatmin").item(0).getTextContent()+" ] = "+finalMinTemp.getElementsByTagName("tmin").item(0).getTextContent()+"C");
        
    }
      private static void temp2016() throws Exception {
        Document doc = openXMLFile("./src/MinMax/meteo2016.xml");
        NodeList cds = doc.getElementsByTagName("element");
        double maxTemp = 0;
        Element finalElement = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmax").getLength() > 0) {
                double temp = NumberFormat.getInstance().parse(cde1.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();

                if (temp > maxTemp) {
                    maxTemp = temp;
                    finalElement = cde;
                }
            }
        }
        System.out.println("Max Temperature[ "+finalElement.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalElement.getElementsByTagName("horatmax").item(0).getTextContent()+" ] = "+finalElement.getElementsByTagName("tmax").item(0).getTextContent()+"C");
        
        double minTemp = 0;
        Element finalMinTemp = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmin").getLength() > 0) {
                double temp = NumberFormat.getInstance().parse(cde.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();

                if (temp > minTemp) {
                    minTemp = temp;
                    finalMinTemp = cde;
                }
            }
        }
        System.out.println("Min Temperature[ "+finalMinTemp.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalMinTemp.getElementsByTagName("horatmin").item(0).getTextContent()+" ] = "+finalMinTemp.getElementsByTagName("tmin").item(0).getTextContent()+"C");
        
    }
        private static void temp2017() throws Exception {
        Document doc = openXMLFile("./src/MinMax/meteo2017.xml");
        NodeList cds = doc.getElementsByTagName("element");
        double maxTemp = 0;
        Element finalElement = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmax").getLength() > 0) {
                double temp = NumberFormat.getInstance().parse(cde.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();

                if (temp > maxTemp) {
                    maxTemp = temp;
                    finalElement = cde;
                }
            }
        }
        System.out.println("Max Temperature[ "+finalElement.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalElement.getElementsByTagName("horatmax").item(0).getTextContent()+" ] = "+finalElement.getElementsByTagName("tmax").item(0).getTextContent()+"C");
        
        double minTemp = 0;
        Element finalMinTemp = null;
        for (int i = 0; i < cds.getLength(); i++) {
            Element cde = (Element) cds.item(i);

            if (cde.getElementsByTagName("tmin").getLength() > 0) {
                double temp = NumberFormat.getInstance().parse(cde.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();

                if (temp > minTemp) {
                    minTemp = temp;
                    finalMinTemp = cde;
                }
            }
        }
        System.out.println("Min Temperature[ "+finalMinTemp.getElementsByTagName("fecha").item(0).getTextContent()+" "+finalMinTemp.getElementsByTagName("horatmin").item(0).getTextContent()+" ] = "+finalMinTemp.getElementsByTagName("tmin").item(0).getTextContent()+"C");
        
    }
}

