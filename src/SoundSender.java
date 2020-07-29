import javax.sound.sampled.*;

/**
 * Created by Edward on 2017/4/18.
 */
public class SoundSender {
     private TargetDataLine line;
     private int bufferLength;
     private Thread thread;
     private boolean isStart;
     public SoundSender(int bufferLength){

         AudioFormat format =new AudioFormat(8000,16,2,true,true);
         DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
         try {
             line = (TargetDataLine) AudioSystem.getLine(info);
             line.open(format, line.getBufferSize());
         } catch (LineUnavailableException e) {
             e.printStackTrace();
         }
         this.bufferLength = bufferLength;
         isStart = false;
     }

    //读取麦克风的数据
     public void run() {
         byte[] buffer = new byte[bufferLength];
         while(isStart&&!thread.isInterrupted()){
             line.read(buffer, 0,buffer.length);//接受麦的数据写入buffer 
//             send(buffer);//发送待解决
         }
     }

     public void start(){
         if(thread == null || !thread.isAlive()){
             thread = new Thread();
             line.start();
             thread.start();
             isStart = true;
         }
     }

     public void stop(){
         thread.interrupt();
         line.stop();
         isStart = false;
     }
}
