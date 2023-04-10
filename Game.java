import javax.swing.JOptionPane;
/**
 * Classe Game - le moteur du jeu d'aventure.
 * Cette classe est le moteur principal du jeu.
 * Les utilisateurs peuvent se promener dans certains paysages.
 * 
 * Pour jouer à ce jeu, créez une instance de cette classe.
 * 
 * Cette classe principale crée les objets d'implémentation nécessaires et lance le jeu.
 * 
 * @author HAKIM Justine
 * @version 15/02/2023
 */

public class Game
{   
    //Attributs
    private UserInterface aGui;
    private GameEngine aEngine;
    
    /**
     * Constructeur par défaut
     * Créer le jeu et initialiser sa carte interne. Créer l'interface et s'y connecter.
     */
    public Game()
    {
        //Demande un Pseudo à l'utilisateur 
        String vPseudo = "";
        while(vPseudo.length() == 0) {
            vPseudo = JOptionPane.showInputDialog("Quel est votre pseudo ?");
            
        }
        
        this.aEngine = new GameEngine(vPseudo);
        this.aGui = new UserInterface( this.aEngine);
        this.aEngine.setGUI( this.aGui );
    }//Game()
   
}//Game()
