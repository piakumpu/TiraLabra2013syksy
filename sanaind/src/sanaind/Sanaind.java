/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanaind;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author piakumpu
 */
public class Sanaind {

    /**
     * @param args the command line arguments
     */
   // public static Scanner lukija = new Scanner(System.in);
    public static ArrayList<String> lauseet = new ArrayList<String>();
    public static ArrayList<String> sanat = new ArrayList<String>();
    public static TrieNode puu;
    
    public static void lukukone() {
        try {
            File apuri = new File("C:\\Users\\Koti\\Dropbox\\Public\\Yliopisto\\HY13-14\\tiraharkka\\TiraLabra2013syksy\\apu.txt");
            
            
            Scanner lukija = new Scanner(apuri);
            
            while (lukija.hasNextLine()) {
                lauseet.add(lukija.nextLine()); 
                sanatOma();
            }
            
            int i =0;
            
            while (i < sanat.size()) {
                System.out.println(sanat.get(i));
                i=i+1;
            }
            i =0;
            
            while (i < lauseet.size()) {
                System.out.println(lauseet.get(i));
                i=i+1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sanaind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void sanatOma() {
        
        Tokennizer lause = new Tokennizer(lauseet.get(lauseet.size()-1)," ");
        while (lause.lisaaTokeneita()) {
                sanat.add(trimmeri(lause.seuraava()));
        }
    
    }
    
    /* JAvan apuja käytetty
    public static void sanat() {
        
        StringTokenizer lause = new StringTokenizer(lauseet.get(lauseet.size()-1)," ");
        while (lause.hasMoreTokens()) {
                sanat.add(trimmeri(lause.nextToken()));
        }
    
    }*/
    
    public static String trimmeri(String sana) {
           
        sana = sana.replace(",", "");
        sana = sana.replace("´", "");
        sana = sana.replace(";", "");
        sana = sana.replace(":", "");
        sana = sana.replace("?", "");
        sana = sana.replace("!", "");
        sana = sana.replace("'", "");
        
        sana= sana.toLowerCase();
        
        return sana;
    }
     
    public static void etsija() {
                   
            Scanner etsija = new Scanner(System.in);
            String etsittava = etsija.nextLine();
            String apulause;
            int i = 0;
            while (etsittava != "") {
                
                if (sanat.contains(etsittava)==true) {
                    
                    while (i < lauseet.size()) {
                        apulause = trimmeri(lauseet.get(i));
                        apulause = apulause.toLowerCase();

                       // System.out.println(apulause.indexOf(etsittava));
                        if (apulause.indexOf(etsittava)!=-1) {
                           System.out.println("Löytyi sana: " + etsittava + " riviltä " + (i +1));
                        }
                                                    
                        i++;
                }}
                else {System.out.println("Ei löytynyt sanaa: " + etsittava);}
               etsittava = etsija.nextLine();
               i =0;
                
            }
           
    }
   
    

    
    public static void main(String[] args) {
        
        System.out.println("Anna tutkittava teksti: ");
        lukukone();
        
         System.out.println("Anna etsittävä sana: ");
        etsija();
        
        /* Koitetaan ekana saada toimimaan muulla keinoin
        TrieNode tree = puu.createTree();
         int i=0;  
         System.err.println("toimii");
        while (i < sanat.size()){
            puu.insertWord(tree, sanat.get(i));
            i=i+1;
        }
       System.err.println("toimii1");
        char[] branch = new char[50];
        puu.printTree(tree, 0, branch);
       System.err.println("toimii2");
        String searchWord = "all";
        if (puu.find(tree, searchWord))
        {
            System.out.println("The word was found");
        }
        else
        {
            System.out.println("The word was NOT found");
        }
    */
        
    }
}
   


