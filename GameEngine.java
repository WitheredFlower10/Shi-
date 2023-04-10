import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Classe GameEngine 
 * 
 * Cette classe crée toutes les salles, crée l'analyseur et démarre le jeu.
 * Elle évalue et exécute également les commandes renvoyées par l'analyseur. 
 * 
 *
 * @author HAKIM Justine
 * @version 25/03/2023
 */
public class GameEngine
{
    //Attributs
    private Parser aParser;// Commande de l'utilisateur
    private UserInterface aGui;//Interface visuelle
    private HashMap<String, Room> aRooms;
    private Player aPlayer;
    private String aName;
    private ItemList aInventory;
    
    /**
     * Constructeur par défaut pour les objets de la classe GameEngine
     */ 
    public GameEngine(final String pPseudo)
    {
        this.aName = pPseudo;
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
        this.aPlayer = new Player(aName,this.aRooms.get("Karesansui"), this);
    }//GameEngine()
    
    /**
     * Accesseur de l'interface
     * 
     * @return L'interface
     */
    public UserInterface getGUI() 
    {
        return this.aGui;
    }//getGUI()
        
    /**
     * Procédure qui permet de modifier la valeur de l'interface et utilise printWelcome
     * Démarre l'interface graphique
     * 
     * @param pUserInterface Interface graphique
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();         
    }//setGUI()

    /**
     * Procédure qui permet d'afficher les messages de bienvenue au début du jeu     
     */
    private void printWelcome()
    {
    this.aGui.print( "\n" );
    this.aGui.println("Bonjour " + this.aPlayer.getPseudo() + "!");
    this.aGui.println( "Bienvenue dans Shi : Kiyowara Fumiaki's Adventure !" );
    this.aGui.println( "Shi : Kiyowara Fumiaki's Adventure est un nouveau et incroyable jeu d'aventure." );
    this.aGui.println( "Essayer 'aide' si vous avez besoin d'aide." );
    this.aGui.print( "\n" );
    this.printLocationInfo();
    }//printWelcome()     
    
