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

    
    /**
     * Constructeur par défaut pour les objets de la classe GameEngine
     */ 
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
    }//GameEngine()
    
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
        
        //Initialisation de la salle de début du jeu
        this.aPlayer = new Player("Sseldeen",vKaresansui);
        
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
        vKaresansui.addItem("Jinbaori",vJinbaori);
        vKyozo.addItem("Livre Torii, temples et sanctuaires japonais",vLivreTorii);
        vKyozo.addItem("Livre Shinto",vLivreShinto);
        vYakushi_do.addItem("Livre Promenades Japonaises",vLivrePJap);
        vDojo_1.addItem("Sauce Soja",vSauce);
        vKuri.addItem("Sobas",vSobas);
        vKuri.addItem("Saké",vSake);
        vHojo.addItem("Kote",vKote);
        vKairo.addItem("Hitatare et hakama",vHetH);
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
                this.goRoom(vCommand);
                break;
            case "quitter":
                this.quitter(vCommand);
                break;
            case "aide":
                this.printAide();
                break;
            case "regarder":
                this.look(); 
                break;
            case "manger" :
                this.eat();
                break;
            case "reculer" :
                this.back(vCommand);
                break;
            case "test":
                this.test(vCommand);
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
     * Procédure permettant de se déplacer de pièces en pièces
     * 
     * @param pDirectionSouhaite Direction dans laquelle le joueur souhaite se rendre
     */
    private void goRoom(final Command pDirectionSouhaite)
    {
        if(!pDirectionSouhaite.hasSecondWord()){
            this.aGui.println("Où aller") ;   
            return;//Arrête la fonction prématurément car l'on n'a pas besoin de changer de pièce
        }//if
        //vérifie si la commande est une direction valide ou non et indique au joueur si la Direction est Inconnue
        if(this.aPlayer.getCurrentRoom() == Room.UNKNOWN_ROOM)
        {
            this.aGui.println("Direction Inconnue");
            return;//Arrête la fonction prématurément car l'on n'a pas besoin de changer de pièce
        }//if
        //Indique au joueur s'il n'y a pas de sorties 
        String vDirection = pDirectionSouhaite.getSecondWord();
        if (this.aPlayer.getCurrentRoom().getExit(vDirection) == null)
        {
            this.aGui.println("Il n'y a pas de portes!");
        } 
        this.aPlayer.goRoom(vDirection);
        printLocationInfo();//Affiche les informations sur la pièce
    }//goRoom()
    
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
     * Procédure qui affiche ce qu'il y a dans la pièce, la description et les sorties
     */
    private void look()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }//look()
    
    /**
     * Proédure qui affiche un message une fois que le joueur a mangé quelque chose
     */
    private void eat()
    {
        this.aGui.println("Vous avez mangé et vous êtes rassasié");
    }//eat()
    
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
     * Permet de revenir en arrière, dans la pièce précédente
     * 
     * @param pCommand Commande de l'utilisateur
     */
    private void back(final Command pCommand)
    {
        //Vérifie si la commande entrée a bien un second mot
        if(pCommand.hasSecondWord()){
            this.aGui.println("Je ne comprends pas cette commande, utilisez un seul mot.");
            return; // Arrêt prématuré
        }//if
        //Vérifie s'il y a une pièce précédente
        if(this.aPlayer.getPreviousRoom().isEmpty())
        {
            this.aGui.println("Il n'y a pas de pièces précédentes.");
            return;//Arrêt prématuré
        }//if
        this.aPlayer.back();
        this.printLocationInfo();//Affiche les informations de la pièce actuelle
    }//back()
    
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
