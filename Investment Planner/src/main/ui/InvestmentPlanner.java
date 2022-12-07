package ui;

import model.EventLog;
import model.Event;
import model.Investment;
import model.InvestmentList;
import persistence.Reader;
import persistence.Writer;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/*REFERENCES: ---------------------------------------------------------------------------------------------------
Phase 2:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
Phase 3:
https://stackoverflow.com/questions/33553881/java-vertically-align-buttons
https://www.youtube.com/watch?v=1JjTAxbsDqs
https://stackoverflow.com/questions/50545296/java-gui-shows-blank-until-resize
https://stackoverflow.com/questions/8176965/how-to-add-element-to-existing-jlist/19511390
https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
https://stackoverflow.com/questions/7080205/popup-message-boxes
https://stackoverflow.com/questions/15258467/java-how-can-i-popup-a-dialog-box-as-only-an-image
https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
----------------------------------------------------------------------------------------------------------------*/


// Contains the Investment planner application data and behaviour
public class InvestmentPlanner extends JFrame implements ActionListener {
    public static final int FONT_SIZE = 25;
    private static final String JSON_STORE = "./data/investments.json";

    private InvestmentList list;
    private Writer jsonWriter;
    private Reader jsonReader;
    private JList<Investment> items;
    private JTextField label;
    private JTextField amount;
    private DefaultListModel model;
    private ButtonGroup options;
    private JPanel panel;


    // EFFECTS: Initializes all fields, renders JFrame components, constructs GUI for Investment Planner
    public InvestmentPlanner() {

        super("Investment Planner");
        initializeSplash();
        list = new InvestmentList(0);
        jsonWriter = new Writer(JSON_STORE);
        jsonReader = new Reader(JSON_STORE);
        initializeFrame();
        initializePanel();
        createInvestmentLabel();
        initializeLabelText();
        createAmountLabel();
        initializeAmountText();
        initializeRadioButtons();
        initializeJButtons();
        add(panel);
        initializeJList();
        add(items);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        printLogWhenClosed();

    }


