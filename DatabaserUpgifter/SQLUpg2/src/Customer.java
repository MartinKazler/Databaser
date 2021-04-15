import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends JFrame {

    private JPanel customerPanel;
    private JTextField searchBar, borrowSelectBar;
    private JButton bookSearchButton, searchMagazineButton, borrowBookButton, loggout;
    private JTextArea resultArea;
    private JLabel bokidruta;
    public Customer(){

        ActionListener burrowBookButton = e -> {

            try {
                {
                    Connection con = Loggin.getCon();
                    PreparedStatement p = con.prepareStatement("INSERT INTO Lånadeböcker(BokID, LånekortID) VALUES(?,?)");
                    p.setInt(1, Integer.parseInt(borrowSelectBar.getText()));
                    p.setInt(2, Loggin.getLåntagarID());
                    p.executeUpdate();
                }
                {
                    Connection con = Loggin.getCon();
                    PreparedStatement p = con.prepareStatement("UPDATE Böcker SET Lånstatus = ? WHERE BokID = ?");
                    p.setInt(2, Integer.parseInt(borrowSelectBar.getText()));
                    p.setInt(1, Loggin.UTLÅNAD);
                    p.executeUpdate();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener searchBook = e->{
            try {
                PreparedStatement p = Loggin.getCon().prepareStatement("SELECT*FROM Böcker where Titel = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                while(set.next()){
                    resultArea.setText("Titel:   " + set.getString(2) + "\nFörfattare:   " + set.getString(3) + "\nSidNr:   "
                            + set.getInt(4) + "\nUtlånings status:   " + (set.getInt(6) == Loggin.UTLÅNAD  ? "Utlånad" : "Tillgänglig") + "\n BokID:  " + set.getInt(1));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener searchMagazine = e->{
            try {
                Connection con = Loggin.getCon();
                PreparedStatement p = con.prepareStatement("SELECT*FROM Tidskrifter where Titel = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                while(set.next()){
                    resultArea.setText("Titel:   " + set.getString(2) + "\nUtgivnings Datum:   " + set.getDate(3) + "\n\nLager Plats:   " + set.getString(4));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener loggaUt = e ->{
            try{
                Loggin.getCon().close();
                this.dispose();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        };


        customerPanel = new JPanel();
        customerPanel.setLayout(null);
        this.setTitle("Customer Page:");
        this.setSize(800,800);

        searchBar = new JTextField("Search Field",20);
        searchBar.setBounds(500,300,280,50);
        searchBar.setBackground(Color.cyan);

        borrowSelectBar = new JTextField("Mata in bokID",20);
        borrowSelectBar.setBounds(10,315,125,35);
        borrowSelectBar.setBackground(Color.GREEN);

        resultArea = new JTextArea();
        resultArea.setBounds(300,10,450,250);
        resultArea.setBackground(Color.pink);

        bookSearchButton = new JButton("Search Book");
        bookSearchButton.setBounds(500,350,140,35);
        bookSearchButton.addActionListener(searchBook);

        searchMagazineButton = new JButton("Search Magazine");
        searchMagazineButton.setBounds(640,350,140,35);
        searchMagazineButton.addActionListener(searchMagazine);

        borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setBounds(10,350,140,35);
        borrowBookButton.addActionListener(burrowBookButton);

        loggout = new JButton("Logga ut");
        loggout.setBounds(300,400,125,35);
        loggout.addActionListener(loggaUt);

        bokidruta = new JLabel("<html>låna Bok med bokens ID nummer");
        bokidruta.setBounds(10,270,125,30);
        bokidruta.setBackground(Color.black);

        customerPanel.add(bokidruta);
        customerPanel.add(loggout);
        customerPanel.add(resultArea);
        customerPanel.add(bookSearchButton);
        customerPanel.add(searchMagazineButton);
        customerPanel.add(borrowBookButton);
        customerPanel.add(searchBar);
        customerPanel.add(borrowSelectBar);

        this.add(customerPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
