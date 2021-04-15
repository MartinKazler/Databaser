import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends JFrame {

    private JPanel adminPanel;
    private JTextField searchBar, changeSalaryBar, changeSemesterBar;
    private JButton bookSearchButton, searchMagazineButton, searchCustomerButton, searchEmployeeButton, searchBorrowedBooksButton, changeSalaryButton, changeSemesterButton, loggout;
    private JTextArea resultArea;
    private int employID = 1;


    public Admin(){

        adminPanel = new JPanel();
        adminPanel.setLayout(null);
        this.setTitle("Admin Sida: ");
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
                while(set.next()){
                    resultArea.setText("Namn:   " + set.getString(2) + "\nAdress:   " + set.getString(3) + "\nTelefon Nr:   " + set.getInt(4));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener searchEmployee = e->{
            try {
                Connection con = Loggin.getCon();
                PreparedStatement p = con.prepareStatement("SELECT*FROM Anställda where Namn = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                while(set.next()){
                    resultArea.setText("Namn:   " + set.getString(2) + "\nAdress:   " + set.getString(3) + "\nTelefon Nr:   "
                            + set.getInt(4) +"\n Månadslön:   " + set.getInt(6) + "\n Semesterdagar   " + set.getInt(7));
                    employID = set.getInt(1);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener searchBorrowedBooks = e->{
            try {
                Connection con = Loggin.getCon();
                PreparedStatement p = con.prepareStatement("SELECT*FROM LånadeBöcker where LånadeB = ?");
                p.setString(1,searchBar.getText());
                ResultSet set = p.executeQuery();
                while(set.next()){
                    resultArea.setText("Titel:   " + set.getString(2) + "\nKlassifikation:   " + set.getString(3));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener modifySalary = e -> {

            try {
                {
                    Connection con = Loggin.getCon();
                    PreparedStatement s = con.prepareStatement("UPDATE Anställda SET Månadslön = ? WHERE AnställdID = ?");
                    s.setString(1, changeSalaryBar.getText());
                    s.setInt(2, employID);
                    s.executeUpdate();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener modifySemester = e -> {
            Connection con = Loggin.getCon();
            try {
                PreparedStatement d = con.prepareStatement("UPDATE Anställda SET Semesterdagar = ? WHERE AnställdID = ?");
                d.setString(1, changeSemesterBar.getText());
                d.setInt(2, employID);
                d.executeUpdate();
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
        searchBar.setBounds(300,270,225,60);
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

        searchEmployeeButton = new JButton("Search Employee");
        searchEmployeeButton.setBounds(405, 350, 125, 35);
        searchEmployeeButton.addActionListener(searchEmployee);

        searchBorrowedBooksButton = new JButton("Search Borrowed books");
        searchBorrowedBooksButton.setBounds(530, 350, 125, 35);
        searchBorrowedBooksButton.addActionListener(searchBorrowedBooks);

        changeSalaryBar = new JTextField("Skriv in ny lön");
        changeSalaryBar.setBounds(20,20,125,35);

        changeSemesterBar = new JTextField("Skriv in ny semester dagar");
        changeSemesterBar.setBounds(20,60, 125,35);

        changeSalaryButton = new JButton("Ändra lön");
        changeSalaryButton.setBounds(150,20,125, 35);
        changeSalaryButton.addActionListener(modifySalary);

        changeSemesterButton = new JButton("Ändra Semester");
        changeSemesterButton.setBounds(150,60,125,35);
        changeSemesterButton.addActionListener(modifySemester);

        loggout = new JButton("Logga ut");
        loggout.setBounds(300,400,125,35);
        loggout.addActionListener(loggaUt);

        adminPanel.add(searchBar);
        adminPanel.add(changeSalaryBar);
        adminPanel.add(changeSemesterBar);

        adminPanel.add(resultArea);
        adminPanel.add(changeSemesterButton);
        adminPanel.add(changeSalaryButton);
        adminPanel.add(bookSearchButton);
        adminPanel.add(searchMagazineButton);
        adminPanel.add(searchCustomerButton);
        adminPanel.add(searchEmployeeButton);
        adminPanel.add(searchBorrowedBooksButton);
        adminPanel.add(loggout);

        this.add(adminPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}
