
import javax.swing.*;

public class Gui extends JFrame {

    private JPanel mainPanel;

    private JTextField userName;
    private JTextField pass;

    private JButton loginButton;

    private JRadioButton admin;
    private JRadioButton employee;
    private JRadioButton customer;


    public Gui(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        this.setTitle("Bibilioteket");
        this.setSize(1000, 900);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        userName = new JTextField("",25);
        userName.setBounds(30,260,200,30);

        pass = new JTextField("", 25);
        pass.setBounds(30,300,200,30);

        loginButton = new JButton("Login");
        loginButton.setBounds(30,350,130,35);

        loginButton.addActionListener(e -> {

            Loggin.con(userName.getText(),pass.getText());

            if (admin.isSelected()){
                if(Loggin.getLoginCheck()==1){
                    Admin adminGuiStart = new Admin();
                }
            }
            if(customer.isSelected()){
                if(Loggin.getLoginCheck()==1){
                    Customer customerGuiStart = new Customer();
                }
            }
            if(employee.isSelected()){
                if(Loggin.getLoginCheck()==1){
                    Employee employeeGuiStart = new Employee();
                }
            }
        });

        admin = new JRadioButton("Admin");
        admin.setBounds(155,360,100,17);
        admin.addActionListener(e -> {
            employee.setSelected(false);
            customer.setSelected(false);
        });

        employee = new JRadioButton("Anställd");
        employee.setBounds(155,370,100,17);
        employee.addActionListener(e -> {
            admin.setSelected(false);
            customer.setSelected(false);
        });

        customer = new JRadioButton("Kund");
        customer.setBounds(155,390,100,17);
        customer.addActionListener(e -> {
            employee.setSelected(false);
            admin.setSelected(false);
        });

        mainPanel.add(userName);
        mainPanel.add(pass);
        mainPanel.add(loginButton);
        mainPanel.add(admin);
        mainPanel.add(employee);
        mainPanel.add(customer);

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}


