/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanaind;

/**
 *
 * @author piakumpu
 */
  public class Trie {


    char kirjain;
    Trie[] links;
    boolean kokosana;
    
    Trie() {}
    
    Trie(char kirjain, boolean kokosana) {
        this.kirjain = kirjain;
        links = new Trie[26];
        this.kokosana = kokosana;
    }
  
     Trie createTree(){
        return(new Trie('\0', false));
    }
    
   void insertWord(Trie root, String sana) {
        int offset = 97;
        int l = sana.length();
        char[] kirjaimet = sana.toCharArray();
        Trie curNode = root;
        
        for (int i = 0; i < l; i++)  {
            //System.out.println(kirjaimet[i]);
            if (curNode.links[kirjaimet[i]-offset] == null) {
                curNode.links[kirjaimet[i]-offset] = new Trie(kirjaimet[i], i == l-1 ? true : false);
            }
            curNode = curNode.links[kirjaimet[i]-offset];
        }
        
    }

    static boolean find(Trie root, String sana) {
        char[] kirjaimet = sana.toCharArray();
        int l = kirjaimet.length;
        int offset = 97;
        Trie curNode = root;
        
        int i;
        for (i = 0; i < l; i++){
            if (curNode == null)
                return false;
            curNode = curNode.links[kirjaimet[i]-offset];
        }
        
        if (i == l && curNode == null)
            return false;
        
        if (curNode != null && !curNode.kokosana)
            return false;
        
        return true;
    }
    
    static void printTree(Trie root, int level, char[] branch){
        if (root == null)
            return;
        
        for (int i = 0; i < root.links.length; i++){
            branch[level] = root.kirjain;
            printTree(root.links[i], level+1, branch);    
        }
        
        if (root.kokosana) {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }


}