    //MODIFIES: this
    //EFFECTS: Creates and renders a window of a splash screen
    private void initializeSplash() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("./data/welcome-splash.jpg"), SwingConstants.CENTER));
        window.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2,
                dim.height / 2 - window.getSize().height / 2);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.dispose();
    }


    //MODIFIES: this
    //EFFECTS: Creates a label with string "Amount", and adds it to panel
    private void createAmountLabel() {
        JLabel a = new JLabel("Amount");
        a.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        panel.add(a);
    }


    //MODIFIES: this
    //EFFECTS: Creates a label with string "Investment name: ", and adds it to panel
    private void createInvestmentLabel() {
        JLabel n = new JLabel("Investment Name: ");
        n.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        panel.add(n);
    }

    //MODIFIES: this
    //EFFECTS:  Instantiates a new JList object component to represent the list of Investments
    private void initializeJList() {
        this.model = new DefaultListModel();
        items = new JList<Investment>(model);
        items.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        items.setBackground(Color.ORANGE);
        TitledBorder t = new TitledBorder("Investments");
        t.setTitleFont(new Font("Aerial", Font.BOLD, FONT_SIZE));
        items.setBorder(t);

    }

    //MODIFIES: this
    //EFFECTS: Calls all the methods responsible for creating the Button objects for JFrame and adding them to panel
    private void initializeJButtons() {
        createAddButton();
        createUpdateButton();
        createTotalButton();
        createHideButton();
        createClearButton();
        createSaveButton();
        createLoadButton();
        createDisplayButton();
        createQuitButton();
    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Quit", sets an Action Command for it, adds it to panel
    private void createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        quit.setActionCommand("quit");
        quit.addActionListener(this);
        panel.add(quit);
    }


    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Load", sets an Action Command for it, adds it to panel
    private void createLoadButton() {
        JButton load = new JButton("Load");
        load.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        load.setActionCommand("load");
        load.addActionListener(this);
        panel.add(load);

    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Display Loaded List", sets an Action Command for it, adds it to panel
    private void createDisplayButton() {
        JButton display = new JButton("Display Loaded list");
        display.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        display.setActionCommand("display");
        display.addActionListener(this);
        panel.add(display);

    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Save", sets an Action Command for it, adds it to panel
    private void createSaveButton() {
        JButton save = new JButton("Save");
        save.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        save.setActionCommand("save");
        save.addActionListener(this);
        panel.add(save);
    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Clear", sets an Action Command for it, adds it to panel
    private void createClearButton() {
        JButton clear = new JButton("Reset");
        clear.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        clear.setActionCommand("clear");
        clear.addActionListener(this);
        panel.add(clear);
    }


    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Hide", sets an Action Command for it, adds it to panel
    private void createHideButton() {

        JButton hide = new JButton("Hide");
        hide.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        hide.setActionCommand("hide");
        hide.addActionListener(this);
        panel.add(hide);

    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Total Invested", sets an Action Command for it, adds it to panel
    private void createTotalButton() {
        JButton total = new JButton("Total Invested");
        total.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        total.setActionCommand("total");
        total.addActionListener(this);
        panel.add(total);
    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Update", sets an Action Command for it, adds it to panel
    private void createUpdateButton() {
        JButton update = new JButton("Update");
        update.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        update.setActionCommand("update");
        update.addActionListener(this);
        panel.add(update);

    }

    //MODIFIES: this
    //EFFECTS: Creates JButton with label "Add Investment", sets an Action Command for it, adds it to panel
    private void createAddButton() {
        JButton create = new JButton("Add Investment");
        create.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        create.setActionCommand("add");
        create.addActionListener(this);
        panel.add(create);
    }


    //MODIFIES: this
    //EFFECTS: Creates three radio buttons to represent the investment types, sets Action Commands, adds them to panel
    private void initializeRadioButtons() {
        JRadioButton cr = new JRadioButton("Crypto");
        cr.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        cr.setActionCommand("Crypto");
        panel.add(cr);
        JRadioButton re = new JRadioButton("Real Estate");
        re.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        re.setActionCommand("Real Estate");
        panel.add(re);
        JRadioButton st = new JRadioButton("Stocks");
        st.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        st.setActionCommand("Stocks");
        panel.add(st);
        options.add(cr);
        options.add(re);
        options.add(st);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the amount field to create a new text box for user input, and adds it to panel
    private void initializeAmountText() {
        this.amount = new JTextField();
        amount.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        panel.add(amount);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the label field to create a new text box for user input, and adds it to panel
    private void initializeLabelText() {
        this.label = new JTextField();
        label.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        panel.add(label);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the panel field (type JPanel), initializes the ButtonGroup panel
    private void initializePanel() {
        this.panel = new JPanel((new GridLayout(16, 1)));
        this.options = new ButtonGroup();
        TitledBorder tb = new TitledBorder("Menu");
        tb.setTitleFont(new Font("Aerial", Font.BOLD, FONT_SIZE));
        panel.setBorder(tb);
        panel.setBackground(Color.ORANGE);
    }


    //MODIFIES: this
    //EFFECTS: Specifies the details of the JFrame window appearance
    private void initializeFrame() {
        setName("Investment Planner");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridLayout(1, 1));
    }


    @Override
    //MODIFIES: this
    //EFFECTS: Calls all the methods that specify the action to be performed depending on the Action command values
    public void actionPerformed(ActionEvent e) {
        createInvestment(e);
        hideInvestment(e);
        showTotalInvestment(e);
        clearList(e);
        updateInvestment(e);
        saveInvestments(e);
        loadInvestments(e);
        displayLoadedInvestments(e);
        quit(e);
    }


    //REQUIRES: Items must be already saved and loaded
    //EFFECTS: Prints the list of loaded (previously saved) items to JList, if display button is clicked
    private void displayLoadedInvestments(ActionEvent e) {
        if (e.getActionCommand().equals("display")) {
            printSavedInvestments();
        }
    }

    //EFFECTS: Terminates the program if the quit button is clicked
    private void quit(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    //REQUIRES: Items must be added to the list and saved first
    //EFFECTS: If the Load button is clicked, Loads investments data, shows a popup confirmation message
    private void loadInvestments(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            loadTotalInvestments();
            infoBox("Loaded your investments!", "Notification");
        }
    }

    //REQUIRES: Items must be added to the list first
    //EFFECTS: If the save button is clicked, saves investments data, show a popup confirmation message
    private void saveInvestments(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            saveTotalInvestments();
            infoBox("Saved your investments!", "Notification");
        }
    }

    //REQUIRES: At least one item must be added to the list first, and it must be selected by the user
    //MODIFIES: this
    //EFFECTS: If the update button is clicked, selected investment data will be updated in the list
    private void updateInvestment(ActionEvent e) {
        if (e.getActionCommand().equals("update")) {
            list.setInvestmentData(items.getSelectedValue(),
                    label.getText(), options.getSelection().getActionCommand(),
                    Integer.parseInt(amount.getText()));
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the list of investment if  the "reset" button is clicked
    private void clearList(ActionEvent e) {
        if (e.getActionCommand().equals("clear")) {
            model.clear();
            list.emptyList();
        }

    }

    //EFFECTS: Displays a popup message with the total amount invested so far, if the "Total" button is clicked
    private void showTotalInvestment(ActionEvent e) {
        if (e.getActionCommand().equals("total")) {
            infoBox("Your total investments: $ " + list.totalInvested(), "Notification");
        }
    }

    //REQUIRES: Both text boxes must have input, and one of the JRadioButtons must be clicked
    //MODIFIES: this
    //EFFECTS: if the "Add investment" button is clicked, creates and adds a new investment using data from user input
    private void createInvestment(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            Investment inv = new Investment(label.getText(), options.getSelection().getActionCommand(),
                    (Integer.parseInt(amount.getText())));
            list.addInvestment(inv);
            model.add(0, inv);
        }
    }


    //MODIFIES: this
    //EFFECTS: Hides a selected investment from the list, if the user selects an item and clicks hide button
    private void hideInvestment(ActionEvent e) {
        if (e.getActionCommand().equals("hide")) {
            model.remove(items.getSelectedIndex());
        }
    }


    //EFFECTS: Creates a popup message box to be used in other parts of the app to notify user
    public static void infoBox(String infoMessage, String titleBar) {
        JLabel label = new JLabel(infoMessage);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        JOptionPane.showMessageDialog(null, label, "InfoBox: "
                + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    //REQUIRES: A list must contain items, and then Save button must be clicked
    //EFFECTS: Prints the items saved as JSON into the JList window
    private void printSavedInvestments() {
        for (Investment i : list.getInvestmentList()) {
            model.add(0, i);
        }
    }


    //MODIFIES: this
    //EFFECTS: loads the investment list from file
    public void loadTotalInvestments() {
        try {
            list = jsonReader.read();

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: Saves the investment list to file
    public void saveTotalInvestments() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved your investment list to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }

    }

    //EFFECTS: Prints out saved log of events to the console
    private void printLog(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString() + "\n\n");
        }

    }

    //EFFECTS: Prints log when application window is closed, then terminates program
    private void printLogWhenClosed() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }
}

/*REFERENCES: ---------------------------------------------------------------------------------------------------
Phase 2:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
Phase 3:
https://stackoverflow.com/questions/33553881/java-vertically-align-buttons
https://www.youtube.com/watch?v=1JjTAxbsDqs
https://stackoverflow.com/questions/50545296/java-gui-shows-blank-until-resize
https://stackoverflow.com/questions/8176965/how-to-add-element-to-existing-jlist/19511390
https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
https://stackoverflow.com/questions/7080205/popup-message-boxes
https://stackoverflow.com/questions/15258467/java-how-can-i-popup-a-dialog-box-as-only-an-image
https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
----------------------------------------------------------------------------------------------------------------*/


