import javax.sound.sampled.*;

/**
 * Created by Edward on 2017/4/18.
 */
public class SoundReceiver {
    private SourceDataLine line;
    private Thread thread;
    private boolean isStart;
    public SoundReceiver(){
        AudioFormat format =new AudioFormat(8000,16,2,true,true);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, 10240);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(isStart&&!thread.isInterrupted()){
//            byte[] data = receive();接收接口待解决
//            line.write(data, 0, data.length);//将data的数据转化为音频
            }
    }
    public void start(){
        if(thread == null || !thread.isAlive()){
            thread = new Thread();
            line.start();
            isStart = true;
//            contect();建立tcp链接待解决
            thread.start();
        }
    }
    public void stop(){
        thread.interrupt();
        line.stop();
        isStart = false;
//        closetcp();断开tcp链接待解决
    }
}
