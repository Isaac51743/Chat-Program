import java.awt.*;

/**
 * Created by Edward on 2017/3/4.
 */
public class Client {
    ClientFrame cframe;
    String currentUserName = "";
    String talkingUser = "";
    //消息接收者uid  
    static StringBuilder uidReceiver = null;

    public Client(String username)
    {
        currentUserName = username;
    }

    public void go(String a, Connect conn) {
        //创建客户端窗口对象  
        cframe = new ClientFrame(conn);
        cframe.go(a, currentUserName);
        talkingUser = a;

        //窗口关闭键无效，必须通过退出键退出客户端以便善后  
        //cframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //获取本机屏幕横向分辨率  
        int w = Toolkit.getDefaultToolkit().getScreenSize().width;
        //获取本机屏幕纵向分辨率  
        int h = Toolkit.getDefaultToolkit().getScreenSize().height;
        //将窗口置中  
        cframe.setLocation((w - cframe.WIDTH)/2, (h - cframe.HEIGHT)/2);

        //设置客户端窗口为可见  
        cframe.setVisible(true);

    }

    public void recvMsg(String msg)
    {
        cframe.recvMsg(talkingUser + ":" + msg);
    }


}


