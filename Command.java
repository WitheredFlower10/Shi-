/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *  
 * Cette classe contient des informations sur une commande émise par l'utilisateur.
 * Une commande se compose actuellement de deux chaînes de caractères : un mot de commande et un second mot.
 * 
 * 
 * La façon dont cela est utilisé est la suivante : les commandes sont déjà vérifiées pour être des mots de commande valides.
 * Si l'utilisateur a saisi une commande non valide (un mot qui n'est pas
 * connu), le mot de commande est alors <null>.
 *
 * Si la commande ne comporte qu'un seul mot, le deuxième mot est <null>.
 * @author HAKIM Justine 
 * @version 15/02/2023
 */
public class Command
{
    //Attributs
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur naturel
     * 
     * @param pCommandWord Le premier mot de la commande
     * @param pSecondWord Le mot de commande supplémentaire/Direction
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command()
    
    /**
     * Accesseur du mot de commande 
     * 
     * @return L'attribut du premier mot de commande
     */
    public String getCommandWord() 
    {
        return this.aCommandWord;
    }//getCommandWord()
    
    
    /**
     * Accesseur de la description de commande
     * 
     * @return l'attribut du deuxième mot de commande
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }//getSecondWord
    
    /**
     * Méthode indiquant si la commande entrée possède un second mot
     * 
     * @return Vrai s'il y a un second mot ou Faux s'il n'y a pas de second mot
     */
    public boolean hasSecondWord()
    {
     return this.getSecondWord()!= null;   
    }//hasSecondWord()
    
    /**
     * Méthode indiquant si la commande entrée possède une direction indiquée inconnue
     * 
     * @return Vrai si le mot entrée est inconnu ou Faux dans le cas écheant
     */
    public boolean isUnknown()
    {
        return this.getCommandWord()==null;
    }//isUnknown 
} // Command
