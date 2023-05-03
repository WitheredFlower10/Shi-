import java.util.ArrayList;
/**
 * La classe Door permet de créer des portes fermées et ouvrables avec une clé.
 *
 * @author HAKIM Justine
 * @version 03/05/2023
 */
public class Door extends Room
{
    // variables d'instance
    private ArrayList<Room> aExits;
    private boolean aIsOpen;
    private Item aKey;
    
    /**
     * Constructeur d'objets de la classe Door
     */
    public Door(final String pDescription, final String pImage, final Item pKey){
        super(pDescription, pImage);
        this.aIsOpen = false;
        this.aKey = pKey;
        this.aExits = new ArrayList<Room>();
    }//Door()
    
    /**
     * 
     */
    public boolean isOpen(){
        return this.aIsOpen;
    }//getIsLocked()
    
    /**
     * 
     */
    public void setOpen() {
        this.aIsOpen = true;
    }//setLocked()

    /**
     * Retourne la clé nécessaire à l'ouverture de la porte
     */
    public Item getKey() {
        return this.aKey;
    }//getKey()

    /**
     * Retourne la prochaine pièce
     */
    public Room getNextRoom(final Room pCurrentRoom){
        if(pCurrentRoom.equals(this.aExits.get(0))) {
            return this.aExits.get(1);
        }
        return this.aExits.get(0);
    }
    
    /**
     * 
     */
    @Override 
    public void setExit(final String pDirection, final Room pNeighboor){
        super.setExit(pDirection,pNeighboor);
        this.aExits.add(pNeighboor);
    }
}//Door()

