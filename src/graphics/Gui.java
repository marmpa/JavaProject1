package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        GridLayout mainMenu_BorderLayout = new GridLayout(4,2);
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
        
        option2_JButton.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για καινούργια κράτηση
                DeleteResvationForm();
            }
        } 
        );
        
        option3_JButton.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για καινούργια κράτηση
                CheckReservationForm();
            }
        } 
        );
        
        option4_JButton.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {//Εάν πατειθεί το συγκεκριμένο κουμπί τότε μπαίνει στην φόρμα για καινούργια κράτηση
                RentalsAvailabilityDatesPopup();
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
        //this.pack();
    }
    
    public void NewReservationForm()
    {
        JLabel from_JLabel,to_JLabel,reservationID_JLabel;
        JTextField from_JTextField,to_JTextField,reservationID_JTextField;
        JComboBox reservationItems_JComboBox,reservationID_JComboBox;
        JButton submit_JButton,back_JButton;
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
        back_JButton = NewBackButton();
        
        reservationItems_JComboBox = new JComboBox(new String[] 
        {"","Monoklino","Diklino","Triklino",
         "Politeles Domatio","Gourouna","Autokinito","Mixanaki",
         "Aithousa Ekdiloseon"});
        reservationID_JComboBox = new JComboBox(hotel.ItemIDs(reservationItems_JComboBox.getSelectedItem().toString()).toArray());
        
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
                        
                        if(from_Date.after(to_Date))
                        {//Εάν το from_Date > to_Date
                            to_JTextField.setText(from_JTextField.getText());
                            to_Date = tempDateFormat.parse(to_JTextField.getText());
                        }
                        
                        String ID_String = reservationID_JTextField.getText();
                        
//                        if(reservationItems_JComboBox.getSelectedItem().toString().isEmpty())
//                        {
//                            ((DefaultComboBoxModel) reservationItems_JComboBox.getModel()).getIndexOf(ABORT);
//                        }
                        
                        int userOption=-1;
                        if(hotel.hReservations.rentalsList.get(ID_String)==null)
                        {
                            JOptionPane.showMessageDialog(getContentPane(),"Το αντικείμενο που προσπαθείς να προσθέσεις δεν υπάρχει");
                        }
                        else if(hotel.hReservations.Available(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String)))
                        {//Εάν το μπήκε το η κράτηση μέσα στο Reservation
                            userOption=JOptionPane.showConfirmDialog(getContentPane(), 
                                    "Θέλεται να κάνεται την κράτηση?\n Θα κοστίσει: "+java_project.Reservation.CostBeforeReservation(from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String))+"\u20ac"
                                    ,"",JOptionPane.YES_NO_OPTION);
                            if(userOption==0)
                            {//Εάν πάτησε ναί
                                String randomStringReservationID = hotel.hReservations.randomReservationIdGenarator();
                                boolean added = hotel.NewReservation(randomStringReservationID, currentName, from_Date, to_Date, hotel.hReservations.rentalsList.get(ID_String));
                                //προσθέτω νέα κράτηση στο ημερολόγιο κρατήσεων
                                
                                if(added)
                                {
                                    JOptionPane.showMessageDialog(getContentPane(),"Η κράτηση σας καταχωρήθηκε επιτυχώς\n"
                                        + "ID Κράτησης: "
                                        + randomStringReservationID);
                                }
                                
                                //System.out.println("YYYYYYYYYYYYYYYYYYYYeinai " + hotel.hReservations.Available(from_Date, from_Date, hotel.hReservations.rentalsList.get(ID_String)));
                            }
                            
                            
                        } 
                        else
                        {
                            JOptionPane.showMessageDialog(getContentPane(),"Είναι κρατήμένο το "+ID_String);
                        }
                        
                        
                        
                    } catch (ParseException ex) 
                    {
                        JOptionPane.showMessageDialog(getContentPane(),"Η ημερομηνία πρέπει να είναι της μορφής dd/mm/yy \nπ.χ 10/08/17","Λάθος ημερομηνία",JOptionPane.ERROR_MESSAGE);
                    } 
                    catch(NullPointerException ex)
                    {
                        System.out.println("now you see when ;)");
                    }
                    
                }
            }
        
        );
        
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
        this.guiPane.add(back_JButton);
        //this.guiPane.add(reservationID_JComboBox);
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void DeleteResvationForm()
    {
        JLabel deleteID_JLabel;
        JTextField deleteID_JTextField;
        JButton confirm_JButton,back_JButton;
        
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(2,2);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        deleteID_JLabel = new JLabel("Γράψε το ID της κράτησης που θέλεις να διαγράψεις");
        deleteID_JTextField = new JTextField();
        
        confirm_JButton = new JButton("Confirm");
        back_JButton = NewBackButton();
        confirm_JButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String ID_String = deleteID_JTextField.getText();
                        
                        int userOption=-1;
                        if(hotel.Search_code(ID_String, currentName).getID()!=null)
                        {
                            userOption=JOptionPane.showConfirmDialog(getContentPane(), 
                                    "Είστε σίγουροι ότι θέλεται να διαγράψεται την κράτηση με ID: "
                                    + ID_String
                                    ,"",JOptionPane.YES_NO_OPTION
                            );
                            
                            if(userOption==0)
                            {//Εάν πατήσει ναι το οποίο επιστρέφει στο userOption 0 τότε
                                boolean deleted = hotel.Delete_reservation(ID_String,currentName);
                                
                                if(deleted)
                                {
                                    JOptionPane.showMessageDialog(getContentPane(),"H κράτηση με ID: "
                                        + ID_String
                                        + " διαγράφηκε επιτυχώς"
                                    );
                                }
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(getContentPane(),"Δεν υπάρχει κράτηση με ID: "
                                    +ID_String
                                    +"\n                ή\nη κράτηση δεν είναι στο όνομα σας"
                            );
                        }
                    }
                    catch(NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(getContentPane(),"Δεν υπάρχει καμία τιμή μέσα στο παιδίο","Καμία τιμή",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        
        
        );
        
        this.guiPane.add(deleteID_JLabel);
        this.guiPane.add(deleteID_JTextField);
        this.guiPane.add(confirm_JButton);
        this.guiPane.add(back_JButton);
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void CheckReservationForm()
    {
        JLabel checkID_JLabel,reservationInfo_JLabel;
        JTextField checkID_JTextField;
        JButton confirm_JButton,back_JButton;
        
        checkID_JLabel = new JLabel("Δώσε ID της κράτησης που θέλεις να δείς: ");
        reservationInfo_JLabel = new JLabel();
        
        checkID_JTextField = new JTextField();
        
        confirm_JButton = new JButton("Confirm");
        back_JButton = NewBackButton();
        
        confirm_JButton.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        String ID_String = checkID_JTextField.getText();
                        
                        Reservation tempReservation = hotel.Search_code(ID_String, currentName);
                        if(tempReservation.getID()==null)
                        {
                            JOptionPane.showMessageDialog(getContentPane(), "Δεν υπάρχει κράτηση με αυτό το ID στο όνομα σας.");
                        }
                        else
                        {
                            reservationInfo_JLabel.setText(tempReservation.toString(true));
                        }
                    }
                    catch(NullPointerException ex)
                    {
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
        
        this.guiPane.add(checkID_JLabel);
        this.guiPane.add(checkID_JTextField);
        this.guiPane.add(reservationInfo_JLabel);
        this.guiPane.add(confirm_JButton);
        this.guiPane.add(back_JButton);
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
    }
    
    public void RentalsAvailabilityDatesPopup()
    {
        JLabel fromDate_JLabel,toDate_JLabel;
        JTextField fromDate_JTextField,toDate_JTextField;
        
        Date from_Date=null,to_Date=null;
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,4);
        this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
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
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.setContentPane(this.guiPane);
        //.......................................
        
        
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
                    MainMenu(currentName);
                    return;
                }
            }
            catch (ParseException ex) 
            {
                
            }
            
            
        }
        
        
        
        RentalsAvailabilityForm(from_Date, to_Date);
    }
    
    public void RentalsAvailabilityForm(Date from_Date,Date to_Date)
    {
        
        
        JTable mainTable_JTable;
        List<String> Dates_String;
        List<ArrayList<String>> listEntrys_String;
        
        //Κώδικας που χρειάζεται σε κάθε παράθυρο
        this.getContentPane().removeAll();//αφαιρώ τα πάντα απο το Frame
        this.guiPane = this.getContentPane();
        GridLayout NewReservation_GridLayout = new GridLayout(3,4);
        //this.guiPane.setLayout(NewReservation_GridLayout);
        //.......................................
        
        
        Dates_String = new ArrayList<>();
        listEntrys_String = new ArrayList<>();
        
        
        
        
        
        
        SimpleDateFormat tempDateFormat = new SimpleDateFormat("dd/mm/yy");
        
        long difference = to_Date.getTime() - from_Date.getTime();//παίρνουμε την διαφορά μεταξύ τους σε milliseconds
        long daysBetween = (difference / (1000*60*60*24));//απο μιλι-δευτερολεπτα τα κάνουμε μέρες
        
                
        
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(from_Date);
        Date tempDate = tempCal.getTime();
        
        
        
        for(tempDate=tempCal.getTime();!tempDate.after(to_Date);tempCal.add(Calendar.DATE,1),tempDate=tempCal.getTime())
        {
            
            
            Dates_String.add(tempDateFormat.format(tempDate));
            
            
            ArrayList<String> tempReservationCheckList_String = new ArrayList<>();
            
            boolean ifFirstTry=true;
            for(Map.Entry<String,Object> mapObject: hotel.hReservations.rentalsList.entrySet())
            {
                if(ifFirstTry)
                {
                    try
                    {
                        Method tempMethod = mapObject.getValue().getClass().getMethod("getID", null);
                        String ID_String = (String) tempMethod.invoke(mapObject.getValue(), null);
                        tempReservationCheckList_String.add(ID_String);
                    } catch (NoSuchMethodException ex) {
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
                    ifFirstTry=false;
                }
                else
                {
                    if(hotel.hReservations.Available(tempDate, tempDate, mapObject.getValue()))
                    {
                    
                        tempReservationCheckList_String.add("Ok");
                    }
                    else
                    {
                        tempReservationCheckList_String.add("No"); 
                    }
                }
                
            }
            listEntrys_String.add(tempReservationCheckList_String);
        }
        
        
        String[][] tempArrayForValues = new String[listEntrys_String.size()][];
        for (int i = 0; i < listEntrys_String.size(); i++) 
        {
            ArrayList<String> row = listEntrys_String.get(i);
            tempArrayForValues[i] = row.toArray(new String[row.size()]);
        }
        
        mainTable_JTable = new JTable(tempArrayForValues,Dates_String.toArray());
        this.guiPane.add(mainTable_JTable);
        
//        for(JLabel tempJLabel:Dates_JLabels)
//        {
//            panelForScrollablePage_JPanel.add(tempJLabel);
//        }
//        
//        for(ArrayList<JLabel> tempJLabelList:listEntrys)
//        {
//            for(JLabel tempJLabel:tempJLabelList)
//            {
//                 panelForScrollablePage_JPanel.add(tempJLabel);
//            }
//        }
        
        
        
        
        
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
    
    public JButton NewBackButton()
    {//Επιστρέφει ένα κουμπί το οποίο γυρνάει πίσω στο αρχικο μενού
        JButton back_JButton = new JButton("Back");//δημιουργώ το κουμπλι με κείμενο πάνω στο κουμπί Back
        back_JButton.addActionListener(//βάζω ένα ActionListener ώστε να καλώ την συνάρτηση MainMenu() όταν πατιέται το κουμπί
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        MainMenu(currentName);
                    }
                }
        );
        return back_JButton;
    }
}
