import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edward on 2017/3/4.
 */
public class ClientFrame extends JFrame {
    JPanel panel = new JPanel();
    Date now = new Date();
    //时间显示格式  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    //窗口宽度  
    final int WIDTH = 650;
    //窗口高度  
    final int HEIGHT = 700;
    JWindow w;



    private static final long serialVersionUID = 1L;
    GridLayout   gridLayout1   =   new   GridLayout(2,7);
    JButton[]   ico=new   JButton[14]; /*放表情*/
    int  i;
    String[] intro = {"","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","",};/*图片描述*/



    //创建发送按钮  
    JButton btnSend = new JButton("发送");
    //创建清除按钮  
    JButton btnClear = new JButton("清屏");
    //创建退出按钮  
    JButton btnExit = new JButton("退出");
    //文件和图片button
    JButton btnfile = new JButton("文件");
    JButton btnpic = new JButton("图片");
    JButton btnvid = new JButton("视频");
    JButton btnface = new JButton("表情");

    //ad图片
    JLabel ad1 = new JLabel("带图片的标签组件",JLabel.CENTER);
    URL url= ClientFrame.class.getResource("123.jpg");
    Icon icon1 = new ImageIcon(url);

    JLabel ad2 = new JLabel("带图片的标签组件",JLabel.CENTER);
    Icon icon2 = new ImageIcon(url);

    //创建消息接收者标签  
    JLabel lblReceiver = new JLabel();

    //创建文本输入框, 参数分别为行数和列数  
    JTextPane jtaSay = new JTextPane();

    //创建聊天消息框  
    JTextPane jtaChat = new JTextPane();

    //创建聊天消息框的滚动窗  
    int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
    JScrollPane jspChat = new JScrollPane(jtaChat,v,h);



    //设置默认窗口属性，连接窗口组件  
    Connect con;
    String talkingWith = "";

    public ClientFrame(Connect conn)
    {

        con = conn;

    }

