import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

/**
 * Created by Edward on 2017/3/19.
 * Modified by Xichao Geng on 2017/3/21.
 */
public class Signup extends JFrame implements ActionListener {
    JLabel username = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JLabel phone = new JLabel("Phone:");
    JLabel m_f = new JLabel("Gender:");
    JLabel e_mail = new JLabel("Email:");


    //窗口宽度  
    final int WIDTH = 300;
    //窗口高度  
    final int HEIGHT = 280;

    //创建确定按钮  
    JButton btnsure = new JButton("确定");
    //创建清除按钮  
    JButton btnClear = new JButton("清空");
    //创建男女生按钮
    JRadioButton manRadio = new JRadioButton("男", false);
    JRadioButton womanRadio = new JRadioButton("女", false);


    //创建文本输入框, 参数分别为行数和列数  
    JTextField usernamef = new JTextField();
    JTextField passwordf = new JTextField();
    JTextField phonef = new JTextField();

    JTextField m_ff = new JTextField();
    JTextField e_mailf = new JTextField();

    Login openWindow;


    public Signup(Login openWindow) {

        this.openWindow = openWindow;

        //标题  
        setTitle("注册");
        //大小  
        setSize(WIDTH, HEIGHT);
        //不可缩放  
        setResizable(false);
        //设置布局:不适用默认布局，完全自定义  
        setLayout(null);
        //设置按钮大小和位置  
        btnsure.setBounds(20, 180, 80, 30);
        btnClear.setBounds(110, 180, 80, 30);

        manRadio.setBounds(100, 110, 50, 25);
        womanRadio.setBounds(150, 110, 50, 25);



        //设置标签大小和位置  
        username.setBounds(20,20,80,25);
        password.setBounds(20,50,80,25);
        phone.setBounds(20,80,80,25);
        m_f.setBounds(20,110,80,25);
        e_mail.setBounds(20,140,80,25);

        //设置按钮文本的字体  
        btnsure.setFont(new Font("宋体", Font.BOLD, 18));
        btnClear.setFont(new Font("宋体", Font.BOLD, 18));


        //添加按钮  
        this.add(btnsure);
        this.add(manRadio);
        this.add(womanRadio);
        this.add(btnClear);


        //添加标签  
        this.add(username);
        this.add(password);
        this.add(phone);
        this.add(m_f);
        this.add(e_mail);
        //设置文本输入框大小和位置  
        usernamef.setBounds(100, 20, 150, 25);
        passwordf.setBounds(100, 50, 150, 25);
        phonef.setBounds(100, 80, 150, 25);
        m_ff.setBounds(200, 110, 80, 25);
        e_mailf.setBounds(100, 140, 150, 25);
        //设置文本输入框字体  
        usernamef.setFont(new Font("楷体", Font.BOLD, 16));
        passwordf.setFont(new Font("楷体", Font.BOLD, 16));
        phonef.setFont(new Font("楷体", Font.BOLD, 16));
        m_ff.setFont(new Font("楷体", Font.BOLD, 16));
        e_mailf.setFont(new Font("楷体", Font.BOLD, 16));

        //添加文本输入框  
        this.add(usernamef);
        this.add(passwordf);
        this.add(phonef);
        this.add(e_mailf);


        btnsure.addActionListener(this);
        btnClear.addActionListener(this);
        manRadio.addActionListener(this);
        womanRadio.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnsure) {
            btnsure.setEnabled(false);

            try {
                Socket s = new Socket("127.0.0.1", 9090);

                PrintWriter pw = new PrintWriter(s.getOutputStream(),true);

                String cmd = "reg";
                cmd += "@#@#";
                cmd += usernamef.getText();
                cmd += "@#@#";
                cmd += passwordf.getText();
                cmd += "@#@#";
                cmd += phonef.getText();
                cmd += "@#@#";
                cmd += m_ff.getText();
                cmd += "@#@#";
                cmd += e_mailf.getText();


                pw.println(cmd);
                pw.flush();

                InputStream in = s.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader inputbuf = new BufferedReader(input);
                String r = inputbuf.readLine();

                if(r.startsWith("register success")) {
                    JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.PLAIN_MESSAGE);
                    this.openWindow.setSignupWindow(null);
                    this.dispose();

                }else{
                    JOptionPane.showMessageDialog(null, r + "，请重新尝试！", "注册失败", JOptionPane.PLAIN_MESSAGE);
                    btnsure.setEnabled(true);
                }

                in.close();
                s.close();

            } catch (Exception aException) {
                aException.printStackTrace();
            }
        }else if(e.getSource() == btnClear)
        {
            usernamef.setText("");
            passwordf.setText("");
            phonef.setText("");
            m_ff.setText("");
            e_mailf.setText("");
            manRadio.setSelected(false);
            womanRadio.setSelected(false);

        }else if(e.getSource() == manRadio)
        {
            m_ff.setText("male");
        }else if(e.getSource() == womanRadio)
        {
            m_ff.setText("female");
        }
    }
}
