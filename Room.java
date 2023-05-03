import java.util.HashMap;
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 * 
 * Une "Room" représente un emplacement dans le décor du jeu.  Elle est 
 * reliée à d'autres pièces par des sorties.  Pour chaque sortie existante, la pièce 
 * stocke une référence à la pièce voisine.
 * Cette classe permet définir les pièces de notre jeu et leurs sorties possibles
 * 
 * @author HAKIM Justine
 * @version 15/02/2023
 */
public class Room
{
    //Attributs de la classe
    private String aDescription;//Description de la pièce actuelle
    private HashMap<String, Room> aExits;//HashMap ("direction", pièce dans cette direction)
    public static final Room UNKNOWN_ROOM = new Room("Pas de pièce", null);//Constante d'une pièce inconnue
    private String aImageName;//Nom de l'image  
    private ItemList aRoomItems;//Liste d'objets présents dans la pièce
    private HashMap<String, Door> aDoors; // Stocke les portes de la pièce
    
    /**
     * Constructeur naturel
     * Crée une pièce décrite par la chaine "description"
     * Au départ, il n'existe aucune sortie
     * "description" est une chaîne
     * 
     * @param pDescription Description de la pièce 
     * @param pImageName Nom de l'image de la pièce
     */
    public Room(final String pDescription, final String pImageName ){
        this.aDescription = pDescription; 
        this.aImageName = pImageName;
        this.aExits = new HashMap<String, Room>(); //Créer un nouveau HashMap vide 
        this.aDoors = new HashMap<String, Door>();
        this.aRoomItems = new ItemList (); 
    }//Room()
    
    /**
     * Accesseur de l'item de la pièce
     * 
     * @return L'item de la pièce
     */
    public ItemList getRoomItems()
    {
        return this.aRoomItems;
    }//getRoomItems()
    
    /**
     * Accesseur de la description
     * 
     * @return La description de la pièce actuelle
     * (telle que définie par le constructeur).
     */
    public String getShortDescription()
    {
        return this.aDescription;
    }//getShortDescription()
    
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
     * 
     * @param pDirection Direction dont on souhaite connaître la sortie 
     * @return L'objet Room accessible dans la direction donnée, ou UNKNOWN_ROOM si la direction n'est pas valide
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
     * Vous etes dans (pièce).
     * Sorties : nord sud.
     * Les objets dans cette pièce sont : (items)
     * 
     * @return Une description de la pièce, ainsi que les sorties, ainsi que les items de la pièce
     */
    public String getLongDescription()
    {
        return "Vous êtes dans "+ this.aDescription + "\n" + this.getExitString() + "\n" + "Les objets dans cette pièce sont :" + this.aRoomItems.getItemString();
    }//getLongDescription()
    
    /**
     * Le nom de l'image est stocké dans l'attribut aImageName de la classe Room.
     * Retourne une chaîne de caractères décrivant le nom de l'image de la pièce 
     * @return String qui décrit le nom de l'image de la pièce
     */
    public String getImageName() 
    {
        return this.aImageName;
    }//getImageName()
    
    /**
     * Fonction booléenne qui vérifie si la pièce est acccessible
     * 
     * @param pRoom Pièce dont on vérifie l'accessibilité
     * @return Vrai si la pièce est une sortie possible, Faux dans le cas contraire
     */
    public boolean isExit (final Room pRoom) {
        return pRoom.aExits.containsValue(this);
    }//isExit()

    public void addDoor(final String pDirection, final Door pDoor){
        this.aDoors.put(pDirection, pDoor);
    }//addDoor()
    
    public Door getDoor(final String pDirection){
        return this.aDoors.get(pDirection);
    }
}// Room
