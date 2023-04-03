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

    /**
     * Constructeur naturel d'objets de classe Player
     * 
     * @param pPseudo Pseudo du joueur
     * @param pCurrentRoom Pièce de départ du joueur
     */
    public Player(final String pPseudo, final Room pCurrentRoom)
    {
        // initialisation des variables d'instance
        this.aPseudo = pPseudo;
        this.aWeight = 120.0;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRoom = new Stack <Room>();
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
     * Permet de revenir en arrière, dans la pièce précédente
     * 
     */
    public void back()
    {
        if(this.aPreviousRoom.empty())
        {
            System.out.println("Il n'y a pas de pièces précédentes.");
        } else {
            this.aCurrentRoom = this.aPreviousRoom.pop();//Change la pièce actuelle par la pièce suivante
        }
    }//back()
}//Player()
