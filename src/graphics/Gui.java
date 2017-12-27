package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java_project.Car;
import java_project.Reservation;

public class Gui extends JFrame
{
    public final int windowWidth = 800;
    public final int windowHeight = 800;
    
    public java_project.Hotel hotel;
    
    private Container guiPane;
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
                    MainMenu(login_name);
                }
            }
        

        );
        
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
            {
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
        JLabel from_JLabel,to_JLabel;
        JTextField from_JTextField,to_JTextField;
        JComboBox reservationItems_JComboBox,reservationID_JComboBox;
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(4,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        from_JLabel = new JLabel("Από");
        to_JLabel = new JLabel("Μέχρι");
        
        from_JTextField = new JTextField();
        to_JTextField = new JTextField();
        
        reservationItems_JComboBox = new JComboBox(new String[] {"Οχήματα","Διαμερίσματα","Αίθουσα"});
        reservationID_JComboBox = new JComboBox(new String[] {"1","2","3"});
        
        reservationID_JComboBox.setSize(50,50);
        
        reservationItems_JComboBox.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    if(reservationItems_JComboBox.getSelectedItem().toString().equalsIgnoreCase("Οχήματα"))
                    {
                        DefaultComboBoxModel tempModel = new DefaultComboBoxModel();
                        HashMap<Car,TreeMap<Date,Reservation>> tempHas = (HashMap) hotel.hReservations.reserveList.get(1);//Παίρνω τα οχήματα
                        
                    }
                }
            }
        );
        
        this.guiPane.add(from_JLabel);
        this.guiPane.add(from_JTextField);
        this.guiPane.add(to_JLabel);
        this.guiPane.add(to_JTextField);
        this.guiPane.add(reservationItems_JComboBox);
        this.guiPane.add(reservationID_JComboBox);
        
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
