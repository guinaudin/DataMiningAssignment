package fr.ece.tweetstats.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MainView extends JFrame implements ActionListener, ListSelectionListener {
    private JTextField addItemTextField;
    private JTextField yearTextField;
    private JTextField monthTextField;
    private JTextField dayTextField;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton fetchButton;
    private DefaultListModel itemList;
    private JList elementJList;
    private int count;
    private int loopVar;
    private BarChart barChart;
    private JPanel graphPanel;
    
    public MainView() {
        super("Tweetstats");
        
        this.buildFrame();
        this.setVisible(true);
    }
    
    private void buildFrame() {
        //On donne une taille à notre fenetre
        this.setSize(1240,778);
        //On centre la fenêtre sur l'écran
        this.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fenêtre
        this.setResizable(false); 
        //On dit à l'application de se fermer lors du clic sur la croix
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //######################## MainPanel ########################
        JPanel mainViewPanel = new JPanel(new BorderLayout());
        
        //######################## AsidePanel ########################
        JPanel asidePanel = new JPanel();
        asidePanel.setLayout(new BoxLayout(asidePanel, BoxLayout.Y_AXIS));
        
        //######################## FlowFetchPanel ########################
        JPanel flowFetchPanel = new JPanel();
        flowFetchPanel.setLayout(new FlowLayout());
        Border fetchFrame = BorderFactory.createTitledBorder("Fetch area");
        flowFetchPanel.setBorder(fetchFrame);
        flowFetchPanel.setBackground(Color.WHITE);
        
        //######################## FetchPanel ########################
        JPanel fetchPanel = new JPanel();
        fetchPanel.setLayout(new BoxLayout(fetchPanel, BoxLayout.Y_AXIS));
        
        fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(this);
        this.setMySize(fetchButton, 100, 40);
        fetchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        fetchPanel.add(fetchButton);
        
        JPanel yearPanel = new JPanel();
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.X_AXIS));
        
        JLabel yearLabel = new JLabel("Year ");
        yearPanel.add(yearLabel);
        
        yearTextField = new JTextField();
        //on lui donne une dimension
        this.setMySize(yearTextField, 200, 40);
        
        //on peut rentrer jusqu'a 15 caracteres
        yearTextField.setColumns(15);
        //on set une police
        Font police = new Font("Arial", Font.BOLD, 14);
        yearTextField.setFont(police);
        //on ajoute les differents elements
        yearPanel.add(yearTextField);
        fetchPanel.add(yearPanel);
        
        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.X_AXIS));
        
        JLabel monthLanel = new JLabel("Month");
        monthPanel.add(monthLanel);
                    
        monthTextField = new JTextField();
        //on lui donne une dimension
        this.setMySize(monthTextField, 200, 40);
        //on peut rentrer jusqu'a 15 caracteres
        monthTextField.setColumns(15);
        //on set une police
        monthTextField.setFont(police);
        //on ajoute les differents elements
        monthPanel.add(monthTextField);
        fetchPanel.add(monthPanel);
        
        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
        
        JLabel dayLanel = new JLabel("Day  ");
        dayPanel.add(dayLanel);
        
        dayTextField = new JTextField();
        //on lui donne une dimension
        this.setMySize(dayTextField, 200, 40);
        //on peut rentrer jusqu'a 15 caracteres
        dayTextField.setColumns(15);
        //on set une police
        dayTextField.setFont(police);
        //on ajoute les differents elements
        dayPanel.add(dayTextField);
        fetchPanel.add(dayPanel);
        
        flowFetchPanel.add(fetchPanel);
        asidePanel.add(flowFetchPanel);
        
        //######################## FlowJListPanel ########################
        JPanel flowJListPanel = new JPanel();
        flowFetchPanel.setLayout(new FlowLayout());
        Border jListFrame = BorderFactory.createTitledBorder("Items area");
        flowJListPanel.setBorder(jListFrame);
        flowJListPanel.setBackground(Color.WHITE);
        
        //######################## JListPanel ########################
        JPanel JListPanel = new JPanel();
        JListPanel.setLayout(new BoxLayout(JListPanel, BoxLayout.Y_AXIS));
        
        itemList = new DefaultListModel();

        itemList.addElement("putain");
        itemList.addElement("fuck");
        itemList.addElement("insupportable");
        itemList.addElement("intolérable");
        itemList.addElement("énervant");
        itemList.addElement("merde");
        
        elementJList = new JList(itemList);
        elementJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        elementJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        elementJList.setSelectedIndex(0);
        elementJList.addListSelectionListener(this);

        JScrollPane listScroller = new JScrollPane(elementJList);
        listScroller.setPreferredSize(new Dimension(200, 250));
        JListPanel.add(listScroller);
        
        flowJListPanel.add(JListPanel);
        asidePanel.add(flowJListPanel);
        
        //######################## AddRemoveItemPanel ########################
        JPanel addRemoveItemsPanel = new JPanel();
        Border frame = BorderFactory.createTitledBorder("add/Remove");
        addRemoveItemsPanel.setBorder(frame);
        
        addRemoveItemsPanel.setLayout(new BoxLayout(addRemoveItemsPanel, BoxLayout.X_AXIS));
        //buttonPanel.setBackground(Color.WHITE);
        addItemButton = new JButton();
        addItemButton.addActionListener(this);
        addItemButton.setPreferredSize(new Dimension(40,40));
        removeItemButton = new JButton();
        removeItemButton.addActionListener(this);
        removeItemButton.setPreferredSize(new Dimension(40,40));
        
        try {
            removeItemButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/images/remove.png"))));
            addRemoveItemsPanel.add(removeItemButton);
            addItemButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/images/add.png"))));
            addRemoveItemsPanel.add(addItemButton);
            
            addItemTextField = new JTextField();
            //on lui donne une dimension
            this.setMySize(addItemTextField, 200, 40);
            //on peut rentrer jusqu'a 15 caracteres
            addItemTextField.setColumns(15);
            //on set une police
            addItemTextField.setFont(police);
            //on ajoute les differents elements
            addRemoveItemsPanel.add(Box.createHorizontalStrut(10));
            addRemoveItemsPanel.add(addItemTextField);
        } 
        catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        asidePanel.add(addRemoveItemsPanel);
        
        //######################## InformationPanel ########################
        JPanel informationPanel = new JPanel();
        Border informationFrame = BorderFactory.createTitledBorder("information");
        informationPanel.setBorder(informationFrame);
        
        JLabel label = new JLabel("hello");
        informationPanel.add(label);
        
        asidePanel.add(informationPanel);
        
        mainViewPanel.add(asidePanel, BorderLayout.WEST);
        
        //######################## GraphPanel ########################
        graphPanel = new JPanel(new FlowLayout());
        graphPanel.setBackground(Color.WHITE);
        barChart = new BarChart(itemList);
        graphPanel.add(barChart.getChartPanel());
        
        mainViewPanel.add(graphPanel, BorderLayout.CENTER);
        
        this.setContentPane(mainViewPanel);
    }
    
    private void setMySize(JComponent component, int x, int y) {
        component.setMinimumSize(new Dimension(x,y));
        component.setMaximumSize(new Dimension(x,y));
        component.setPreferredSize(new Dimension(x,y));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //on recupere la source du clic
        Object source = (JButton)(e.getSource());
        
        count = 0;
        loopVar = 0;
        
        if(source == addItemButton) {
            while(loopVar < itemList.size()) {
                if(addItemTextField.getText().equals(itemList.get(loopVar).toString())) {
                    System.out.println(itemList.get(loopVar).toString());
                    count++;
                }
                loopVar++;
            }
            if(count == 0 && !addItemTextField.getText().equals(""))
                itemList.addElement(addItemTextField.getText());
            addItemTextField.setText("");
        }
        else if(source == removeItemButton) {
            if(elementJList.getSelectedIndices().length > 0) {
                int[] selectedIndices = elementJList.getSelectedIndices();
                for (int i = selectedIndices.length-1; i >=0; i--) {
                    itemList.removeElementAt(selectedIndices[i]);
                } 
            } 
        }
        else if(source == fetchButton) {
            barChart = new BarChart(itemList);
            graphPanel.removeAll();
            graphPanel.add(barChart.getChartPanel());
            graphPanel.validate();
            graphPanel.repaint();
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
