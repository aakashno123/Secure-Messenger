import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

public class FileTrfCli implements Runnable {
	private String fileName;
	private ServerSocket ss=null;
	private Socket s=null;
	private volatile static boolean done=false;
	
	public String RCV() {
		String path=null;
		try{ss=new ServerSocket(1234);
		s=ss.accept();
		ss.setReuseAddress(true);
		s.setReuseAddress(true);
		InputStream is=s.getInputStream();
		DataInputStream dis=new DataInputStream(s.getInputStream());
		 fileName=dis.readUTF();
		File file=new File(System.getProperty("user.home")+"\\Desktop\\"+fileName);
		FileOutputStream fos=new FileOutputStream(file);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		path=file.getAbsolutePath();
		byte[] mybytearray=new byte[1024];
		int count;
		
		while ((count=is.read(mybytearray))>0) {
			bos.write(mybytearray,  0 , count);
		}
		bos.close();
		s.close();
		ss.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return path;
	}

	
	public static boolean getter(){
		return done;
	}
	
	
	public static void setter(boolean value){
		FileTrfCli.done=value;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!done){
			String path=RCV();
			SGUI.setRcfile(path);
			SGUI.OpenAfterRCV();
		}
		
	}

}