    /**
     * Procédure permettant de créer les différentes pièces du jeu 
     */
    private void createRooms()
    {
        //Création des différents lieux du temple
        Room vKaresansui = new Room("un jardin de rocaille.", "./Images/Karesansui.png");
        Room vChinjusha = new Room("un petit sanctuaire.", "./Images/Chinjusha.png");
        Room vKyozo = new Room("un dépôt de livres traitant de l'histoire du temple.", "./Images/Kyozo.png");
        Room vYakushi_do = new Room("un bâtiment dans lequel est vénérée une statue de Yakushi Nyorai.", "./Images/Yakushi_do.png");
        Room vDojo_1 = new Room("la pièce qui historiquement était la salle du temple religieux. Cette salle est maintenant utilisée pour l'enseignement des arts martiaux. Le dojo est un lieu où l'on progresse. Cette progression est obligatoirement supervisée et contrôlée par un maître. ", "./Images/Dojo_1.png");
        Room vHojo = new Room("les quartiers d'habitation du prêtre responsable du temple. ", "./Images/Hojo.png");
        Room vHokke_do = new Room("une salle dont la disposition permet la marche autour d'une statue pour la méditation. Le but de la marche est de se concentrer et de chercher la vérité ultime.", "./Images/Hokke_do.png");
        Room vKairo = new Room("un long passage couvert semblable à un portique reliant deux bâtiments.","./Images/Kairo.png");
        Room vHatto = new Room("un bâtiment où l'abbé donne des conférences sur les écritures du bouddhisme.","./Images/Hatto.png");
        Room vKoro = new Room("la tour dans laquelle se trouve un tambour qui marque le passage du temps.","./Images/Koro.png");
        Room vDojo_2 = new Room("la pièce qui historiquement était la salle du temple religieux. Cette grande salle a aussi été utilisée par la suite pour l'enseignement des arts martiaux. Le dojo est un lieu où l'on progresse. Cette progression est obligatoirement supervisée et contrôlée parun maître.", "./Images/Dojo_2.png");
        Room vMi_do = new Room("un bâtiment dans lequel est vénérée une statue sacrée. ", "./Images/Mi_do.png");
        Room vKuri = new Room("un bâtiment abritant les cuisines du temple.","./Images/Kuri.png");
        
        //Sorties du Jardin
        vKaresansui.setExit("montée",vKairo);
        vKaresansui.setExit("ouest", vHokke_do);
        vKaresansui.setExit("est", vChinjusha);
        
        //Sortie du Sanctuaire
        vChinjusha.setExit("ouest",vKaresansui);
      
        //Sortie de la "Bibliothèque"
        vKyozo.setExit("nord", vYakushi_do);
        
        //Sorties du batîment dans lequel on peut vénérer la statue Yakushi
        vYakushi_do.setExit("nord",vDojo_1);
        vYakushi_do.setExit("sud",vKyozo);
        
        //Sorties du Premier Dojo
        vDojo_1.setExit("sud",vYakushi_do);
        vDojo_1.setExit("nord",vHojo);
        
        //Sorties des Quartiers d'Habitation du prêtre
        vHojo.setExit("sud", vDojo_1);
        vHojo.setExit("ouest", vHatto);
        
        //Sortie de la salle de Méditation
        vHokke_do.setExit("est",vKaresansui);
        
        //Sorties du long Passage 
        vKairo.setExit("descente",vKaresansui);
        vKairo.setExit("ouest", vKuri);
        vKairo.setExit("est", vKyozo);
        
        //Sorties du Batîment où l'abbé donne des conférences
        vHatto.setExit("sud", vKairo);
        vHatto.setExit("ouest", vKoro);
        vHatto.setExit("est", vHojo);
        
        //Sorties de la Tour
        vKoro.setExit("sud",vDojo_2);
        vKoro.setExit("est",vHatto);
        
        //Sorties du Deuxième Dojo
        vDojo_2.setExit("nord",vKoro);
        vDojo_2.setExit("sud",vMi_do);
        
        //Sorties du Batîment dans lequel est vénérée une statue sacrée
        vMi_do.setExit("nord",vDojo_2);
        vMi_do.setExit("sud",vKuri);
        
        //Sortie de la cuisine
        vKuri.setExit("nord",vMi_do);
                
        //Stockage des pièces
        this.aRooms.put("Karesansui",vKaresansui);
        this.aRooms.put("Chinjusha",vChinjusha);
        this.aRooms.put("Kyozo",vKyozo);
        this.aRooms.put("Yakushi_do",vYakushi_do);
        this.aRooms.put("Dojo_1",vDojo_1);
        this.aRooms.put("Hojo",vHojo);
        this.aRooms.put("Hokke_do",vHokke_do);
        this.aRooms.put("Kairo",vKairo);
        this.aRooms.put("Hatto",vHatto);
        this.aRooms.put("Koro",vKoro);
        this.aRooms.put("Dojo_2",vDojo_2);
        this.aRooms.put("Mi_do",vMi_do);
        this.aRooms.put("Kuri",vKuri);
        
        //Création des Items
        Item vJinbaori = new Item ("Jinbaori","C'est un manteau court traditionnel japonais porté par les samouraïs pendant la période Edo (1603-1868). Il est fabriqué à partir de tissus épais et imperméables et est souvent orné de motifs colorés. Le jinbaori était souvent porté par-dessus le hitatare et le hakama pour fournir une couche supplémentaire de protection contre les éléments lors des batailles.",600);
        Item vLivreTorii = new Item("Livre Torii, temples et sanctuaires japonais", "Entre bande dessinée et carnet de voyage, ce livre plein d'humour et très documenté vous dit tout sur les sanctuaires shintoïstes, les temples bouddhistes et sur la place du sacré dans la vie des Japonais.", 500);
        Item vLivreShinto = new Item("Livre Shinto", "C'est un livre sur la religion shintoïste, qui est la religion traditionnelle du Japon.",500);        
        Item vLivrePJap = new Item("Livre Promenades Japonaises","C'est un livre sur les promenades dans les endroits historiques et touristiques du Japon.",500);
        Item vSauce = new Item ("Sauce Soja","C'est une sauce salée et légèrement sucrée d'origine japonaise, faite à partir de soja, de blé, d'eau et de sel.",200);
        Item vSobas = new Item ("Sobas","Ce sont des nouilles fines et grises, généralement faites à partir de farine de sarrasin, consommées chaudes ou froides au Japon.",100);
        Item vSake = new Item ("Saké","C'est une boisson alcoolisée japonaise traditionnelle, fabriquée à partir de riz fermenté.",700);
        Item vKote = new Item ("Kote","Ce sont des gants de protection utilisés dans les arts martiaux japonais",350);      
        Item vHetH = new Item ("Hitatare et hakama","Il s'agit d'un costume traditionnel japonais porté par les hommes. Le hitatare est une veste ample portée sur un pantalon et le hakama est une jupe-culotte ample portée par-dessus.",3000);
        
        //Placement des Items dans la pièce
        vKaresansui.addItem(vJinbaori);
        vKyozo.addItem(vLivreTorii);
        vKyozo.addItem(vLivreShinto);
        vYakushi_do.addItem(vLivrePJap);
        vDojo_1.addItem(vSauce);
        vKuri.addItem(vSobas);
        vKuri.addItem(vSake);
        vHojo.addItem(vKote);
        vKairo.addItem(vHetH);   
    }//createRooms()
        
