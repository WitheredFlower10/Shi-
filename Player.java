import java.util.Stack;
/**
 * Classe Player 
 *
 * @author HAKIM Justine
 * @version 03/04/2023
 */
public class Player
{
    // variables d'instance
    private String aPseudo;
    private Room aCurrentRoom; //Pièce actuelle
    private Stack <Room> aPreviousRoom;//Pièce précédente
    private double aWeight;
    private GameEngine aEngine;
    private ItemList aInventory;

    /**
     * Constructeur naturel d'objets de classe Player
     * 
     * @param pPseudo Pseudo du joueur
     * @param pCurrentRoom Pièce de départ du joueur
     */
    public Player(final String pPseudo, final Room pCurrentRoom, final GameEngine pEngine)
    {
        // initialisation des variables d'instance
        this.aPseudo = pPseudo;
        this.aWeight = 0;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRoom = new Stack <Room>();
        this.aEngine = pEngine;
        this.aInventory = new ItemList ("Inventaire");
    }//Player()
    
    /**
     * Accesseur du Pseudo
     * 
     * @return Pseudo du joueur
     */
    public String getPseudo() 
    {
        return this.aPseudo;
    }//getPseudo()
    
    /**
     * Accesseur de la pièce actuelle du joueur
     * 
     * @return Pièce actuelle du joueur
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }//getCurrentRoom()
    
    /**
     * Accesseur du Stack de pièces précédentes
     * 
     * @return Stack des pièces précédentes
     */
    public Stack <Room> getPreviousRoom()
    {
        return this.aPreviousRoom;
    }//getPreviousRoom()
    
    /**
     * 
     * 
     */
    public ItemList getInventory()
    {
        return this.aInventory;
    }//getInventory()
          
    /**
     * Procédure permettant de se déplacer de pièces en pièces
     * 
     * @param pDirectionSouhaite Direction dans laquelle le joueur souhaite se rendre
     */
    public void goRoom(final String pDirectionSouhaite)
    {
        //Appelle getExit() avec la direction pour connaître la prochaine pièce
        Room vNextRoom = this.aCurrentRoom.getExit(pDirectionSouhaite);
        //Modifie la pièce précédente par la pièce dans laquelle on se situe
        this.aPreviousRoom.push(this.aCurrentRoom);
        //Change la pièce actuelle par la pièce suivante
        this.aCurrentRoom = vNextRoom;
    }//goRoom()
    
     /**
     * Procédure permettant de se déplacer de pièces en pièces
     * 
     * @param pDirectionSouhaite Direction dans laquelle le joueur souhaite se rendre
     */
    public void goRoom(final Command pDirectionSouhaite)
    {
        if(!pDirectionSouhaite.hasSecondWord()){
            this.aEngine.getGUI().println("Où aller") ;   
            return;//Arrête la fonction prématurément car l'on n'a pas besoin de changer de pièce
        }//if
        //vérifie si la commande est une direction valide ou non et indique au joueur si la Direction est Inconnue
        if(this.aCurrentRoom == Room.UNKNOWN_ROOM)
        {
            this.aEngine.getGUI().println("Direction Inconnue");
            return;//Arrête la fonction prématurément car l'on n'a pas besoin de changer de pièce
        }//if
        //Indique au joueur s'il n'y a pas de sorties 
        String vDirection = pDirectionSouhaite.getSecondWord();
        if (this.aCurrentRoom.getExit(vDirection) == null)
        {
            this.aEngine.getGUI().println("Il n'y a pas de portes!");
            return;//Arrête la fonction prématurément car l'on n'a pas besoin de changer de pièce
        } 
        //Appelle getExit() avec la direction pour connaître la prochaine pièce
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
        //Modifie la pièce précédente par la pièce dans laquelle on se situe
        this.aPreviousRoom.push(this.aCurrentRoom);
        //Change la pièce actuelle par la pièce suivante
        this.aCurrentRoom = vNextRoom;
    }//goRoom()
    
    
    /** 
     * Permet de revenir en arrière, dans la pièce précédente
     * 
     * @param pCommand Commande de l'utilisateur
     */
    public void back(final Command pCommand)
    {
        //Vérifie si la commande entrée a bien un second mot
        if(pCommand.hasSecondWord()){
            this.aEngine.getGUI().println("Je ne comprends pas cette commande, utilisez un seul mot.");
            return; // Arrêt prématuré
        }//if
        //Vérifie s'il y a une pièce précédente
        if(this.aPreviousRoom.isEmpty())
        {
            this.aEngine.getGUI().println("Il n'y a pas de pièces précédentes.");
            return;//Arrêt prématuré
        }//if  
        if(this.aPreviousRoom.empty())
        {
            this.aEngine.getGUI().println("Il n'y a pas de pièces précédentes.");
        } else {
            this.aCurrentRoom = this.aPreviousRoom.pop();//Change la pièce actuelle par la pièce suivante
        }
    }//back()
    
    /**
     * Procédure qui affiche ce qu'il y a dans la pièce, la description et les sorties
     */
    public void look()
    {
        this.aEngine.getGUI().println(this.aCurrentRoom.getLongDescription());
    }//look()
    
    /**
     * Proédure qui affiche un message une fois que le joueur a mangé quelque chose
     */
    public void eat()
    {
       this.aEngine.getGUI().println("Vous avez mangé et vous êtes rassasié");
    }//eat()
    
    /**
     * Procédure qui permet au joueur de ramasser un objet et de le mettre dans son inventaire
     * 
     * @param pCommand Une commande entrée par l'utilisateur
     */
    public void take(final Command pCommand)
    {
        if(!pCommand.hasSecondWord()) {
            this.aEngine.getGUI().println("Que voulez-vous prendre ?");
            return;
        }//if
        String vPrise = pCommand.getSecondWord();
        if(this.aCurrentRoom.getRoomItems().getItem(vPrise) == null) 
        {
            this.aEngine.getGUI().println("Désolé... Cette objet n'existe pas..."); 
            return;
        }//if
        Item vItem = this.aCurrentRoom.getRoomItems().getItem(vPrise);
        this.aEngine.getGUI().println(" " + vPrise + " est à présent dans votre inventaire.");
        this.aInventory.addItem(vPrise, vItem);
        this.aCurrentRoom.removeItem(vItem);
    }//take()
    
    /**
     * Procédure qui permet au joueur de lâcher un des objets de son inventaire
     * 
     * @param pCommand Une commande entrée par l'utilisateur
     */
    public void drop(final Command pCommand)
    {
        if(!pCommand.hasSecondWord()) {
            this.aEngine.getGUI().println("Que voulez-vous jeter ?");
            return;
        }//if
        String vPoubelle = pCommand.getSecondWord();
        if(this.aCurrentRoom.getRoomItems().getItem(vPoubelle) == null) {
            this.aEngine.getGUI().println("Désolé... Cette objet n'est pas dans votre inventaire...");
            return;
        }//if 
        this.aInventory.removeItem(vPoubelle);
        this.aEngine.getGUI().println("Vous n'avez plus" + vPoubelle + " dans votre inventaire.");
        this.aEngine.getGUI().println(this.aInventory.getInventoryString());
    }//drop()
}//Player()
