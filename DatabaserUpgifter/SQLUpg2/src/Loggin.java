import java.sql.*;

public class Loggin {
    public static Connection con;
    public static int loginCheck = 0;
    public static boolean Utlånad = false;
    public static final int UTLÅNAD = 0;
    public static final int TILLGÄNGLIG = 1;


    public static Connection getCon() {
        return con;
    }


    public static String userName;


    public static Connection con(String userName, String password) {
        Loggin.userName = userName;

        try {
            System.out.println(" Successfully logged in");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", userName, password);
            loginCheck = 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int getLoginCheck(){
        return loginCheck;
    }

    public static int getLåntagarID(){
        try {

            PreparedStatement p = con.prepareStatement("SELECT LånekortID FROM Låntagare WHERE Namn = ?");
            p.setString(1,userName);
            ResultSet set = p.executeQuery();
            if (set.next()){
                return set.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

}
