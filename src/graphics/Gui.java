package graphics;

import javax.swing.*;//Διάφορα import που χρειάζονται για να λειτουργεί
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_project.Reservation;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Gui extends JFrame
{//Κλάση Gui που κάνει extend το Frame για να δημιουργήσω το παράθυρο
    public final int windowWidth = 800;//οι διαστάσεις του παραθύρου
    public final int windowHeight = 800;
    
    public java_project.Hotel hotel;//ένα αντικείμενου τύπου hotel το οποίο είναι το ξενοδοχείου που φτιάξαμε στο προιγούμενο κομμάτι της άσκησης
    
    private Container guiPane;//ένα container που χρειασημοιποιώ σε κάθε συνάρτηση ώστε να αλλάζω το περιεχόμενο του παραθύρου
    
    private String currentName;//ενα αντικείμενο τυπου string
    public Gui(java_project.Hotel hotel)
    {//constructor της κλάσης
        super("Ξενοδοχείο Αιγαίο");//δηλώνω τίτλο στο παράθυρο που θα δημιουργηθεί//καλή την υπερκλάση για να θέσει το όνομα του παραθύρου
        this.hotel=hotel;//Αναφορά στο ξενοδοχείο//αποθήκεύει το ξενοδοχείου σε μια μεταβλητή της κλάσης
        this.setSize(windowWidth,windowHeight);//θέτω το μήκος και πλάτος του παραθύρου
        this.setVisible(true);//κάνω το παράθυρο να φαίνεται
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//εάν πατήσει κάποιος exit κλήνει όλο το πρόγραμμα
        
        
        
        LoginMenu();//καλό το state LoginMenu το οποίο αλλάζει το παράθυρο
    }
    
    
    
    public void LoginMenu()
    {
        JLabel login_JLabel1,login_JLabel2;//Δηλώνω δύο labels
        JTextField login_JTextField;//Και ένα Textfiled για να μπορώ να γράψω
        String login_JLabel1Text = "Kalosirthate gia sas gia sas gia sas gia sas gias sas";//κείμενο
        String login_JLabel2Text = "Όνομα";
        
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();//θέτω το guiPane στο pane που υπάρχει είδη στο παράθυρο
        GridLayout login_BorderLayout = new GridLayout(4,2);//θέτω το layout του παραθύρου
        this.guiPane.setLayout(login_BorderLayout);//το θέτω στο παραπάνω pane
        
        login_JLabel1 = new JLabel(login_JLabel1Text);//φτιάχνω ένα νέο αντικείμενο τύπου JLabel και του βάζω σαν text το login_JLavel1Text
        //login_JLabel1.setSize(250,250);
        
        login_JLabel2 = new JLabel(login_JLabel2Text,JLabel.CENTER);//το τοποθετώ στο κέντρο
        
        login_JTextField = new JTextField();//δημιουργώ ένα νέο textfield στο οποίο μπορεί να πληκτρολογήσει ο παίκτης
        
        
        
        
        login_JTextField.addActionListener(//βάζω actionListener ο οποίος ακούει οταν πατήσει ο χρήστης enter στο textfield
            new ActionListener()
            {//νέο αντικείμενο ActionListener
                public void actionPerformed(ActionEvent e)
                {//συνάρτηση lambda η οποία τσεκάρει για τι διάφορα events στην προκημένη περίπτωση πότε πατήθηκε enter
                    String login_name = login_JTextField.getText();//παίρνει το όνομα απο το πεδίο και το αποθηκεύει σε μεταβλητή
                    currentName=login_name;
                    MainMenu(login_name);//καλή την MainMenu με text το κείμενο που έδοσε ο χρήστης
                }
            }
          
        

        );
        //System.out.println(this.getFocusOwner().toString());
        this.guiPane.add(login_JLabel1);//προσθέτει στο Pane τα διάφορα αντικείμενα ώστε να φαίνονται μέσα στην οθόνη της εφαρφμογής
        this.guiPane.add(login_JLabel2);//παρομοίος
        this.guiPane.add(login_JTextField);//παρομοίος
        
        this.setContentPane(this.guiPane);//θέτω το pane του jframe στο pane που δημιούργησα και έβαλα αντικείμενα πιο πριν
        
    }
    
    
    public void MainMenu(String userName)
    {
        JLabel title_JLabel,menuTitle_JLabel;//δηλώνω δυο JLabel αντικείμενα
        JButton option1_JButton,option2_JButton,option3_JButton,option4_JButton,option5_JButton;//Δηλώνω τρία αντικείμενα τύπου JButton
        
        JLabel hotelImage_JLabel;//αντικείμενο τύπου label
        
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();//θέτω το guiPane με το current pane
        GridLayout mainMenu_BorderLayout = new GridLayout(4,2);//δημιουργώ ένα νέο αντικείμενο gridlayout στο οποίο και το τοποθετώ πιο μετά τα αντικείμενα
        this.guiPane.setLayout(mainMenu_BorderLayout);//θέτω το layout αυτου το pane
        
        title_JLabel = new JLabel("Ξενοδοχείο αιγαίο");//ορίζω όνομα στο laybel
        title_JLabel.setFont(new Font("Serif", Font.BOLD, 30));//ορίζω το μέγεθος γραμματοσειρας
        
        menuTitle_JLabel = new JLabel("Μενού Επιλογών");//ορίζω όνομα στο laybel
        menuTitle_JLabel.setFont(new Font("Serif", Font.BOLD, 20));//ορίζω το μέγεθος γραμματοσειρας
        
        option1_JButton = new JButton("Dimiourgia");//ορίζω όνομα στο button
        option2_JButton = new JButton("Diagrafi");//ορίζω όνομα στο button
        option3_JButton = new JButton("Anazitisi");//παρομοίος
        option4_JButton = new JButton("Diathesima domatia");//παρομοίος
        option5_JButton = new JButton("Advanced");//παρομοίος
        
        option1_JButton.addActionListener(//actionlistener ο οποίος κοιτάει πότε πατιέται το κουμπί
        new ActionListener()
        {//νέο αντικείμενο τύπου Actionlister
            public void actionPerformed(ActionEvent e)//κάνω overwrite την μέθοδο actionPerformed
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για καινούργια κράτηση
                NewReservationForm();
            }
        } 
        );
        
        option2_JButton.addActionListener(
        new ActionListener()//actionlistener ο οποίος κοιτάει πότε πατιέται το κουμπί
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για διαγραφή κράτησης
                DeleteResvationForm();
            }
        } 
        );
        
        option3_JButton.addActionListener(
        new ActionListener()//actionlistener ο οποίος κοιτάει πότε πατιέται το κουμπί
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για έλενχο κράτησης
                CheckReservationForm();
            }
        } 
        );
        
        option4_JButton.addActionListener(
        new ActionListener()//actionlistener ο οποίος κοιτάει πότε πατιέται το κουμπί
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για έλενχο κενών δωματίων
                RentalsAvailabilityDatesPopup();
            }
        } 
        );
        
        option5_JButton.addActionListener(
        new ActionListener()//actionlistener ο οποίος κοιτάει πότε πατιέται το κουμπί
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα advanced menu
                AdvancedMenu();
            }
        } 
        );
        
        this.guiPane.add(title_JLabel);//βάζω το αντικείμενο μέσα το Pane
        this.guiPane.add(menuTitle_JLabel);//βάζω το αντικείμενο μέσα το Pane
        
        this.guiPane.add(option1_JButton);//βάζω το JButton μέσα το Pane
        this.guiPane.add(option2_JButton);//βάζω το JButton μέσα το Pane
        this.guiPane.add(option3_JButton);//βάζω το JButton μέσα το Pane
        this.guiPane.add(option4_JButton);//βάζω το JButton μέσα το Pane
        this.guiPane.add(option5_JButton);//βάζω το JButton μέσα το Pane
        
        this.setContentPane(this.guiPane);//βάζω το pane στο contentpane του current frame
        
    }
    
    public void NewReservationForm()
    {
        JLabel from_JLabel,to_JLabel,reservationID_JLabel;//δηλώνω δύο αντικείμενα JLabel
        JTextField from_JTextField,to_JTextField,reservationID_JTextField;//δηλώνω JTextField αντικείμενο
        JComboBox reservationItems_JComboBox,reservationID_JComboBox;//φτιάχνω JComboBox αντικείμενο
        JButton submit_JButton,back_JButton;//δημιουργώ 2 κουμπιάα
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(4,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        from_JLabel = new JLabel("Από");//βάζω κείμενο στο label και το δημιουργώ
        to_JLabel = new JLabel("Μέχρι");//παρομοίως
        reservationID_JLabel = new JLabel("Διαθέσιμα Ενοικιαζόμενα: ");//νέο αντικείμενο τύπου  JLabel
        
        from_JTextField = new JTextField();//φτίαχνω νέο αντικείμενο JTextField
        to_JTextField = new JTextField();//φτίαχνω νέο αντικείμενο JTextField
        reservationID_JTextField = new JTextField();//φτίαχνω νέο αντικείμενο JTextField
        
        submit_JButton = new JButton("OK");//φτίαχνω νέο αντικείμενο JButton και το βάζω όνομα
        back_JButton = NewBackButton();//καλώ την συνάρτηση NewBackButton() που επιστρέφει ένα έτοιμο κουμπί
        
        reservationItems_JComboBox = new JComboBox(new String[] //φτίαχνω ένα JComboBox και σαν επιλογή του βάζω όλα τα αντικείμενα που μπορεί να πάρει
        {"","Monoklino","Diklino","Triklino",
         "Politeles Domatio","Gourouna","Autokinito","Mixanaki",
         "Aithousa Ekdiloseon"});
        reservationID_JComboBox = new JComboBox(hotel.ItemIDs(reservationItems_JComboBox.getSelectedItem().toString()).toArray());//μετατρέπω την επιλογή του χρήστη στο 
        //αντίστοιχο αλφαριθμητικό του ονόματος της κλάσης και με αυτό παίρνω όλα τα ID που είναι τέτοιου τύπου
        
        submit_JButton.addActionListener(//βάζω actionlistener
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {//αν πατηθεί το κουμπί
                    SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/MM/yy");//φτιάχνω ένα αντικείμενο τύπου SimpleDateFormat το οποίο 
                    //χρειάζεται για να μετρέψω αντικείμενα τύπου Date σε αλφαρηθμητικά και αντίστροφα
                    try
                    {//δοκίμασε 
                        Date from_Date = tempDateFormat.parse(from_JTextField.getText());//πάρε την τιμή του textfield και έπειτα μετάτρεψε το σε Date
                        Date to_Date = tempDateFormat.parse(to_JTextField.getText());//πάρε την τιμή του textfield και έπειτα μετάτρεψε το σε Date
                        
                        if(from_Date.after(to_Date))
                        {//Εάν το from_Date > to_Date
                            to_JTextField.setText(from_JTextField.getText());//βάλε στο text του δεύτερου label το κείμενο του πρώτου
                            to_Date = tempDateFormat.parse(to_JTextField.getText());//και θέσε το to_Date με βάση αυτό
                        }
                        
                        String ID_String = reservationID_JTextField.getText();//πάρε το κείμενο (ID) από το αντίστοιχο textfield
                        
//                        if(reservationItems_JComboBox.getSelectedItem().toString().isEmpty())
//                        {
//                            ((DefaultComboBoxModel) reservationItems_JComboBox.getModel()).getIndexOf(ABORT);
//                        }
                        
                        int userOption=-1;//ένα νουμερο που κρατάει την επιλογή του χρήστη
                        if(hotel.hReservations.rentalsList.get(ID_String)==null)
                        {//εάν δεν υπάρχει αυτό το ID
                            JOptionPane.showMessageDialog(getContentPane(),"Το αντικείμενο που προσπαθείς να προσθέσεις δεν υπάρχει");
                        }
                        else if(hotel.hReservations.Available(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String)))
                        {//Εάν το μπήκε  η κράτηση μέσα στο Reservation
                            userOption=JOptionPane.showConfirmDialog(getContentPane(), 
                                    "Θέλεται να κάνεται την κράτηση?\n Θα κοστίσει: "+java_project.Reservation.CostBeforeReservation(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String))+"\u20ac"
                                    ,"",JOptionPane.YES_NO_OPTION);//ένα μύνημα επιβεβαίωσης
                            if(userOption==0)
                            {//Εάν πάτησε ναί
                                String randomStringReservationID = hotel.hReservations.randomReservationIdGenarator();//παίρνω ένα τυχαίο string απο την randomReservationIdGenerator
                                boolean added = hotel.NewReservation(randomStringReservationID, currentName, from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String));
                                //προσθέτω νέα κράτηση στο ημερολόγιο κρατήσεων
                                
                                if(added)
                                {//εάν προσθέτηκε
                                    JOptionPane.showMessageDialog(getContentPane(),"Η κράτηση σας καταχωρήθηκε επιτυχώς\n"
                                        + "ID Κράτησης: "
                                        + randomStringReservationID);//εμφανίζει μύνημα επιτυχίας
                                }
                                
                                //System.out.println("YYYYYYYYYYYYYYYYYYYYeinai " + hotel.hReservations.Available(from_Date, from_Date, hotel.hReservations.rentalsList.get(ID_String)));
                            }
                            
                            
                        } 
                        else
                        {//αν δεν είναι διαθέσιμο
                            JOptionPane.showMessageDialog(getContentPane(),"Είναι κρατήμένο το "+ID_String);//εμφανίζει αντίστοιχο μύνημα
                        }
                        
                        
                        
                    } catch (ParseException ex) 
                    {//Εάν υπάρχει ParseException
                        JOptionPane.showMessageDialog(getContentPane(),"Η ημερομηνία πρέπει να είναι της μορφής dd/mm/yy \nπ.χ 10/08/17","Λάθος ημερομηνία",JOptionPane.ERROR_MESSAGE);
                        //εμφανίζει μύνημα
                    } 
                    catch(NullPointerException ex)
                    {//Εάν υπάρχει NullPointerException
                        System.out.println("now you see when ;)");//εκτυπώνει στην κονσόλα
                    }
                    
                }
            }
        
        );
        
        reservationID_JComboBox.setSize(50,50);//θέτω το μέγεθος του combobox
        
        reservationItems_JComboBox.addActionListener(//βάζω listener
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {//βάζω τα διαθέσιμα ενοικιαζόμενα
                    reservationID_JLabel.setText("Διαθέσιμα Ενοικιαζόμενα: "+String.join(",",hotel.ItemIDs(reservationItems_JComboBox.getSelectedItem().toString())));
                }
            }
        );
        
        reservationID_JTextField.addActionListener(//βάζω actionlistener
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String tempReservationItemID = reservationID_JTextField.getText();//παίρνει το text από την ετοικέτα
                    
                    try
                    {
                        int tempReservationItemID_int = Integer.parseInt(tempReservationItemID);//δοκιμάζει να την μετατρέψει σε αριθμό
                        
                    }
                    catch(NumberFormatException ex)
                    {//εαν exception βγάζει μύνημα λάθους
                        reservationID_JTextField.setText("Dose kati to opoio einai arithmos kai oxi: "+tempReservationItemID);
                    }
                    
                }
            }
        );
        
        
        
        this.guiPane.add(from_JLabel);//προσθέτει στον καμβά το αντικείμενο
        this.guiPane.add(from_JTextField);//παρομοίως
        this.guiPane.add(to_JLabel);//παρομοίως
        this.guiPane.add(to_JTextField);//παρομοίως
        this.guiPane.add(reservationItems_JComboBox);//παρομοίως
        
        
        JPanel reservationInfo_JPanel  = new JPanel(new GridLayout(2,1));//δημιουργώ ένα panel το οπόιο του βάζω gridlayout σαν layout
        reservationInfo_JPanel.add(reservationID_JLabel);//προσθέτω την ετικέτα στο παραπάνω
        reservationInfo_JPanel.add(reservationID_JTextField);//παρομοίως
        this.guiPane.add(reservationInfo_JPanel);//παρομοίως
        this.guiPane.add(submit_JButton);//παρομοίως
        this.guiPane.add(back_JButton);//παρομοίως
        //this.guiPane.add(reservationID_JComboBox);
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void DeleteResvationForm()
    {//διαγράφη μια κράτηση
        JLabel deleteID_JLabel;//δηλώνω αντικείμενο
        JTextField deleteID_JTextField;//παρομοίως
        JButton confirm_JButton,back_JButton;//παρομοίος
        
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(2,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        deleteID_JLabel = new JLabel("Γράψε το ID της κράτησης που θέλεις να διαγράψεις");//νεο αντικείμενο και περνάω όνομα στον constructor
        deleteID_JTextField = new JTextField();//νεο textfield
        
        confirm_JButton = new JButton("Confirm");//νεο κουμπί
        back_JButton = NewBackButton();//κουμπί απο την συνάρτηση NewBackButtonn που πάει στο αρχικό μενου
        confirm_JButton.addActionListener(//βάζω actionListener
            new ActionListener()//νεος actionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {//δοκίμασε
                        
                        String ID_String = deleteID_JTextField.getText();//πάρε το κείμενο απο το παιδίο και βάλτο σε ένα String
                        
                        int userOption=-1;//θέτω ένα αριθμό == 1
                        if(hotel.Search_code(ID_String, currentName).getID()!=null)
                        {//εάν υπάρχει η κράτηση με αυτό το ID
                            userOption=JOptionPane.showConfirmDialog(getContentPane(), 
                                    "Είστε σίγουροι ότι θέλεται να διαγράψεται την κράτηση με ID: "
                                    + ID_String
                                    ,"",JOptionPane.YES_NO_OPTION
                            );//εμφανίζει μύνημα
                            
                            if(userOption==0)
                            {//Εάν πατήσει ναι το οποίο επιστρέφει στο userOption 0 τότε
                                boolean deleted = hotel.Delete_reservation(ID_String,currentName);//διαγράφω την κράτηση και ελέγχω εάν διαγράφηκε επιτυχός
                                
                                if(deleted)
                                {//εάν διαγράφηκε
                                    JOptionPane.showMessageDialog(getContentPane(),"H κράτηση με ID: "
                                        + ID_String
                                        + " διαγράφηκε επιτυχώς"
                                    );//εμφανίζει αντίστοιχο μύνημα
                                }
                            }
                        }
                        else
                        {//αλλιώς αν δεν υπάρχει κράτηση με τέτοιο ID
                            JOptionPane.showMessageDialog(getContentPane(),"Δεν υπάρχει κράτηση με ID: "
                                    +ID_String
                                    +"\n                ή\nη κράτηση δεν είναι στο όνομα σας"
                            );//εμφανίζει αντοίστιχο μύνημα
                        }
                    }
                    catch(NullPointerException ex)
                    {//εαν υπάρξει NullPointerException
                        JOptionPane.showMessageDialog(getContentPane(),"Δεν υπάρχει καμία τιμή μέσα στο παιδίο","Καμία τιμή",JOptionPane.ERROR_MESSAGE);
                        //εμφανίζει μύνημα
                    }
                }
            }
        
        
        );
        
        this.guiPane.add(deleteID_JLabel);//προσθέτη τα αντικείμενα στο Pane
        this.guiPane.add(deleteID_JTextField);//παρομοίως
        this.guiPane.add(confirm_JButton);//παρομοίως
        this.guiPane.add(back_JButton);//παρομοίως
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void CheckReservationForm()
    {//ελέγχει εάν υπάρχει μια κράτηση
        JLabel checkID_JLabel,reservationInfo_JLabel;//δηλώνω αντικείμενο τύπου JLabel
        JTextField checkID_JTextField;//δηλώνω αντικείμενο τύπου JTextField
        JButton confirm_JButton,back_JButton;//και τύπου JButton
        
        checkID_JLabel = new JLabel("Δώσε ID της κράτησης που θέλεις να δείς: ");//νέο JLabel με το σωστό κείμενο
        reservationInfo_JLabel = new JLabel();//αρχηκοποιώ το αντικείμενο
        
        checkID_JTextField = new JTextField();//παρομοίως
        
        confirm_JButton = new JButton("Confirm");//νεο κουμπί με κατάληλο όνομα
        back_JButton = NewBackButton();//νεο backButton
        
        confirm_JButton.addActionListener(//βάζω actionListener
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {//δοκίμασε
                        String ID_String = checkID_JTextField.getText();//πάρε το κείμενο απο το textfield
                        
                        Reservation tempReservation = hotel.Search_code(ID_String, currentName);//δοκιμάζει να πάρει την κράτηση ανάλογα με το ID που δόθηκε
                        if(tempReservation.getID()==null)
                        {//εάν το id είναι null
                            JOptionPane.showMessageDialog(getContentPane(), "Δεν υπάρχει κράτηση με αυτό το ID στο όνομα σας.");//εμφανίζει μύνημα
                        }
                        else
                        {//αλλιώς
                            reservationInfo_JLabel.setText(tempReservation.toString(true));//καλεί την σωστή toString για να εμφανίσει σωστά την πληροφορίες
                        }
                    }
                    catch(NullPointerException ex)
                    {//εάν υπάρξει NullPointerException εκτυπώνει στη κονσόλα μύνημα
                        System.out.println("Gia kapoio logo to id einai null kati paize me kapia sinartisi sou");
                    }
                }
            }
        );
        
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        this.guiPane.add(checkID_JLabel);//βάζω το αντικείμενο στο Pane
        this.guiPane.add(checkID_JTextField);//παρομοίως
        this.guiPane.add(reservationInfo_JLabel);//παρομοίως
        this.guiPane.add(confirm_JButton);//παρομοίως
        this.guiPane.add(back_JButton);//παρομοίως
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void RentalsAvailabilityDatesPopup()
    {//δείχνει ένα Popup στο οποιο διαλέγει ημερομηνίες για την συνάρτηση RentalsAvailability
        JLabel fromDate_JLabel,toDate_JLabel;//δηλώνω αντικείμενα JLabel
        JTextField fromDate_JTextField,toDate_JTextField;//δηλώνω αντικείμενα JTextField
        
        Date from_Date=null,to_Date=null;//θέτω δύο ημερομηνίες με null για να μην βγάζουν πρόβλημα πιο μετά
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,4);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        fromDate_JLabel = new JLabel("Ημερομηνία από");//νεα Jlabel με αντίστοιχα ονόμα και string στον constructor
        toDate_JLabel = new JLabel("Ημερομηνία μέχρι");
        
        fromDate_JTextField = new JTextField();//νεο textField
        toDate_JTextField = new JTextField();//παρομοίως
        
        final JComponent[] message_JComponents = new JComponent[]//νέος πίνακας JComponent στον οποίο βάζω τα αντικείμεα που φτιάξαμε παραπάνω
        {
            fromDate_JLabel,
            fromDate_JTextField,
            toDate_JLabel,
            toDate_JTextField
        };
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
        
        
        boolean checkIfNotAnswered=true;//θέτω boolean
        SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/MM/yy");//αντικείμενο τύπου SimpleDateFormat το οποίο χρειάζεται για την μετατροπή string
        //με προκαθορισμένη μορφή σε αντικείμενο τύπου Date και αντίστροφα
        while(checkIfNotAnswered)
        {//ώσο είναι αληθές
            int result = JOptionPane.showConfirmDialog(getContentPane(), message_JComponents,"Διάλεξε ημερομηνία",JOptionPane.PLAIN_MESSAGE);//δείχνει το popup
            //και περιμένει απάντηση
            try
            {//δοκίμασε
                if(result==JOptionPane.OK_OPTION)
                {//εάν πάτησε Ok ο χρήστης
                    from_Date = tempDateFormat.parse(fromDate_JTextField.getText());//πάρε το κείμενο απο το παιδίο και κάντο Date
                    to_Date = tempDateFormat.parse(toDate_JTextField.getText());//παρομοίως
                    
                    if(!from_Date.after(to_Date))
                    {//εάν from_Date<=to_Date
                        checkIfNotAnswered=false;//θέτω με false ώστε να μην ξαναγίνει το Loop
                    }
                }
                else if(result==JOptionPane.CLOSED_OPTION)
                {//εάν πάτησε X
                    MainMenu(currentName);//γυρίζει στο αρχικό μενού
                    return;//επιστρέφει το οποίο τελειώνει αυτή τη συνάρτηση
                }
            }
            catch (ParseException ex) 
            {//εάν υπάρχει προβλήμα στην μετατροπή απλά ξαναγίνεται το loop
                
            }
            
            
        }
        
        
        
        RentalsAvailabilityForm(from_Date, to_Date);//αφού βγεί απο το Loop τότε καλεί την συνάρτηση RentalsAvailabilityForm με ορίσματα τις δύο ημερομηνίες που 
        //δόθηκαν
    }
    
    public void RentalsAvailabilityForm(Date from_Date,Date to_Date)
    {//δείχνει ένα πίνακα με όλα τα ενοικιαζόμενα κια πότε είναι ελεύθερα ανάλογα με τα παραπάνω Dates
        
        
        JTable mainTable_JTable;//ένα νέο JTable
        List<String> Dates_String;//μια λίστα με string
        List<ArrayList<String>> listEntrys_String;//νεο αντικειμενο τύπου λιστ που δέχεται αντικείμενα τύπου Arraylist που δέχεται αντικείμενα τύπου string
        JButton back_JButton;//ένα νεο κουμπί
        JScrollPane mainPane_JScrollPane;//και ένα JScrollPane
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(2,1);
        //this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        
        Dates_String = new ArrayList<>();//;ένα νέο ArrayList
        listEntrys_String = new ArrayList<>();//αρχηκοποιώ το αντικείμενο
        
        back_JButton = NewBackButton();//δημιουργώ το κουμπί
        
        SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/MM/yy");//για μετατροπη απο Date σε string και αντίστροφα
        
        long difference = to_Date.getTime() - from_Date.getTime();//παίρνουμε την διαφορά μεταξύ τους σε milliseconds
        long daysBetween = (difference / (1000*60*60*24));//απο μιλι-δευτερολεπτα τα κάνουμε μέρες
        
                
        
        Calendar tempCal = Calendar.getInstance();//παίρνω το instance του calendar
        tempCal.setTime(from_Date);//θέτω την ώρα με βάση το from_Date
        Date tempDate = tempCal.getTime();//θέτω την ημερομηνία με βάση το calendar
        
        Dates_String.add("Ενοικιαζόμενα");//βάζω ένα νέο αντικείμενο μέσα στη λιστα
        
        
        boolean ifFirstTry=true;//θέτω ένα boolean
        
        
        for(Map.Entry<String,Object> mapObject: hotel.hReservations.rentalsList.entrySet())
        {//αφου μετατρέψω σε Map.Entry προσπελνώ τη λίστα
            ArrayList<String> tempReservationCheckList_String = new ArrayList<>();//νέο αντικείμενο τύπου Arraylist
            tempCal.setTime(from_Date);//βάζω στο Calendar την σωστή ημερομηνία
            
            for(tempDate=tempCal.getTime();!tempDate.after(to_Date);tempCal.add(Calendar.DATE,1),tempDate=tempCal.getTime())
            {//ξεκινόντας απο την ημερομηνία from_Date αυξάνω την τοπική ημερομηνία μέχρι και το to_Date
                if(tempDate.equals(from_Date))
                {//εάν είναι η ίδια μέρα
                    try
                    {//δοκίμασε
                        Method tempMethod = mapObject.getValue().getClass().getMethod("getID", null);//αποθηκεύω την μέθοδο της αντίστοιχης κλάσεις του αντικειμένου
                        //mapObject.getValue() ώστε να μπορέσω να έχω πρόσβαση σε αυτή και το αποτέλεσμα της
                        String ID_String = (String) tempMethod.invoke(mapObject.getValue(), null);//αποθήκευω το αποτέλεσμα της κλήσης της μεθόδου (μέσο reflection)
                        //το οποίο αν επιτυχές μου επιστρέφει το αποτέλεσμα της συνάρτησης getId η οποία επιστρέφει string
                        tempReservationCheckList_String.add(ID_String);//προσθέτω αυτό το string στη λίστα
                    } catch (NoSuchMethodException ex) {//διάφορα exception για να λειτουργεί το reflection απο πάνω
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(ifFirstTry)
                {//εάν είναι η πρώτη προσπάθεια
                    Dates_String.add(tempDateFormat.format(tempDate));//βάζει την ημερομηνία στη λίστα
                }
                
                    if(hotel.hReservations.Available(tempDate, tempDate, mapObject.getValue()))
                    {//εάν το δωμάτιο είναι ελεύθερο
                        tempReservationCheckList_String.add("Ok");//βάζει στη αντίστοιχη θέση του πίνακα οκ
                    }
                    else
                    {//αλλιώς
                        tempReservationCheckList_String.add("No"); //βάζει όχι
                    }
            }
            ifFirstTry=false;//θέτω το firstTry με false
            listEntrys_String.add(tempReservationCheckList_String);//προσθέτω στη λίστα το arrayList
        }
        
        
        String[][] tempArrayForValues = new String[listEntrys_String.size()][];//ένας πίνακας δύο διαστάσεων ο οπίος έχει μήκος το μέγεθος των εγγραφών της λιστασ
        //και πλατος μη ορισμένο
        for (int i = 0; i < listEntrys_String.size(); i++) 
        {//προσπελνώ όλο τη λίστα
            ArrayList<String> row = listEntrys_String.get(i);//παίρνω το αντιστοιχο στοιχείο και το βάζω στην αντίστοιχη θέση
            tempArrayForValues[i] = row.toArray(new String[row.size()]);//μετατρέπω αυτό το ArrayList σε array
        }
        
        mainTable_JTable = new JTable(tempArrayForValues,Dates_String.toArray());//αρχηκοποιώ το JTable με ορίστατα της τιμές, τον πίνακα ημερομηνιών
        mainTable_JTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//κάνω το table να μην κάνει autoresize
        
        
        mainPane_JScrollPane = new JScrollPane(mainTable_JTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //κάνω το table να έχει scroll bars
        this.guiPane.add(mainPane_JScrollPane);//το προσθέτω στο Pane
        this.guiPane.add(back_JButton);//παρομοίος
        

        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void AdvancedDatesPopup()
    {//ίδιο μενού με το RentalsAvailabilityDatesPopup απλά αλλάζει το που επιστρέφει αν πατηθεί κλείσειμο
        //και που άν πατηθεί OK
        JLabel fromDate_JLabel,toDate_JLabel;
        JTextField fromDate_JTextField,toDate_JTextField;
        
        Date from_Date=null,to_Date=null;
        
        
        
        fromDate_JLabel = new JLabel("Ημερομηνία από");
        toDate_JLabel = new JLabel("Ημερομηνία μέχρι");
        
        fromDate_JTextField = new JTextField();
        toDate_JTextField = new JTextField();
        
        final JComponent[] message_JComponents = new JComponent[]
        {
            fromDate_JLabel,
            fromDate_JTextField,
            toDate_JLabel,
            toDate_JTextField
        };
        
       
        
        
        boolean checkIfNotAnswered=true;
        SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/MM/yy");
        while(checkIfNotAnswered)
        {
            int result = JOptionPane.showConfirmDialog(getContentPane(), message_JComponents,"Διάλεξε ημερομηνία",JOptionPane.PLAIN_MESSAGE);
            
            try
            {
                if(result==JOptionPane.OK_OPTION)
                {
                    from_Date = tempDateFormat.parse(fromDate_JTextField.getText());
                    to_Date = tempDateFormat.parse(toDate_JTextField.getText());
                    
                    if(!from_Date.after(to_Date))
                    {
                        checkIfNotAnswered=false;
                    }
                }
                else if(result==JOptionPane.CLOSED_OPTION)
                {
                    AdvancedMenu();
                    return;
                }
            }
            catch (ParseException ex) 
            {
                
            }
            
            
        }
        CompletionChartDiagram(from_Date, to_Date);
    }
    
    public void AdvancedMenu()
    {//Μενού που περιέχει κάποιες λίγο πιο πολύπλοκες διαδικασίες του ξενοδοχείου
        
        JButton getReservations_JButton,showBarChart_JButton,back_JButton;//δηλώνω τα κουμπιά
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,4);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        getReservations_JButton = new JButton("Σε αρχείο");//νέο JButton μέ όνομα "Σε αρχείο"
        getReservations_JButton.addActionListener(//βάζω actionListener
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {//κάνω ovewrite την συνάρτηση actionPerformed
                    try(BufferedWriter tempBuffWriter = new BufferedWriter(new FileWriter("./"+"res.txt")))
                    {//δοκιμάζω να ανοίξω (ή δημιουργήσω) το αρχείο res.txt στο οποίο και γράφω τις εγγραφές
                        for(Reservation tempReservation: hotel.Get_Reservations())
                        {//για κάθε κράτηση του ξενοδοχείου
                            tempBuffWriter.write(tempReservation.toFile());//γράψε το αποτέσεσμα τις toString στο αρχείο
                            tempBuffWriter.newLine();//άφησε νέα γραμμή
                        }
                    } catch (IOException ex) {//εάν υπάρξει IOException
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
        showBarChart_JButton = new JButton("Γράφημα");//νέο κουμπί με όνομα Γράφημα
        showBarChart_JButton.addActionListener(//νέος actionListener
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    AdvancedDatesPopup();//καλώ τη συνάρτηση AdvancedDatesPopup
                    
                }
            }
        
        );
        
        back_JButton = NewBackButton();//δημιουργώ το back Button με βάση την συνάρτηση
        
        this.guiPane.add(getReservations_JButton);//βάζω το αντικείμενο στο Pane
        this.guiPane.add(showBarChart_JButton);//παρομοίως
        this.guiPane.add(back_JButton);//παρομοίως
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void CompletionChartDiagram(Date from_Date,Date to_Date)
    {//δείχνει το διαγράμμα πιασμένων δωματίων ανά ημερομηνία
        int values[];
        values = hotel.Get_Fullness_Percentage(from_Date, to_Date).stream().map(i -> (i == null ? 0 : i))
            .mapToInt(Integer::intValue)
            .toArray();//μετατρέπω το αποτέλεσνα της Get_Fullness_Percentage σε stream και έπειτα σε Array
                    
            String labels[] = {"1","2","3","4","5"};//array απο κάποιες τιμές
            BarChart functionBarChart = new BarChart("Γράφημα", values, labels);//φτίαχνω ένα νέο Barchart και του δίνω κάποιες τιμές
                    
            JFrame frame = new JFrame("Histogram");//νέο JFrame μέ όνομα Histogram
            frame.setMinimumSize(new Dimension(400,400));//θέτω το μικρότερο μέγεθος που μπορεί να πάρει το παράθυρο
            frame.add(functionBarChart);//βάζω στο frame το Barchart
            frame.pack();//καλώ την pack η οποίο το μικρένει στο σωστό μέγεθος
            Dimension frameDimension = Toolkit.getDefaultToolkit().getScreenSize();//παίρνω το μέγεθος της τορινής οθόνης
            frame.setLocation(frameDimension.width/2-frame.getSize().width/2, frameDimension.height/2-frame.getSize().height/2);//και το τοποθετώ στο κέντρο
                    
            frame.setVisible(true);//το κάνω visible
    }
    
    public JButton NewBackButton()
    {//Επιστρέφει ένα κουμπί το οποίο γυρνάει πίσω στο αρχικο μενού
        JButton back_JButton = new JButton("Back");//δημιουργώ το κουμπλι με κείμενο πάνω στο κουμπί Back
        back_JButton.addActionListener(//βάζω ένα ActionListener ώστε να καλώ την συνάρτηση MainMenu() όταν πατιέται το κουμπί
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        MainMenu(currentName);//καλήται η main συνάρτηση όταν πατηθεί το κουμπί
                    }
                }
        );
        return back_JButton;
    }
    
    static class BarChart extends JPanel
    {//νέα κλάση Barchart η οποία κάνει extend to JPanel
        private int[] chartValues;//διάφορες μεταβλητές
        private String[] chartLabels;
        private String chartTitle;
        
        public BarChart(String title,int[] values,String[]labels)
        {//constructor της BarChart το οποίο δέχεται τρία ορίσμα
            this.chartTitle = title;//αποθηκέυει τα ορίσμα
            this.chartValues = values;//παρομοίως
            this.chartLabels = labels;//παρομοίως
        }
        
        public void paintComponent(Graphics g)
        {//κάνω overide την συνάρτηση PaintComponent 
            super.paintComponent(g);//Καλεί την υπερκλάση και περνά το g σάν παράμετρο
            
            Random r = new Random();//Δημιουργεί ένα αντικείμενο τύπου Random
            
            
            if(this.chartValues == null || this.chartValues.length==0)
            {//Εάν ο πίνακας είναι null ή το μέγεθος του ειναι 0
                return;
            }
            
            Dimension chartDimension = this.getSize();//παίρνω το μέγεθος του panel
            int panelWidth = chartDimension.width;//αποθηκέω σε μεταβλητή το πλάτος
            int panelHeight = chartDimension.height;//και το ύψος
            int barWidth = panelWidth / this.chartValues.length;//βρήσκω το μήκος κάθε μπάρας
            int maxValue = 0;//θέτω δύο τιμές  =0
            int minValue = 0;
            for(int tempValue:this.chartValues)
            {//προσπελώ τον πίνακα και βρήσκω την μιρκότερη και μεγαλύτερη αντίστοιχα
                maxValue = Math.max(maxValue, tempValue);
                minValue = Math.min(minValue, tempValue);
            }
            
            Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);//θέτω ένα font για τον τίτλο
            FontMetrics titleFontMetrics  = g.getFontMetrics(titleFont);//και φτίαχνω ένα αντικείμενο τύπου FontMetrics με βάση το font απο πάνω
            
            Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);//παρομοίος για την ετοικέτα κάτω
            FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);//παρομοίως
            
            int titleWidth = titleFontMetrics.stringWidth(this.chartTitle);//βρήσκω το μήκος του τίτλου
            int stringHeight = titleFontMetrics.getAscent();//βρήσκω το ύψος του τίτλου
            int stringWidth = (panelWidth - titleWidth) / 2;//βρήσκω την τοποθεσία του τίτλου
            g.setFont(titleFont);//θέτω το font
            g.drawString(this.chartTitle, stringWidth, stringHeight);//και το ζωγραφίζω στο Panel
            
            int top = titleFontMetrics.getHeight();//βρήσκω το ύψος του τιτλου
            int bottom = labelFontMetrics.getHeight();//και το ύψους του label κάτω
            
            
            
            if(maxValue==minValue)
            {//εάν η μέγιστη τιμή ισούτε με την ελάχιστη σταμάτα
                return;
            }
            double barScale = (panelHeight - top - bottom)/(double)(maxValue - minValue);//βρήσκω το scale με το οποίο μεγενθήνω η μικρένω της μπάρες για
            //να χωράνε στην οθόνη
            
           
            
            stringHeight = panelHeight - labelFontMetrics.getDescent();//βρήσκω την τοποθεσία του label
            
            
            
            g.setFont(labelFont);//θέτω το font
            for(int i=0; i<this.chartValues.length;i++)
            {//προσπελνώ όλες της εγγραφές
                int xPos = barWidth*i + 1;//υπολογίζει το x
                int tempValue = this.chartValues[i];//παίρνω και αποθηκεύω την τιμή του πίνακα σε μία τοπική μεταβλητή
                int barHeight = (int) (tempValue * barScale);//βρήσκω το ύψος της μπάρας με βάση την τίμή της και το Scale
                int yPos = top;//θέτω το ύψος που θα το τοποθετηθεί η μπαρα
                
                if(tempValue>=0)
                {//εάν είνα θετική η τιμή
                    yPos += (int) ((maxValue - tempValue)* barScale);//προσθέτω στο y τη διαφορά maxValue - tempValue γινόμενο του Barscale
                    //το οποίο την τοποθετεί και αντίστοιχα ώστε  να είνα όλες σε μια γραμμή το κάτω μέρος τους
                }
                else
                {//αλλιώς αν είναι αρνητική τιμή
                    yPos += (int) (maxValue * barScale);//προσθέτω την μέγιστη τιμή * της scale
                    barHeight = - barHeight;//και αλλάζω το ύωος
                }
                
                g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));//διαλέγω ένα τυχαίο χρώμα
                
                g.fillRect(xPos, yPos, barWidth-2, barHeight);//φτίαχνω την μπάρα με τα παραπάνω στοιχεια (θεση χ, θέση y, μήκος , πλατος)
                g.setColor(Color.BLUE);//θέτω το χρώμμα του περιγράμματος
                g.drawRect(xPos, yPos, barWidth-2, barHeight);//ζωγραφίζω και ένα περίγραμμα
                
                
            }
            
            
            g.drawString("Xronos", stringWidth,stringHeight);//θέτω και το κάτω label
            
            
            
        }
    }

}
