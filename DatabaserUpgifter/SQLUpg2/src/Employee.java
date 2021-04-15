import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends JFrame{


    private JPanel employeePanel;
    private JTextField searchBar;
    private JButton bookSearchButton, searchMagazineButton, searchCustomerButton, loggout;
    private JTextArea resultArea;

    public Employee(){

        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        this.add(employeePanel);
        this.setTitle("Anställda Sida: ");
        this.setSize(800,800);

        ActionListener searchBook = e->{
            try {
                Connection con = Loggin.getCon();
                PreparedStatement p = con.prepareStatement("SELECT*FROM Böcker where Titel = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                while(set.next()){
                    resultArea.setText("Titel:   " + set.getString(2) + "\nFörfattare:   " + set.getString(3) + "\nSidNr:   "
                            + set.getInt(4) + "\nUtlånad:   " + set.getInt(6));
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
                    resultArea.setText("Titel:   " + set.getString(2) + "\nUtgivnings Datum:   " + set.getDate(3) + "\nLager Plats:   " + set.getString(4));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener searchCustomer = e->{
            try {
                Connection con = Loggin.getCon();
                PreparedStatement p = con.prepareStatement("SELECT*FROM Låntagare where Namn = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                int lånID = 0;
                String customerInfo = " ";
                while(set.next()){

                    customerInfo = "Namn:   " + set.getString(2) + "\nAdress:   " + set.getString(3) + "\nTelefon Nr:   "
                            + set.getInt(4) + "\n Kundens Lånade Böcker: ";
                    lånID = set.getInt(1);
                }
                PreparedStatement c = con.prepareStatement("SELECT*FROM Lånadeböcker JOIN Böcker ON Lånadeböcker.BokID=Böcker.BokID WHERE Lånadeböcker.lånekortID=?");
                c.setInt(1,lånID);
                ResultSet set1 = c.executeQuery();
                while(set1.next()){

                  customerInfo += "\n " + set1.getString(5);
                }
                resultArea.setText(customerInfo);
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


        searchBar = new JTextField("Search Field",20);
        searchBar.setBounds(300,270,450,60);
        searchBar.setBackground(Color.cyan);

        resultArea = new JTextArea();
        resultArea.setBounds(300,10,450,250);
        resultArea.setBackground(Color.pink);

        bookSearchButton = new JButton("Search Book");
        bookSearchButton.setBounds(30,350,125,35);
        bookSearchButton.addActionListener(searchBook);

        searchMagazineButton = new JButton("Search Magazine");
        searchMagazineButton.setBounds(155,350,125,35);
        searchMagazineButton.addActionListener(searchMagazine);

        searchCustomerButton = new JButton("Search Customer");
        searchCustomerButton.setBounds(280,350,125,35);
        searchCustomerButton.addActionListener(searchCustomer);

        loggout = new JButton("Logga ut");
        loggout.setBounds(300,400,125,35);
        loggout.addActionListener(loggaUt);


        employeePanel.add(searchBar);
        employeePanel.add(resultArea);
        employeePanel.add(bookSearchButton);
        employeePanel.add(searchMagazineButton);
        employeePanel.add(searchCustomerButton);
        employeePanel.add(loggout);

        this.add(employeePanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
