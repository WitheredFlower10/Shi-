import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;

/**
 * Cette classe met en œuvre une interface utilisateur graphique simple avec une zone de saisie de texte, une zone de sortie de texte et une image optionnelle.
 *
 * @author HAKIM Justine
 * @version 26/03/2023
 */
public class UserInterface implements ActionListener
{
    // Variables d'instance
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JPanel aPanelEast;
    private JButton aButtonNord;
    private JButton aButtonMontee;
    private JButton aButtonDescente;
    private JButton aButtonSud;
    private JButton aButtonEst;
    private JButton aButtonOuest;
    private JButton aButtonAide;
    private JButton aButtonQuitter;
    
    /**
     * Constructeur d'objets de classe UserInterface
     * Construire une interface utilisateur. 
     * En tant que paramètre, un moteur de jeu (un objet qui traite et exécute les commandes du jeu) est nécessaire.
     * 
     * @param gameEngine L'objet GameEngine implémentant la logique du jeu.
     */
    public UserInterface(final GameEngine pGameEngine)
    {
        // initialisation des variables d'instance
        this.aEngine = pGameEngine;
        this.createGUI();
    }//userInterface()

    /**
     * Imprimez un texte dans la zone de texte.  
     * 
     * @param pText Un texte saisi 
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print()
    
    /**
     * Imprimez du texte dans la zone de texte, suivi d'un saut de ligne.
     * 
     * @param pText Un texte saisi
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println()
    
    /**
     * Afficher un fichier image dans l'interface.
     * 
     * @param pImageName Le nom de l'image
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage()
    
    /**
     * Permet d'activer ou de désactiver la saisie dans le champ de saisie.
     * 
     * @param pOnOff 
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable()

    /**
     * Mise en place de l'interface utilisateur graphique.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Shi : Kiyowara Fumiaki's Adventure" );
        this.aEntryField = new JTextField( 34 );
      
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        Color vCouleur = new Color(255,102,102);
        Color vCouleur2 = new Color(102,255,102);

        this.aImage = new JLabel();
        this.aPanelEast = new JPanel();
        this.aPanelEast.setLayout(new GridLayout(4,4));
     
        //Création du bouton quitter
        this.aButtonQuitter = new JButton("Quitter");
        this.aButtonQuitter.addActionListener(this);
        this.aButtonQuitter.setBackground(vCouleur);
        this.aPanelEast.add(this.aButtonQuitter);
        
        //Création du bouton Aller Nord
        this.aButtonNord = new JButton("Aller Nord");
        this.aButtonNord.addActionListener(this);
        this.aButtonNord.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonNord);
        
        //Création du bouton Aller Montée
        this.aButtonMontee = new JButton("Aller Montée");
        this.aButtonMontee.addActionListener(this);
        this.aButtonMontee.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonMontee);
        
        //Création du bouton Aller Descente
        this.aButtonDescente = new JButton("Aller Descente");
        this.aButtonDescente.addActionListener(this);
        this.aButtonDescente.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonDescente);
        
        //Création du bouton Aller Sud
        this.aButtonSud = new JButton("Aller Sud");
        this.aButtonSud.addActionListener(this);
        this.aButtonSud.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonSud);
        
        //Création du bouton Aller Est
        this.aButtonEst = new JButton("Aller Est");
        this.aButtonEst.addActionListener(this);
        this.aButtonEst.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonEst);
        
        //Création du bouton Aller Ouest
        this.aButtonOuest = new JButton("Aller Ouest");
        this.aButtonOuest.addActionListener(this);
        this.aButtonOuest.setBackground(vCouleur2);
        this.aPanelEast.add(this.aButtonOuest);
        
         //Création du bouton Aide
        this.aButtonAide = new JButton("Aide");
        this.aButtonAide.addActionListener(this);
        this.aButtonAide.setBackground(Color.yellow);
        this.aPanelEast.add(this.aButtonAide);
    

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add(this.aPanelEast, BorderLayout.EAST );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Interface Actionlistener pour le champ de saisie.
     * 
     * @param pE Action réalisée
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if(pE.getSource() == this.aButtonQuitter) 
        {
          this.aEngine.interpretCommand("quitter");
        } 
        else if (pE.getSource() == this.aButtonNord) 
        {
            this.aEngine.interpretCommand("aller nord");
        } 
        else if (pE.getSource() == this.aButtonMontee) 
        {
            this.aEngine.interpretCommand("aller montée");
        }
        else if (pE.getSource() == this.aButtonDescente)
        {
            this.aEngine.interpretCommand("aller descente");
        }
        else if (pE.getSource() == this.aButtonSud) 
        {
            this.aEngine.interpretCommand("aller sud");
        }
        else if (pE.getSource() == this.aButtonEst)
        {
            this.aEngine.interpretCommand("aller est");
        }
        else if (pE.getSource() == this.aButtonOuest) 
        {
            this.aEngine.interpretCommand("aller ouest");
        }
        else if (pE.getSource() == this.aButtonAide)
        {
            this.aEngine.interpretCommand("aide");
        }
        else {
        this.processCommand(); // never suppress this line
        }
    } // actionPerformed()
    
    /**
     * Une commande a été introduite dans le champ de saisie.  
     * Lit la commande et fait le nécessaire pour la traiter.
     */ 
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( vInput );
    } // processCommand()
}//UserInterface()
