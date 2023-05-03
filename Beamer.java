/**
 * Décrivez votre classe Beamer ici.
 *
 * @author HAKIM Justine
 * @version 02/05/2023
 */
public class Beamer extends Item
{
    // variables d'instance
    private Room aChargedRoom; //Pièce dans laquelle le beamer a été chargé
    private boolean aIsCharged; 

    /**
     * Constructeur naturel d'objets de classe Beamer
     * 
     * @param pName Nom du Beamer
     * @param pDescription Description du Beamer
     * @param pWeight Poids du Beamer
     */
    public Beamer(final String pName, final String pDescription, final int pWeight)
    {
        // initialisation des variables d'instance
        super(pName, pDescription, pWeight);
        this.aChargedRoom = null;
        this.aIsCharged = false;
    }//Beamer()
    
    /**
     * Accesseur de la dernière pièce où le Beamer a été chargé.
     * 
     * @return La dernière pièce où le Beamer a été chargé.
     */
    public Room getChargedRoom(){
        return this.aChargedRoom;
    }//getChargedRoom()
    
    /**
     * Fonction booléenne qui vérifie si le beamer est chargé
     * 
     * @return Vrai si une pièce est stockée dans le beamer, Faux dans le cas contraire
     */
    public boolean isCharged(){
        return this.aChargedRoom != null;
    }//isCharged()
    
    /**
     * Charge le Beamer dans la pièce spécifiée en stockant la pièce dans laquelle il est chargé.
     * 
     * @param pChargedRoom La pièce dans laquelle le Beamer est chargé
     */
    public void charged(final Room pChargedRoom) {
        this.aIsCharged = true;
        this.aChargedRoom = pChargedRoom;
    }//charged()
    
    /**
     * Décharge le Beamer, le désactivant pour une prochaine utilisation.
     */
    public void discharged(){
        this.aIsCharged = false;
    }//discharged()
}//Beamer()
