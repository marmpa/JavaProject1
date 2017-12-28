package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_project.Car;
import java_project.Reservation;
import java_project.Room;
import java_project.Single_Room;
import java_project.Triple_Room;

public class Gui extends JFrame
{
    public final int windowWidth = 800;
    public final int windowHeight = 800;
    
    public java_project.Hotel hotel;
    
    private Container guiPane;
    
    private String currentName;
    public Gui(java_project.Hotel hotel)
    {
        super("Ξενοδοχείο Αιγαίο");//δηλώνω τίτλο στο παράθυρο που θα δημιουργηθεί
        this.hotel=hotel;//Αναφορά στο ξενοδοχείο
        this.setSize(windowWidth,windowHeight);//θέτω το μήκος και πλάτος του παραθύρου
        this.setVisible(true);//κάνω το παράθυρο να φαίνεται
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        LoginMenu();//καλό το state LoginMenu το οποίο αλλάζει το παράθυρο
    }
    
    
    
    public void LoginMenu()
    {
        JLabel login_JLabel1,login_JLabel2;//Δηλώνω δύο labels
        JTextField login_JTextField;//Και ένα Textfiled για να μπορώ να γράψω
        String login_JLabel1Text = "Kalosirthate gia sas gia sas gia sas gia sas gias sas";
        String login_JLabel2Text = "Όνομα";
        
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout login_BorderLayout = new GridLayout(4,2);
        this.guiPane.setLayout(login_BorderLayout);
        
        login_JLabel1 = new JLabel(login_JLabel1Text);
        //login_JLabel1.setSize(250,250);
        
        login_JLabel2 = new JLabel(login_JLabel2Text,JLabel.CENTER);
        
        login_JTextField = new JTextField();
        
        
        
        
        login_JTextField.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String login_name = login_JTextField.getText();
                    currentName=login_name;
                    MainMenu(login_name);
                   
                    
                }
            }
          
        

        );
        System.out.println(this.getFocusOwner().toString());
        this.guiPane.add(login_JLabel1);
        this.guiPane.add(login_JLabel2);
        this.guiPane.add(login_JTextField);
        
        this.setContentPane(this.guiPane);
        
    }
    //να επιστρέφω το username στην main!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public void MainMenu(String userName)
    {
        JLabel title_JLabel,menuTitle_JLabel;
        JButton option1_JButton,option2_JButton,option3_JButton,option4_JButton,option5_JButton;
        
        JLabel hotelImage_JLabel;
        
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout mainMenu_BorderLayout = new GridLayout(3,4);
        this.guiPane.setLayout(mainMenu_BorderLayout);
        
        title_JLabel = new JLabel("Ξενοδοχείο αιγαίο");
        title_JLabel.setFont(new Font("Serif", Font.BOLD, 30));
        
        menuTitle_JLabel = new JLabel("Μενού Επιλογών");
        menuTitle_JLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        option1_JButton = new JButton("Dimiourgia");
        option2_JButton = new JButton("Diagrafi");
        option3_JButton = new JButton("Anazitisi");
        option4_JButton = new JButton("Diathesima domatia");
        option5_JButton = new JButton("Advanced");
        
        option1_JButton.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για καινούργια κράτηση
                NewReservationForm();
            }
        } 
        );
        
        this.guiPane.add(title_JLabel);
        this.guiPane.add(menuTitle_JLabel);
        
        this.guiPane.add(option1_JButton);
        this.guiPane.add(option2_JButton);
        this.guiPane.add(option3_JButton);
        this.guiPane.add(option4_JButton);
        this.guiPane.add(option5_JButton);
        
        this.setContentPane(this.guiPane);
    }
    
    public void NewReservationForm()
    {
        JLabel from_JLabel,to_JLabel,reservationID_JLabel;
        JTextField from_JTextField,to_JTextField,reservationID_JTextField;
        JComboBox reservationItems_JComboBox,reservationID_JComboBox;
        JButton submit_JButton;
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(4,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        from_JLabel = new JLabel("Από");
        to_JLabel = new JLabel("Μέχρι");
        reservationID_JLabel = new JLabel("Διαθέσιμα Ενοικιαζόμενα: ");
        
        from_JTextField = new JTextField();
        to_JTextField = new JTextField();
        reservationID_JTextField = new JTextField();
        
        submit_JButton = new JButton("OK");
        submit_JButton.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/MM/yy");
                    try
                    {
                        Date from_Date = tempDateFormat.parse(from_JTextField.getText());
                        Date to_Date = tempDateFormat.parse(to_JTextField.getText());
                        String ID_String = reservationID_JTextField.getText();
                        
                        int userOption=-1;
                        if(hotel.hReservations.Available(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String)))
                        {//Εάν το μπήκε το η κράτηση μέσα στο Reservation
                            userOption=JOptionPane.showConfirmDialog(getContentPane(), 
                                    "Θέλεται να κάνεται την κράτηση?\n Θα κοστίσει: "+java_project.Reservation.CostBeforeReservation(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String))+"\u20ac"
                                    ,"",JOptionPane.YES_NO_OPTION);
                            if(userOption==0)
                            {//Εάν πάτησε ναί
                            
                                hotel.hReservations.Add(hotel.hReservations.randomReservationIdGenarator(), currentName, from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String));
                            }
                            
                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(getContentPane(),"Είναι κρατήμένο το "+ID_String);
                        }
                        
                    } catch (ParseException ex) {
                        
                    } catch(NullPointerException ex)
                    {
                        
                    }
                    
                }
            }
        
        );
        
        reservationItems_JComboBox = new JComboBox(new String[] 
        {"","Monoklino","Diklino","Triklino",
         "Politeles Domatio","Gourouna","Autokinito","Mixanaki",
         "Aithousa Ekdiloseon"});
        reservationID_JComboBox = new JComboBox(hotel.ItemIDs(reservationItems_JComboBox.getSelectedItem().toString()).toArray());
        
        reservationID_JComboBox.setSize(50,50);
        
        reservationItems_JComboBox.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    reservationID_JLabel.setText("Διαθέσιμα Ενοικιαζόμενα: "+String.join(",",hotel.ItemIDs(reservationItems_JComboBox.getSelectedItem().toString())));
                }
            }
        );
        
        reservationID_JTextField.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String tempReservationItemID = reservationID_JTextField.getText();
                    
                    try
                    {
                        int tempReservationItemID_int = Integer.parseInt(tempReservationItemID);
                        
                    }
                    catch(NumberFormatException ex)
                    {
                        reservationID_JTextField.setText("Dose kati to opoio einai arithmos kai oxi: "+tempReservationItemID);
                    }
                    
                }
            }
        );
        
        
        
        this.guiPane.add(from_JLabel);
        this.guiPane.add(from_JTextField);
        this.guiPane.add(to_JLabel);
        this.guiPane.add(to_JTextField);
        this.guiPane.add(reservationItems_JComboBox);
        
        
        JPanel reservationInfo_JPanel  = new JPanel(new GridLayout(2,1));
        reservationInfo_JPanel.add(reservationID_JLabel);
        reservationInfo_JPanel.add(reservationID_JTextField);
        this.guiPane.add(reservationInfo_JPanel);
        this.guiPane.add(submit_JButton);
        //this.guiPane.add(reservationID_JComboBox);
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    /* Parakato kodikas gia oles tis methodous
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,4);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    */
    
}
