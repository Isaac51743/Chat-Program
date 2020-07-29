import javax.swing.*;

public class myMain extends JFrame{
    static Connect conn =new Connect();



    public static void main(String[] args) {
        Client b =new Client("ZHAOYUEHANG");
        String a="haha";
        b.go("zhaoyuehang",conn);
        Userlist c=new Userlist(conn,a);

        Login loginWindow = new Login();


    }
}


