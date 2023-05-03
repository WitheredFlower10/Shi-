import java.util.Stack;
/**
 * Classe Player 
 * La classe Player représente un joueur dans le jeu.
 * Elle stocke les informations telles que le pseudo du joueur, sa position dans le jeu, les objets qu'il porte etc.
 * @author HAKIM Justine
 * @version 03/04/2023
 */
public class Player
{
    // variables d'instance
    private String aPseudo;
    private Room aCurrentRoom; //Pièce actuelle
    private Stack <Room> aPreviousRoom;//Pièce précédente
    private int aWeight;
    private GameEngine aEngine;
    private ItemList aItems;
    private int aWeightMax;
    private int aMaxDeplacements;//Nombre de déplacements maximum avant de perdre

    /**
     * Constructeur naturel d'objets de classe Player
     * 
     * @param pPseudo Pseudo du joueur
     * @param pCurrentRoom Pièce de départ du joueur
     * @param pEngine Le moteur de jeu associé au joueur
     */
    public Player(final String pPseudo, final Room pCurrentRoom, final GameEngine pEngine)
    {
        // initialisation des variables d'instance
        this.aPseudo = pPseudo;
        this.aWeight = 0;
        this.aWeightMax = 5000;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRoom = new Stack <Room>();
        this.aEngine = pEngine;
        this.aMaxDeplacements = 100;
        this.aItems = new ItemList ();
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
     * Accesseur de la pile de pièces précédentes visitées par le joueur
     * 
     * @return La pile de pièces précédentes visitées par le joueur
     */
    public Stack <Room> getPreviousRoom()
    {
        return this.aPreviousRoom;
    }//getPreviousRoom()
    
    /**
     * Accesseur de la liste des objets portés par le joueur
     * 
     * @return La liste des objets portés par le joueur
     */
    public ItemList getItems()
    {
        return this.aItems;
    }//getItems()
    
    /**
     * Accesseur du poids maximal que le joueur peut porter
     * 
     * @return Le poids Maximal que le joueur peut porter
     */
    public int getWeightMax()
    {
        return this.aWeightMax;
    }//getWeightMax()
    
    /**
     * Modificateur du poids maximal que le joueur peut porter.
     * 
     * @param pWeightMax Le nouveau poids maximal que le joueur peut porter
     */
    public void setWeightMax(final int pWeightMax)
    {
        this.aWeightMax = pWeightMax;
    }//setWeightMax()
    
    /**
     * Accesseur du poids total des objets portés par le joueur
     * 
     * @return Le poids total des objets portés par le joueur
     */
    public double getWeight()
    {
        return this.aWeight;
    }//getWeight()
    
    /**
     * Accesseur du nombre de déplacements restants
     * 
     * @return Entier représentant le nombre de déplacements restants avant que le jeu ne se termine
     */
    public int getMaxDeplacements(){
        return this.aMaxDeplacements;
    }//getMaxDeplacements()
    
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
        String vMessage = this.goTo(vNextRoom);
        this.aEngine.getGUI().println(vMessage + this.aCurrentRoom.getLongDescription());
        this.aMaxDeplacements -= 1;
        this.aEngine.timerEnd();
    }//goRoom()
    
    
    /** 
     * Permet de revenir en arrière, dans la pièce précédente
     * 
     * @param pCommand Commande de l'utilisateur
     */
    public boolean back()
    {
        if(this.aPreviousRoom.isEmpty())
        {
            this.aEngine.getGUI().println("Il n'y a pas de pièces précédentes.");
            return false;//Arrêt prématuré
        }//if  
        if(passTrapDoor()){
            this.aEngine.getGUI().println(this.aPseudo + " ne peux pas revenir en arrière après avoir emprunté une Trap Door.");
            return false;
        }
        this.aCurrentRoom = this.aPreviousRoom.pop();//Change la pièce actuelle par la pièce suivante
        this.aMaxDeplacements -= 1;
        this.aEngine.timerEnd();
        return true;
    }//back()
    
