/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanaind;

/**
 *
 * @author Koti
 */
import java.util.NoSuchElementException;

public class Tokennizer {
    private int paikka;
    private final String lause;
    private final int pituus;
    private String delim;
  private final boolean palautettava;
 
  public Tokennizer(String lause) {

      this(lause, " \t\n\r\f", false);
 }

  
  public Tokennizer(String lause, String delim) {
    this(lause, delim, false);
}
 
  public Tokennizer(String lause, String delim, boolean returnDelims) {
    pituus = lause.length();
    this.lause = lause;
    this.delim = delim;
    this.palautettava = returnDelims;
    this.paikka = 0;
}
 
  public boolean lisaaTokeneita() {

      if (! palautettava){
         while (paikka < pituus && delim.indexOf(lause.charAt(paikka)) >= 0)
            paikka++;
        }
        return paikka < pituus;
}
 
  public String seuraava(String delim) throws NoSuchElementException {
    this.delim = delim;
    return seuraava();
}

  public String seuraava() throws NoSuchElementException {
      
    if (paikka < pituus && delim.indexOf(lause.charAt(paikka)) >= 0)   {
        if (palautettava)  return lause.substring(paikka, ++paikka);
    while (++paikka < pituus && delim.indexOf(lause.charAt(paikka)) >= 0);
    }
    if (paikka < pituus){
        int start = paikka;
        while (++paikka < pituus && delim.indexOf(lause.charAt(paikka)) < 0);
            
            return lause.substring(start, paikka);
    }  
    
    throw new NoSuchElementException();
}

  public boolean hasMoreElements() {
    return lisaaTokeneita();
}
 
  public Object nextElement() throws NoSuchElementException{
    return seuraava();

  }
 
  public int countTokens(){
        int summa = 0;
        int delimiterCount = 0;
        boolean loydetty = false; 
        int apu = paikka;

        while (apu < pituus){
            if (delim.indexOf(lause.charAt(apu++)) >= 0){
                if (loydetty) {
                    summa++;
                    loydetty = false;
                }
                delimiterCount++; // Increment for this delimiter
            }
            else{
                loydetty = true; 
                while (apu < pituus && delim.indexOf(lause.charAt(apu)) < 0)
                    ++apu;
            }
        }
        if (loydetty)
            summa++;
             return palautettava ? summa + delimiterCount : summa;
    }
    
}