    public void recvMsg(String msg)
    {
        String originMsg = jtaChat.getText();

        originMsg += "\n";
        originMsg += getStringDate();
        originMsg += "\n";
        originMsg += msg;
        originMsg += "\n";

        jtaChat.setText(originMsg);
    }

    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public void go(String a, String currentUserName)
    {
        talkingWith = a;

        lblReceiver.setText("对"+a+"说");
        panel.add(jspChat);
        panel.add(new JScrollPane(jtaSay));
        //ad图片

        ad1.setIcon(icon1);

        ad2.setIcon(icon2);
        //标题  
        setTitle("聊天室");
        //大小  
        setSize(WIDTH, HEIGHT);
        //不可缩放  
        setResizable(false);
        //设置布局:不适用默认布局，完全自定义  
        setLayout(null);
        //设置按钮大小和位置  
        btnSend.setBounds(20,630,60,30);
        btnClear.setBounds(140,630,60,30);
        btnExit.setBounds(260, 630, 60,30);
        btnfile.setBounds(130, 425, 60,30);
        btnpic.setBounds(220, 425, 60,30);
        btnvid.setBounds(310, 425, 60,30);
        btnface.setBounds(130, 460, 60,30);


        //设置标签大小和位置  
        lblReceiver.setBounds(20, 420, 300, 30);
        ad1.setBounds(400,20,200,300);
        ad2.setBounds(400,340,200,300);
        //设置按钮文本的字体  
        btnSend.setFont(new Font("宋体", Font.BOLD, 12));
        btnClear.setFont(new Font("宋体",Font.BOLD,12));
        btnExit.setFont(new Font("宋体", Font.BOLD,12));
        btnfile.setFont(new Font("宋体", Font.BOLD,12));
        btnpic.setFont(new Font("宋体", Font.BOLD,12));
        btnvid.setFont(new Font("宋体", Font.BOLD,12));
        btnface.setFont(new Font("宋体", Font.BOLD,12));
        //添加按钮  
        this.add(btnSend);
        this.add(btnClear);
        this.add(btnExit);
        this.add(btnfile);
        this.add(btnpic);
        this.add(btnvid);
        this.add(btnface);

        //添加标签  
        this.add(lblReceiver);
        this.add(ad1);
        this.add(ad2);
        //设置文本输入框大小和位置  
        jtaSay.setBounds(20,500,360,120);
        //设置文本输入框字体  
        jtaSay.setFont(new Font("楷体",Font.BOLD,16));
        //添加文本输入框  
        this.add(jtaSay);


        //设置滚动条
//        jtaChat.setPreferredSize(new Dimension(360,400));
        jtaChat.setPreferredSize(new Dimension(650,700));

        jspChat.setViewportView(jtaChat);
        jspChat.setBounds(20, 20, 360, 400);



        jtaChat.setEditable(false);
        //设置聊天框字体  
        jtaChat.setFont(new Font("楷体",Font.BOLD,16));
        //设置滚动窗大小和位置  
//        jspChat.setBounds(20,20,360,400);
        //添加聊天窗口的滚动窗  
        this.add(jspChat);
        this.addti("zhaoyuehang[D:\\chatprogram\\src\\123.jpg]xiedong[D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg][D:\\chatprogram\\src\\123.jpg]");
//                this.addtext("zhaoyuehang");


//
//                //设置滚动窗的水平滚动条属性:不出现  
//                jspOnline.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//                //设置滚动窗的垂直滚动条属性:需要时自动出现  
//                jspOnline.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//                //设置当前在线列表滚动窗大小和位置  
//                jspOnline.setBounds(20,20,210,640);
//                //添加当前在线列表  
//                this.add(jspOnline);
        //添加发送按钮的响应事件  

        btnSend.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent event)
                                      {
//                                        //显示最新消息  
//                                        jtaChat.setCaretPosition(jtaChat.getDocument().getLength());
//                                        try{
//                                            //有收信人才发送  
////                                            if(Client.uidReceiver.toString().equals("") == false) {
//                                                //在聊天窗打印发送动作信息  
//                                                Date date = new Date();
//                                                String LgTime = sdf.format(date);
//                                                jtaChat.append(LgTime + "\n发往 " + Client.uidReceiver.toString() + ":\n");
//                                                //显示发送消息  
//                                                jtaChat.append(jtaSay.getText() + "\n\n");
//                                                //向服务器发送聊天信息  
////                                                con.send("Chat/" + Client.uidReceiver.toString() + "/"  + jtaSay.getText());
////                                            }
//                                        } catch(Exception e){}
//                                        finally
//                                        {
//                                            //文本输入框清除  
//                                            jtaSay.setText("");
//                                        }

                                          System.err.println("Send: "+jtaSay.getText());

                                          String cmd = "msg";
                                          cmd += "@#@#";
                                          cmd += talkingWith;
                                          cmd += "@#@#";
                                          cmd += jtaSay.getText();
                                          cmd += "@#@#";
                                          cmd += currentUserName;

                                          con.send(cmd);

                                          recvMsg("我:" + jtaSay.getText());
                                          jtaSay.setText("");



//                                        String hehe = sdf.format( now );
//                                        Vector picVector = new Vector();
//                                        for(int i = 0; i < jtaSay.getStyledDocument().getRootElements()[0].getElement(0).getElementCount(); i++){
//                                            Icon icon = StyleConstants.getIcon(jtaSay.getStyledDocument().getRootElements()[0].getElement(0).getElement(i).getAttributes());
//                                            if(icon != null){
//                                                picVector.add(hehe+a);
//                                            }
//                                        }
//
//                                        int k = 0;
//
//                                        for(int i = 0; i < jtaSay.getText().length(); i++){
//                                            if(jtaSay.getStyledDocument().getCharacterElement(i).getName().equals("icon")){
//                                                System.err.println("你在第 " + i + " 位置插入了圖片，图片命名为 :");
////                                                jtaChat.insertIcon(new ImageIcon(picVector.get(k).toString()));
//                                                System.err.println("["+picVector.get(k++).toString()+"]");
//                                            }else{
//                                                try {
//                                                    jtaChat.getStyledDocument().insertString(jtaChat.getText().length(), jtaChat.getStyledDocument().getText(i,1), null);
//                                                } catch (Exception e) {}
//                                            }
//                                        }
//                                        picVector.clear();


                                      }
                                  }
        );
        //添加清屏按钮的响应事件  
//                btnClear.addActionListener(
//                                new ActionListener()
//                                {
//                                    @Override
//                                    public void actionPerformed(ActionEvent event)
//                                    {
//                                        //聊天框清屏  
//                                        jtaChat.setText("");
//                                    }
//                                }
//                                );
        //添加退出按钮的响应事件  
