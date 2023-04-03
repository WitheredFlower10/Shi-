import java.util.HashMap;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 * 
 * Une "Room" représente un emplacement dans le décor du jeu.  Elle est 
 * reliée à d'autres pièces par des sorties.  Pour chaque sortie existante, la pièce 
 * stocke une référence à la pièce voisine.
 * Cette classe permet définir les pièces de notre jeu et leurs sorties possibles
 * @author HAKIM Justine
 * @version 15/02/2023
 */
public class Room
{
    //Attributs de la classe
    private String aDescription;//Description de la pièce actuelle
    private HashMap<String, Room> aExits;//HashMap ("direction", pièce dans cette direction)
    public static final Room UNKNOWN_ROOM = new Room("Pas de pièce", null);
    private String aImageName;//Nom de l'image
    private HashMap <String, Item> aItems; //HashMap ("Nom de l'objet", objet)
    
    /**
     * Constructeur naturel
     * Crée une pièce décrite par la chaine "description"
     * Au départ, il n'existe aucune sortie
     * "description" est une chaîne
     * @param pDescription Description de la pièce 
     * @param pImageName Nom de l'image de la pièce
     */
    public Room(final String pDescription, final String pImageName ){
        this.aDescription = pDescription; 
        this.aImageName = pImageName;
        this.aExits = new HashMap<String, Room>(); //Créer un nouveau HashMap vide 
        this.aItems = new HashMap<String, Item>(); //Créer un nouveau HashMap vide
    }//Room()
    
    
    /**
     * Accesseur de la description
     * 
     * @return La description de la pièce actuelle
     * (telle que définie par le constructeur).
     */
    public String getShortDescription(){
        return this.aDescription;
    }//getShortDescription()
    
    /**
     * Accesseur de l'item
     * 
     * @param pItem Clef de l'item à récupérer
     * @return L'item lié à la clef
     */
    public Item getItem(final String pItem)
    {
        return this.aItems.get(pItem);
    }//getItem()
    
    /**
     * Accesseur de la chaîne de caractères de l'item.
     * Envoie une chaîne de carctères de tous les items de la pièce
     * 
     * @return Chaîne de caractères avec les items
     */
    public String getItemString()
    {
          if(this.aItems.isEmpty()) {
            return "Il n'y aucun objets dans cette pièce";
        }//if
    
        StringBuilder returnString = new StringBuilder ("Les items sont : ");
        for(Item vI : aItems.values())
        {
            returnString.append("\n").append(vI.getItemDescription()).append("\n");
        }
        return returnString.toString();
    }//getItemString()
    
    /**
     * Ajoute un item à la HashMap
     * 
     * @param pName Clef de la HashMap (nom de l'item)
     * @param pItem Item à ajouter dans la HashMap
     */
    public void addItem(final String pName, final Item pItem)
    {
        this.aItems.put(pName, pItem);
    }//addItem()
    
    /**
     * Définit une sortie de cette pièce dans la direction indiquée.
     * 
     * @param pDirection Indique la direction de la sortie.
     * @param pVoisine Indique la pièce dans la direction donnée.
     */
    public void setExit(final String pDirection, final Room pVoisine)
    {
        this.aExits.put(pDirection, pVoisine);
    }//setExit()
    
    /**
     * Accesseur de la sortie indiquée 
     * 
     * Renvoie la pièce atteinte si nous nous déplaçons dans la direction "direction"
     * S'il n'y a pas de pièces dans cette direction, alors on renvoie null. 
     * Acceseurs d'une des sorties
     * @param pDirection Direction dont on souhaite connaître la sortie 
     * @return la sortie indiquée
     */
    public Room getExit(final String pDirection)
    {
        if(!pDirection.equals("nord") && !pDirection.equals("sud") && !pDirection.equals("est") && !pDirection.equals("ouest") && !pDirection.equals("montée") && !pDirection.equals("descente"))
        {
            return UNKNOWN_ROOM ;
        }
        return this.aExits.get(pDirection);
    }//getExit()
    
    
    /**
     * Renvoie une description des sorties disponibles
     * 
     * Par exemple : "Les sorties : Nord Sud "
     * @return Une chaine de caractères indiquant une description des sorties disponibles.
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Les sorties sont:");
        for (String vS : this.aExits.keySet())
            returnString.append( " " + vS );
        return returnString.toString();
    }//getExitString()
    
    /**
     * Renvoie une description détaillée de cette pièce sous la forme :
     * Vous êtes dans (pièce).
     * Sorties : nord sud.
     * 
     * @return Une description de la pièce, ainsi que les sorties 
     */
    public String getLongDescription()
    {
        return "Vous êtes dans "+ this.aDescription + ".\n" + getExitString() + ".\n" + getItemString();
    }//getLongDescription
    
    /**
     * Retourne une chaîne de caractères décrivant le nom de l'image de la pièce 
     * @return String qui décrit le nom de l'image de la pièce
     */
    public String getImageName() 
    {
        return this.aImageName;
    }//getImageName()
}// Room
