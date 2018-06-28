import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class FileTrfSer {
	private Socket s;
	
	
	public void SND(BufferedImage img,String name,String IP) throws Exception{
		//ServerSocket ss=new ServerSocket(1235);
		
		
		
		s=new Socket(IP, 1234);
		s.setReuseAddress(true);
		
		DataOutputStream dos=new DataOutputStream(s.getOutputStream());
		
			dos.writeUTF(name);
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write( img, "png", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		while(true){
			byte[] mybytearray=imageInByte;
			
		//	FileInputStream fis=new FileInputStream(file);
		//	BufferedInputStream bis=new BufferedInputStream(fis);
			

			
		//	bis.read(mybytearray, 0, mybytearray.length);
			OutputStream os=s.getOutputStream();
			
			os.write(mybytearray, 0, mybytearray.length);
			
			os.flush();
			s.close();
			break;
		}
	
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			
			ServerSocket ss=new ServerSocket(1234);
			Socket s=ss.accept();
			FileTrfSer obj=new FileTrfSer();
			obj.SND();
					} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


*/
	}

}
