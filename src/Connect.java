import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Created by Edward on 2017/3/4.
 */
public class Connect {
    private Socket socket;
    String rec;
    InputStream is ;
    OutputStream os ;
    InputStreamReader input;
    BufferedReader inputbuf;

    public Connect()
    {
        Connect("127.0.0.1", 9090);
    }

    public void Connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            is = socket.getInputStream();
            input = new InputStreamReader(is);
            inputbuf = new BufferedReader(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void send(String s)  {
        try{
            // 向服务器端发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println(s);
            pw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String rec(){
        String r = "";
        try{
            r = inputbuf.readLine();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }
    public void stop(){
        try{
            socket.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public int len() {
        int len = 0;
        try {
            byte[] buf = new byte[1024];
            len = is.read(buf);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return len;
    }
    public int port(){
        int port;
        port=socket.getLocalPort();
        return port;
    }
}
