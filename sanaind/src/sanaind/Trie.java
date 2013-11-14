/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanaind;

/**
 *
 * @author piakumpu
 */
    class TrieNode
{
    char letter;
    TrieNode[] links;
    boolean fullWord;
    TrieNode() {}
   
    TrieNode(char letter, boolean fullWord)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = fullWord;
    }


  TrieNode createTree()
    {
        return(new TrieNode('\0', false));
    }
   
    void insertWord(TrieNode root, String word)
    {
        int offset = 97;
        int l = word.length();
        char[] letters = word.toCharArray();
        TrieNode curNode = root;
       
        for (int i = 0; i < l; i++)
        {
            if (curNode.links[letters[i]-offset] == null) {
                curNode.links[letters[i]-offset] = new TrieNode(letters[i], i == l-1 ? true : false);
                curNode = curNode.links[letters[i]-offset];
            }
        }
    }

     boolean find(TrieNode root, String word)
    {
        char[] letters = word.toCharArray();
        int l = letters.length;
        int offset = 97;
        TrieNode curNode = root;
       
        int i;
        for (i = 0; i < l; i++)
        {
            if (curNode == null)
                return false;
            curNode = curNode.links[letters[i]-offset];
        }
       
        if (i == l && curNode == null){
            return false;}
       
        if (curNode != null && !curNode.fullWord){
            return false;}
       
        return true;
    }
   
     void printTree(TrieNode root, int level, char[] branch)
    {
        if (root == null)
            return;
       
        for (int i = 0; i < root.links.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.links[i], level+1, branch);   
        }
       
        if (root.fullWord)
        {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }
   

    }
