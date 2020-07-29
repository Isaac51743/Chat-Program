import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by Edward on 2017/3/18.
 */

public class Userlist extends JFrame {
    //窗口宽度  
    final int WIDTH = 250;
    //窗口高度  
    final int HEIGHT = 700;

    //当前在线列表的列标题  
    String[] colTitles = {"用户名", "用户状态"};
    //当前在线列表的数据  

    DefaultTableModel tablemodel = new DefaultTableModel(0,2);

    JLabel username = new JLabel("");

    //创建当前在线列表  

    JTable jtbOnline = new JTable(tablemodel);

    //创建当前在线列表的滚动窗  
    JScrollPane jspOnline = new JScrollPane(jtbOnline);


    Dictionary<String, Client> onlineUserWindow = new Hashtable<String, Client>();



    //设置默认窗口属性，连接窗口组件  
    public Userlist(Connect conn, String username) {

        //标题  
        setTitle("联系人");
        //大小  
        setSize(WIDTH, HEIGHT);
        //不可缩放  
        setResizable(false);
        //设置布局:不适用默认布局，完全自定义  
        setLayout(null);
        //设置滚动条
        jtbOnline.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        jspOnline.setViewportView(jtbOnline);
//        jspOnline.setBounds(new Rectangle(100, 17, 142, 114));


        tablemodel.setColumnIdentifiers(colTitles);
        //设置jtable大小位置
//        jtbOnline.setBounds(20,70,210,590);




        //设置滚动窗的水平滚动条属性:不出现  
        jspOnline.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //设置滚动窗的垂直滚动条属性:需要时自动出现  
//        jspOnline.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //设置当前在线列表滚动窗大小和位置  
        jspOnline.setBounds(20, 70, 210, 590);
        //添加当前在线列表  
        this.add(jspOnline);
        this.setname("赵月航");

        //添加在线列表项被鼠标选中的相应事件  
        jtbOnline.addMouseListener(new MouseListener(){
                                       @Override
                                       public void mouseClicked(MouseEvent event)
                                       {
                                           //取得在线列表的数据模型  
                                           DefaultTableModel tbm = (DefaultTableModel) jtbOnline.getModel();
                                           //提取鼠标选中的行作为消息目标，最少一个人，最多全体在线者接收消息  
                                           int[] selectedIndex = jtbOnline.getSelectedRows();


                                           String userName = (String) tbm.getValueAt(selectedIndex[0],0);

                                           Client b =new Client(username);

                                           onlineUserWindow.put(userName, b);

                                           b.go(userName, conn);

                                       }
                                       @Override
                                       public void mousePressed(MouseEvent event){};
                                       @Override
                                       public void mouseReleased(MouseEvent event){};
                                       @Override
                                       public void mouseEntered(MouseEvent event){};
                                       @Override
                                       public void mouseExited(MouseEvent event){};
                                   }
        );
        setVisible(true);
    }

    public void updateUserList(Dictionary<String,String> userList)
    {
        tablemodel.setRowCount(0);

        Enumeration e = userList.keys();

        while( e. hasMoreElements() ){
            Vector newRow = new Vector();

            String username = (String) e.nextElement();

            newRow.add(username);
            System.out.println(username);

            newRow.add(userList.get(username));

            tablemodel.addRow(newRow);
        }
    }

    public void receiveMsg(String username, String msg)
    {
        Client tempWindow = onlineUserWindow.get(username);
        tempWindow.recvMsg(msg);

    }
    public void setname(String a){
        //添加用户姓名
        username.setText(a);
        username.setBounds(20, 20, 210, 40);
        username.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(username);
    }


}