    /**
     * Appelle la méthode souhaitée par le joueur
     * Transforme et analyse la commande de l'utilisateur
     * 
     * @param pCommandLine Commande écrite par le joueur
     * @return Vrai si le joueur veut arrêter le jeu, Faux s'il veut le continuer
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );//Affiche les caractères tapés au clavier
        Command vCommand = this.aParser.getCommand( pCommandLine );//Converti en commande

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "La demande ne peut pas être prise en compte..." );
            return;
        }//if

        String vCommandWord = vCommand.getCommandWord();
        switch(vCommandWord){
            case "aller":
                this.aPlayer.goRoom(vCommand);
                this.printLocationInfo();
                break;
            case "quitter":
                this.quitter(vCommand);
                break;
            case "aide":
                this.printAide();
                break;
            case "regarder":
                this.aPlayer.look();
                break;
            case "manger" :
                this.aPlayer.eat();
                break;
            case "reculer" :
                this.aPlayer.back(vCommand);
                this.printLocationInfo();
                break;
            case "test":
                this.test(vCommand);
                break;
            case "prendre" :
                this.aPlayer.take(vCommand);
                break;
            case "jeter" : 
                this.aPlayer.drop(vCommand);
                break;
        }//switch
    }//interpretCommand()

    /**
     * Procédure qui affiche les commandes d'aides possibles
     */
    private void printAide() 
    {  
    this.aGui.println("Vous êtes perdu. Vous êtes seul.\nVous vous baladez dans le temple de Shizue. \n\n Les commandes disponibles sont :\n"); 
    this.aGui.println(this.aParser.getCommandString());
    }//printAide()
    
    /**
     * Termine le jeu
     */
    private void endGame() 
    {
        int exit = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir quitter? :/","Quitter?",JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION)
            {
                JOptionPane.showInternalMessageDialog(null,"Merci d'avoir joué au jeu, au revoir !", "Message d'au revoir", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }else {
            this.aGui.enable(true); 
        }
    }//endGame()
    
    /**
     * Méthode qui permet de quitter le jeu 
     * 
     * @param pCommand Commande rentrée par le joueur
     * @return Vrai si le joueur écrit "quitter", Faux s'il y a un second mot rentré par le joueur
     */
    private void quitter(final Command pCommand) 
    {
        if (pCommand.hasSecondWord()){
          this.aGui.println("Quitter ?");
        } else {
           this.endGame();
        }//else
    }//quitter()    
    
    /**
    * Affiche les sorties possibles de la pièce courante
    */
    private void printLocationInfo()
    {
    this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    if( this.aPlayer.getCurrentRoom().getImageName() != null)
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName());
    }//printLocationInfo()
    
    /**
     * Accesseur.Récupère une pièce par rapport à une description donnée.
     * 
     * @param pDescription Description de la pièce
     * @return Une pièce par rapport à la description donnée ou null s'il n'y a aucune qui ne correspond.
     */
    public Room getRoom(final String pDescription)
    {
        return this.aRooms.get(pDescription);
    }//getRoom()
    
    /**
     * Méthode de test
     * 
     * @param pCommand Commande de l'utilisateur
     */
    private void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord()) {
            this.aGui.println("Que voulez-vous tester?");
            return;
        }
        String vFile = pCommand.getSecondWord();
        if(!vFile.contains(".txt")){
            vFile += ".txt";
        }
        try {
            Scanner vScan = new Scanner(new File (vFile));
            this.aGui.println("Test " + vFile + "...");
            while(vScan.hasNextLine()){
                this.interpretCommand(vScan.nextLine());         
            }
        }catch(final FileNotFoundException pE){
            this.aGui.println("Désolé, le ficher " + vFile + " n'a pas été trouvé. Réessayer");
        }
    }//test()
}//GameEngine()
