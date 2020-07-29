import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Edward on 2017/3/4.
 */
public class Login implements ActionListener{

    private JFrame loginframe;
    private JButton loginButton;
    private JButton signinButton;
    private JTextField userText;
    private JPasswordField passwordText;
    private Signup signupWindow = null;
    Socket s = null;
    OutputStream out = null;
    Connect conn = null;
    Userlist userListWindow = null;

    Dictionary<String,String> onlineUser = new Hashtable<String,String>();


    public Login()
    {

        loginframe = new JFrame("登陆");
        loginframe.setSize(350, 200);
        loginframe.setResizable(false);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginpanel = new JPanel();
        loginframe.add(loginpanel);
        placeComponents(loginpanel);

        loginframe.setVisible(true);
    }

    private void placeComponents(JPanel loginpanel) {

        loginpanel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("用户名:");

        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        loginpanel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        loginpanel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        loginpanel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        loginpanel.add(passwordText);

        // 创建登录按钮
        loginButton = new JButton("登陆");

        signinButton = new JButton("注册");

        loginButton.addActionListener(this);

        signinButton.addActionListener(this);

        loginButton.setBounds(10, 80, 80, 25);
        signinButton.setBounds(100, 80, 80, 25);

        loginpanel.add(loginButton);
        loginpanel.add(signinButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signinButton) {
            if (signupWindow == null) {
                signupWindow = new Signup(this);
            }else{
                signupWindow.setVisible(true);
            }
        }
        else if(e.getSource() == loginButton)
        {
            doLogin( userText.getText(), String.valueOf(passwordText.getPassword()) );
        }
    }

    public void setSignupWindow(Signup signupWindow)
    {
        this.signupWindow = signupWindow;
    }

    Runnable incomingReader = new Runnable() {
        @Override
        public void run() {
            String msg;
            while(!(msg = conn.rec()).equals(""))
            {
//                System.out.println(msg);
                String msgs[] = msg.split("@#@#");

                String cmd = msgs[0];

                if( cmd.equals("cmdAdd") )
                {
                    onlineUser.put(msgs[2], "Online");
                    userListWindow.updateUserList(onlineUser);
                }else if( cmd.equals("cmdRed") )
                {
                    onlineUser.put(msgs[2], "Offline");
                    userListWindow.updateUserList(onlineUser);
                }else if( cmd.equals("msg") )
                {
                    System.out.println("RECEIVE MSG:" + msg);
                    userListWindow.receiveMsg(msgs[1], msgs[2]);
                }

            }
        }
    };



    private void doLogin(String username, String password)
    {
        this.conn = new Connect();

        String cmd = "login";
        cmd += "@#@#";
        cmd += username;
        cmd += "@#@#";
        cmd += password;
        cmd += "@#@#";


        this.conn.send(cmd);

        String r = this.conn.rec();
        if(true)//r.startsWith("login success"))
        {
            loginframe.dispose();
            userListWindow = new Userlist(conn, username);

            Thread readThread = new Thread(incomingReader);
            readThread.start();


        }
        else{
            JOptionPane.showMessageDialog(null, r + "，请重新尝试！", "登陆失败", JOptionPane.PLAIN_MESSAGE);
        }
    }



}