//                btnExit.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent event)
//                    {
//                        try {
//                            //向服务器发送退出信息  
//                            con.send("Exit/");
//                            //退出  
//                            System.exit(0);
//                        }catch(Exception e){}
//                    }
//                }
//                );
        //添加视频按钮响应事件
        btnvid.addActionListener(new ActionListener(){
                                     @Override
                                     public void actionPerformed(ActionEvent event)
                                     {
                                         MediaTransmit T =new MediaTransmit("127.0.0.1","1");
                                         String[] sessions={"zhaoyuehang","xiedong","likunxin"};
                                         MediaReceive R = new MediaReceive(sessions);



                                     }
                                 }
        );
        //添加图片按钮响应时间
        btnpic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
//                        JLabel label = new JLabel();
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                int result = chooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    String name = chooser.getSelectedFile().getPath();
//                            label.setIcon(new ImageIcon(name));
                    System.out.println(name);
                    ImageIcon img = new ImageIcon(name);
                    img.setImage(img.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT));
                    jtaSay.insertIcon(img);
                }
            }


        });
        //添加表情按钮响应事件
        final boolean[] b = {true};//好像只有数组才能传金click响应
        btnface.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                if (b[0])
                {
                    try {
                        init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    w.setVisible(false);
                }
                b[0]=!b[0];

                System.out.println("znangoi");

            }


        });
    }
    public void addtext(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,Color.blue);
        StyleConstants.setItalic(attrset,true);
        StyleConstants.setFontSize(attrset,24);

        Document docs=jtaChat.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
        str=str+"\n";
        try{
            docs.insertString(docs.getLength(),str,attrset);
        }catch(BadLocationException ble){
            System.out.println("BadLocationException:"+ble);
        }
    }
    public void addimg(String name){
        ImageIcon img = new ImageIcon(name);
        img.setImage(img.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT));
        jtaChat.insertIcon(img);
    }

    public void addti(String str){
        int i=0;
        String text="" ,img="";
        boolean p=true;
        for (i=0;i<str.length();i++){

            if (str.charAt(i)!='['&&str.charAt(i)!=']'){
                if (p){
                    text=text+str.charAt(i);
                }else {
                    img=img+str.charAt(i);
                }

            }else if (str.charAt(i)=='['){

                addtext(text);
                text="";
                p=false;
            }else if(str.charAt(i)==']'){
                changepo();
                addimg(img);
                img="";
                p=true;
            }

        }
        this.addtext(text);

    }
    public JButton getPicBtn(){
        return btnface;
    }
    public void changepo(){
        jtaChat.setCaretPosition(jtaChat.getDocument().getLength());
    }

    private void init() throws Exception{
        w = new JWindow();
        w.setSize(28*7,28*2);
        w.setVisible(true);
        w.setEnabled(true);

        JPanel p = new JPanel();
        //设置控件不透明
        p.setOpaque(true);
        p.setBounds(0,0,28*7,28*2);
        w.setLocationRelativeTo(null);

//        w.setLocation(20,20);

        //把Jpanel放在Jwingdow中
        w.setContentPane(p);
//        w.add(p);

        p.setLayout(gridLayout1);


        p.setBackground(SystemColor.text);

        String fileName = "";


        for(i=0;i <ico.length;i++){
//            fileName= "E:\\照片\\emotions 09\\"+i+1+".gif"; /*修改图片路径*/
//
//
//            ico[i]=new JButton("带图片的标签组件",JButton.CENTER);
            ico[i]=new JButton("带图片的标签组件");

            ico[i].setBounds(0,0,20,20);
            //设置图片
            String s = "09\\"+String.valueOf(i+1)+".jpg";
            URL url = ClientFrame.class.getResource( s);
            ImageIcon img = new ImageIcon(url);
            img.setImage(img.getImage().getScaledInstance(28,28,Image.SCALE_DEFAULT));
            ico[i].setIcon(img);
            //设置表情按钮相应
            ico[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    ImageIcon img1 = new ImageIcon(url);
                    img1.setImage(img.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT));
                    jtaSay.insertIcon(img1);

                }


            });

            p.add(ico[i]);
        }

    }

}