    /**
     * Procédure qui affiche ce qu'il y a dans la pièce, la description, les sorties et les objets
     * 
     * @param pCommand Commande de l'utilisateur
     */
    public void look(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){
        String vLook = pCommand.getSecondWord();
        Item vCurrentItem = this.getCurrentRoom().getRoomItems().getItem(vLook);
        if(vCurrentItem == null) {
            this.aEngine.getGUI().println("Désolé...Cet item n'est pas dans la pièce ou n'existe pas...");
            return;
        }else {
            this.aEngine.getGUI().println(vCurrentItem.getItemDescription());
            return;
        }
    } else {
        this.aEngine.getGUI().println(this.aCurrentRoom.getLongDescription());
    }
    }//look()
    
    /**
     * Proédure qui affiche un message une fois que le joueur a mangé quelque chose
     * 
     * @param pCommand Commande de l'utilisateur
     */
    public void eat(final Command pCommand)
    {
        if(!pCommand.hasSecondWord()){
            this.aEngine.getGUI().println("Que voulez-vous ingérer ?");
            return;
        }
        String vItem = pCommand.getSecondWord();
        Item vMange = this.aItems.getItem(vItem);
        if(vMange == null) {
            this.aEngine.getGUI().println("Aucun objet dans l'inventaire peut se consommer...");
        } else if(vItem.equals("Sauce.Soja") || vItem.equals("Sobas")){
            this.aEngine.getGUI().println(this.aPseudo + " a mangé " + vItem + ".");
            this.aItems.removeItem(vItem, vMange);
            this.aWeight -= vMange.getWeight();
        } else if (vItem.equals("Saké")){
            this.aItems.removeItem(vItem, vMange);
            this.setWeightMax(getWeightMax() + 1500);
            this.aWeight -= vMange.getWeight();
            this.aEngine.getGUI().println(this.aPseudo + " a bu une bouteille de Saké. Elle lui redonne la force de porter de nouveaux objets. Le poids maximum de l'inventaire est à présent de : " + getWeightMax() + " !");
        } else {
            this.aEngine.getGUI().println("Aucun objet dans l'inventaire peut se consommer... (ou alors vous n'avez plus faim !) ");
        }
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
        Item vItem = this.aCurrentRoom.getRoomItems().getItem(vPrise);
        if(this.aCurrentRoom.getRoomItems().getItem(vPrise) == null) 
        {
            this.aEngine.getGUI().println("Désolé... Cette objet n'existe pas..."); 
            return;
        }else if(canTake(vItem.getWeight())==false){
            this.aEngine.getGUI().println(this.aPseudo + " porte déjà une charge lourde. " + this.aPseudo + " ne peux donc pas prendre cet objet.");
            return;
        }
        this.aEngine.getGUI().println(this.aPseudo + " a pris " + vPrise + " .Cet objet est à présent dans son inventaire.");
        this.aItems.addItem(vPrise, vItem);
        this.aCurrentRoom.getRoomItems().removeItem(vItem.getName(), vItem);
        this.aWeight += vItem.getWeight();
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
        Item vItem = this.aItems.getItem(vPoubelle);
        if(vItem == null) {
            this.aEngine.getGUI().println("Désolé... Cette objet n'est pas dans l'inventaire de" + this.aPseudo + "...");
            return;
        }//if 
        this.aItems.removeItem(vPoubelle, vItem);
        this.aCurrentRoom.getRoomItems().addItem(vItem.getName(), vItem);
        this.aEngine.getGUI().println(" "   + this.aPseudo  + " n'a plus " + vPoubelle + " dans son inventaire.");
        this.aWeight -= vItem.getWeight();
    }//drop()
    
    /**
     * Commande qui permet de montrer l'inventaire du joueur
     */
    public void inventory()
    {
        String vInventory = this.aItems.getItemString();
        if(vInventory.equals("Les objets dans l'inventaire de " + this.aPseudo + " sont : ")){
            this.aEngine.getGUI().println("");
        }else {
            this.aEngine.getGUI().println("L'inventaire de " + this.aPseudo + " contient : " + vInventory + ". Son poids total est de " + this.aWeight + "g.");
        }
    }//inventory()
    
    /**
     * Commande qui permet de charger le Beamer
     * 
     * @param pCommand Une commande entrée par l'utilisateur
     */
    public void charge(final Command pCommand) {
        this.aEngine.timerEnd();
        if(!pCommand.hasSecondWord()) {
            this.aEngine.getGUI().println("Que voulez-vous charger ?");
            return;
        }//if
        
        String vSecondWord = pCommand.getSecondWord();
        if(!vSecondWord.equals("Shukkô.ki")){
            this.aEngine.getGUI().println("Désolé... Cet objet ne peut pas être chargé...");
            return;
        }//if
        
        Item vItem = this.aItems.getItem(vSecondWord);
        Beamer vBeamer =(Beamer)vItem;
        if(vItem == null){
            this.aEngine.getGUI().println(this.aPseudo + " ne possède pas le beamer dans son inventaire.");
            return;
        }//if
        if(vBeamer.isCharged()){
            this.aEngine.getGUI().println(this.aPseudo + " a déjà chargé le beamer !");
            return;
        }//if
        vBeamer.charged(this.aCurrentRoom);
        this.aEngine.getGUI().println(this.aPseudo + " a chargé le beamer !");
    }//charge()
    
    /**
     * Commande qui décharge le beamer
     * 
     * @param pCommand Une commande entrée par l'utilisateur
     */
    public void fire (final Command pCommand){
        if(!pCommand.hasSecondWord()){
            this.aEngine.getGUI().println("Que voulez-vous décharger ?");
            return;
        }//if
        
        String vSecondWord = pCommand.getSecondWord();
        if(!vSecondWord.equals("Shukkô.ki")){
            this.aEngine.getGUI().println("Désolé... Cet objet ne peux pas être déchargé...");
            return;
        }
        
        Item vItem = this.aItems.getItem(vSecondWord);
        Beamer vBeamer = (Beamer)vItem;
        if(vItem == null){
            this.aEngine.getGUI().println(this.aPseudo + " ne possède pas le beamer dans son inventaire.");
            return;
        }//if
        if(!vBeamer.isCharged()){
            this.aEngine.getGUI().println(this.aPseudo + " doit d'abord charger le beamer...");
            return;
        }//if
        vBeamer.discharged();
        this.aEngine.getGUI().println(this.aPseudo + " a déchargé le beamer !"); 
        this.aPreviousRoom.push(this.aCurrentRoom);//Ajoute la pièce actuelle aux anciennes pièces
        this.aCurrentRoom = vBeamer.getChargedRoom();//Remplace la pièce actuelle par celle où le beamer a été chargé
        this.aItems.removeItem(vBeamer.getName(), vItem);//Retire le beamer de l'inventaire
        this.aWeight -= vBeamer.getWeight();//Retire le poids du beamer du poids de l'inventaire
        this.aMaxDeplacements -= 1; //Retire un déplacement
        this.aEngine.printLocationInfo();
        this.aEngine.timerEnd();
    }//fire()
    
    
    /**
     * Procédure qui permet de savoir si le joueur peut prendre ou pas l'objet
     * 
     * @param pWeight Poids de l'item
     * @return true si la somme du poids passé en paramètre et du poids actuel porté par le joueur ne dépasse pas le poids maximal que le joueur peut porter
     */
    public boolean canTake(final double pWeight)
    {
        return (this.aWeight + pWeight <= this.aWeightMax);
    }//canTake()
    
    /**
     * Fonction booléenne qui permet de savoir si le joueur a emprunté une Trap Door
     * 
     * @return Vrai si la dernière pièce ajoutée à la pile des anciennes pièces est une sortie de la pièce courante, Faux dans le cas contraire
     */
    public boolean passTrapDoor(){
        if(!this.aPreviousRoom.peek().isExit(this.aCurrentRoom)){
            return true;
        }
        return false;
    }//passTrapDoor()
    
    public void setCurrentRoom(Room pRoom) {
        this.aCurrentRoom = pRoom;
    }
    
    /**
     * 
     */
    public String goTo(final Room pRoom)
    {
        if (pRoom instanceof Door)
        {
            if (((Door)pRoom).isOpen() || this.hasKey(pRoom))
            {
                this.setCurrentRoom(((Door)pRoom).getNextRoom(this.aCurrentRoom));
                ((Door)pRoom).setOpen();

                return "La porte est ouverte.\n";
            }
            return "La porte est fermée. Vous ne pouvez pas passer.\n";
        }
        return "";
    }
    
    /**
     * Teste si le joueur possède l'item demandé
     */
    public boolean hasItem(final String pName)
    {
        return this.aItems.hasItem(pName);
    }
    
    /**
     * Teste si le joueur possède la clé de la porte entrée.
     */
    public boolean hasKey(final Room pRoom)
    {
        if (((Door)pRoom).getKey() == null) return false;
        return this.hasItem((((Door)pRoom).getKey().getName()));
    }
}//Player()
