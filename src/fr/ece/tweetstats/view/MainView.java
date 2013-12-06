package fr.ece.tweetstats.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MainView extends JFrame implements ActionListener, ListSelectionListener {
    private JTextField addItemTextField;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton fetchButton;
    private DefaultListModel itemList;
    private JList elementJList;
    private int count;
    private int loopVar;
    
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
        
        JPanel mainViewPanel = new JPanel(new BorderLayout());
        
        JPanel flowFetchPanel = new JPanel(new FlowLayout());
        JPanel fetchPanel = new JPanel();
        fetchPanel.setLayout(new BoxLayout(fetchPanel, BoxLayout.Y_AXIS));
        fetchPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        fetchPanel.setBackground(Color.WHITE);
        
        fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(this);
        fetchButton.setPreferredSize(new Dimension(80,40));
        fetchPanel.add(fetchButton);
        
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
        fetchPanel.add(listScroller);
        //fetchPanel.add(Box.createVerticalStrut(10));
        
        JPanel buttonListPanel = new JPanel();
        buttonListPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonListPanel.setLayout(new BoxLayout(buttonListPanel, BoxLayout.X_AXIS));
        //buttonPanel.setBackground(Color.WHITE);
        addItemButton = new JButton();
        addItemButton.addActionListener(this);
        //addItemButton.setMaximumSize(new Dimension(40,30));
        //addItemButton.setMinimumSize(new Dimension(30,30));
        addItemButton.setPreferredSize(new Dimension(40,40));
        removeItemButton = new JButton();
        removeItemButton.addActionListener(this);
        //removeItemButton.setMaximumSize(new Dimension(30,30));
        //removeItemButton.setMinimumSize(new Dimension(30,30));
        removeItemButton.setPreferredSize(new Dimension(40,40));
        
        try {
            removeItemButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/images/remove.png"))));
            buttonListPanel.add(removeItemButton);
            addItemButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/images/add.png"))));
            buttonListPanel.add(addItemButton);
            
            addItemTextField = new JTextField();
            //on lui donne une dimension
            addItemTextField.setPreferredSize(new Dimension(160,40));
            //on peut rentrer jusqu'a 15 caracteres
            addItemTextField.setColumns(15);
            //on set une police
            Font police = new Font("Arial", Font.BOLD, 14);
            addItemTextField.setFont(police);
            //on ajoute les differents elements
            buttonListPanel.add(Box.createHorizontalStrut(10));
            buttonListPanel.add(addItemTextField);
            
            fetchPanel.add(buttonListPanel);
        } 
        catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //JPanel informationPanel = new JPanel();
        flowFetchPanel.add(fetchPanel);
        mainViewPanel.add(flowFetchPanel, BorderLayout.WEST);
        
        //JPanel graphPanel = new JPanel(new BorderLayout());
        
        this.setContentPane(mainViewPanel);
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
            /*int selected[] = elementJList.getSelectedIndices();

            for (int i = 0; i < selected.length; i++) {
                System.out.println(selected[i]);
                itemList.removeElementAt(selected[i]);
            }*/
            
            if(elementJList.getSelectedIndices().length > 0) {
                int[] selectedIndices = elementJList.getSelectedIndices();
                for (int i = selectedIndices.length-1; i >=0; i--) {
                    itemList.removeElementAt(selectedIndices[i]);
                } 
            } 
        }
        else if(source == fetchButton) {
            
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
