package fr.ece.tweetstats;

import javax.swing.SwingUtilities;
import fr.ece.tweetstats.view.MainView;

public class DataMiningAssignment {

    public static void main(String[] args) {
        //creation d'une tache(Runnable) contenant le code d�di� � la cr�ation de la fen�tre et on l'ajoute dans le thread de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {   
                MainView mainView = new MainView();
            }
        });
    }
}
