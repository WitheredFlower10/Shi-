import java.util.HashMap;
import java.util.Set;
/**
 * Classe ItemList
 * 
 * Cette classe permet de gérer une liste d'items et ainsi mutualiser la gestion des items qui se retrouvait dupliquée dans Room et dans Player.
 *
 * @author HAKIM Justine
 * @version 10/04/2023
 */
public class ItemList
{
    // variables d'instance
    private HashMap <String, Item> aItems;//HashMap ("Nom de l'objet", objet)

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList(final String pLocation)
    {
        // initialisation des variables d'instance
        this.aItems = new HashMap <String, Item>() ;
    }//ItemList()
    
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
     * Accesseur permettant de récupérer la HashMap des objets présent dans le sac
     * 
     * @return Inventaire
     */
    public HashMap <String, Item> getItemsInventory()
    {
        return aItems; 
    }//getItemsInventory()
    
    /**
     * Accesseur de la chaîne de caractères de l'item.
     * Envoie une chaîne de caractères de tous les items de la pièce
     * 
     * @return Chaîne de caractères avec les items
     */
    public String getInventoryString() 
    {
        if(this.aItems.isEmpty())
        {
            return "Ton inventaire est vide, il ne contient aucun objets...";
        }//if
        StringBuilder returnString = new StringBuilder ("Les objets dans votre inventaire sont les suivants :");
        for(Item vItem : this.aItems.values()){
            returnString.append("\n").append(vItem.getItemDescription());
        }//for()
        return returnString.toString();
    }//getInventoryString()
    
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
     * Supprime un Item de notre HashMap
     * 
     * @param pName Nom de l'Item
     */
    public void removeItem(final String pName)
    {
        this.aItems.remove(pName);
    }//removeItem()
}//ItemList()
