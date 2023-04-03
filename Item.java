/**
 * La classe Item permet de mettre des items dans des pièces
 *
 * @author HAKIM Justine
 * @version 27/03/2023
 */
public class Item
{
    // variables d'instance
    private String aName;
    private String aDescription;
    private double aWeight;

    /**
     * Constructeur naturel d'objets de classe Item
     * 
     * @param pName Nom de l'item
     * @param pDescription Description de l'item
     * @param pWeight Poids de l'item
     */
    public Item(final String pName, final String pDescription, final double pWeight)
    {
        // initialisation des variables d'instance
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }//Item()
    
    /**
     * Accesseur du nom de l'item
     * 
     * @return Nom de l'item
     */
    public String getName() 
    {
        return this.aName;
    }//getName()
    
    /**
     * Accesseur de la description de l'item
     * 
     * @return Description de l'item
     */
    public String getDescription()
    {
        return this.aDescription;
    }//getDescription()
    
    /**
     * Accesseur du poids de l'item
     * 
     * @return Poids de l'item
     */
    public double getWeight()
    {
        return this.aWeight;
    }//getWeight()
    
    /** 
     * Accesseur de la description complète de l'item
     * 
     * @return Description complète d'un item
     */
    public String getItemDescription()
    {
        return "Il y a un " + this.getName() + " à prendre... " + " " + this.getDescription() + ". " + "Cet objet pèse " + this.getWeight() + " g.";
    }
}//Item()
