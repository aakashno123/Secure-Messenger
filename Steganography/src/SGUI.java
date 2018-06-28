import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;

public class SGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{	
	try {
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	private static StringBuilder ssttrr=new StringBuilder();
	private static JTextArea textArea;
	private static Thread thread;
	private long jai;
	private static String rcfile;
	private static FileTrfCli obj;
	private static JLabel label;
	private JPanel contentPane;
	private JLabel label_1;
	private String path;
	private static SGUI frame;
	private BufferedImage receivedImg;
	private static BufferedImage img;
	private JTextField txtIP;
	private String filename;
	public static boolean flag=false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SGUI();
					frame.setVisible(true);
					frame.setTitle("Steganographer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SGUI(String path){
		setRcfile(path);
		//OpenAfterRCV(rcfile);
	}

	/**
	 * Create the frame.
	 */
	public SGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 767);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 13, 772, 706);
		contentPane.add(scrollPane);
		
		label = new JLabel("");
		scrollPane.setViewportView(label);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(881, 13, 97, 25);
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Open();
			}
		});
		contentPane.add(btnOpen);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(990, 13, 97, 25);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Save();
			}
		});
		contentPane.add(btnSave);
		
		JButton btnHide = new JButton("Hide");
		btnHide.setBounds(881, 621, 97, 25);
		btnHide.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(img==null){
					JOptionPane.showMessageDialog(frame, "Please open an image first !!");
				}else if(chkLen()){
					Hide();
				}else{
					JOptionPane.showMessageDialog(null, "The message is too long for the selected image!!");
				}
			}
		});
		contentPane.add(btnHide);
		obj=new FileTrfCli();
		
  Thread thread=new Thread(obj);
  thread.start();
		
		//System.out.println(thread.isAlive());

		JButton btnExtract = new JButton("Extract");
		btnExtract.setBounds(990, 621, 97, 25);
		btnExtract.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(img==null){
					JOptionPane.showMessageDialog(frame, "Please open an image first !!");
				}else{
					Extract();	
				}
				
			}
		});
		contentPane.add(btnExtract);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(784, 53, 303, 477);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("Georgia", Font.ITALIC, 16));
		textArea.setRows(6);
		textArea.setColumns(10);
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				JAI();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				JAI();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				JAI();
			}
		});
		
		label_1 = new JLabel("");
		label_1.setBounds(955, 543, 56, 16);
		contentPane.add(label_1);
		
		JLabel lblNumberOfCharacters = new JLabel("Number of characters left:");
		lblNumberOfCharacters.setBounds(794, 543, 225, 16);
		contentPane.add(lblNumberOfCharacters);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(990, 659, 97, 25);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileTrfSer obj=new FileTrfSer();
				try {
					
					if(txtIP.getText().equals("")){
						JOptionPane.showMessageDialog(frame, "Please Enter a valid IP Address!!");
					}
					if(img==null){
						JOptionPane.showMessageDialog(frame, "Please Open an image !!");
					}
					else{
						obj.SND(img, filename, txtIP.getText());	
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSend);
		
		JLabel lblIpAddress = new JLabel("IP address:");
		lblIpAddress.setBounds(784, 662, 65, 16);
		contentPane.add(lblIpAddress);
		
		txtIP = new JTextField();
		txtIP.setBounds(862, 659, 116, 22);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
	}


	//Opens and set the image to the GUI.
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	void Open(){
		textArea.setText("");
		JFileChooser fc=new JFileChooser("C:\\Users\\AKASH\\Desktop");
		if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file=new File(path=fc.getSelectedFile().getAbsolutePath()) ;
			try {
				
				img=ImageIO.read(file);
				frame.setTitle(file.getName()+"   --Steganographer");
				filename=file.getName();
				label.setIcon(new ImageIcon(img));
				jai=img.getWidth()*img.getHeight()/4-16;
				label_1.setText(jai+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	public static void OpenAfterRCV(){
		
	
		try {
			JOptionPane.showMessageDialog(frame, "Message Received!");
			
			//System.out.println("oar");
		//System.out.println(rcfile);
		
			File f=new File(rcfile);
			//System.out.println(f.exists());
			img=ImageIO.read(f);
			//img=deepCopy(receivedImg);
			//System.out.println(img.getHeight());
			
			
			label.setIcon(new ImageIcon(img));
			Extract();
			//System.out.println("after label");
			FileTrfCli.setter(true);
			TimeUnit.SECONDS.sleep(10);
			thread=new Thread(obj);
			FileTrfCli.setter(false);
			//System.out.println("hooka");
			f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	public static void fun(){
		flag=true;
	}
	
	private void JAI(){
		long illi=jai-textArea.getText().length();		
		label_1.setText(illi+"");
	}
	
	
	boolean chkLen(){
		
		if((img.getHeight()*img.getWidth())>=((16+textArea.getText().length()*8))){
			return true;
		}else{
			return false;
		}
	}
	
	void Hide(){
		int count=0;
		int k=0;
		//byte [] barry=textArea.getText().getBytes();
		//System.out.println(textArea.getText());
		byte [] barry=Masking.encrypt(textArea.getText()).getBytes();
		byte [] bit=new byte[barry.length*8];
		int LEN=bit.length;
		for(byte b:barry){
			 //System.out.print(b);
			 //System.out.print("\t");
			bit[k]=(byte)(b&0b1);k++;
			bit[k]=(byte)((b>>1)&0b1);k++;
			bit[k]=(byte)((b>>2)&0b1);k++;
			bit[k]=(byte)((b>>3)&0b1);k++;
			bit[k]=(byte)((b>>4)&0b1);k++;
			bit[k]=(byte)((b>>5)&0b1);k++;
			bit[k]=(byte)((b>>6)&0b1);k++;
			bit[k]=(byte)((b>>7)&0b1);k++;
		}
		
		k=0;
		int p,a,r,g,b;
		//System.out.println("LEN at writing "+LEN);
		outer:
		for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				p=img.getRGB(j, i);
				if(count>=32){
					//writing data
					if(k>=LEN){
						break outer;
					}
					a=p>>24&0xff;
					r=p>>16&0xff;
					g=((p>>8)&0b11111110)|bit[k];k++;
					b=(p&0b11111110)|bit[k];k++;
					p=(a<<24)|(r<<16)|(g<<8)|(b);
				}else{
					//writing LEN
					
					a=p>>24&0xff;
					r=p>>16&0xff;
					g=p>>8&0xff;
					b=p&0xff;
					//System.out.print("("+a+";"+r+";"+g+";"+b+")--->");
					
					a=p>>24&0xff;
					r=p>>16&0xff;
					g=((p>>8)&0b11111110)|((LEN>>count)&0b1);count++;
					b=((p)&0b11111110)|((LEN>>count)&0b1);count++;
					p=(a<<24)|(r<<16)|(g<<8)|(b);
					//System.out.println("("+a+";"+r+";"+g+";"+b+")");
				}
				img.setRGB(j, i, p);
				
				
			}
		}
		label.setIcon(new ImageIcon(img));
		textArea.setText("");
		JOptionPane.showMessageDialog(null, "Done!!");
	}
	
	

	static void Extract(){
		int k=0,count=0;
		int p,a,r,g,b;
		int LEN=0;
		int val=0,counter=0;
		outer:
		for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				p=img.getRGB(j, i);
				if(count>=32){
					if(k>=LEN){
						break outer;
					}
	
					val=val|((p>>8&1)<<counter++);
					val=val|((p&1)<<counter++);
					if (counter%8==0)
					{
						counter=0;
						k+=8;
						//System.out.print((char)val);
					char ch=(char)val;
					ssttrr.append(ch);
						val=0;
						
					}
				}else{
					
					LEN=LEN|((p>>8&1)<<count++);
					LEN=LEN|((p&1)<<count++);
					a=p>>24&0xff;
					r=p>>16&0xff;
					g=p>>8&0xff;
					b=p&0xff;
					//System.out.print("("+a+";"+r+";"+g+";"+b+")");
				}
				
			}
			//System.out.println();
		}
		
		//System.out.println("\nLEN at reading "+LEN);
		//System.out.println(ssttrr);
		textArea.setText(Masking.decrypt(ssttrr));
		//JOptionPane.showMessageDialog(null,Masking.decrypt(ssttrr));
		ssttrr.delete(0,ssttrr.length());
		JOptionPane.showMessageDialog(frame, "Done !!");
	}
	
	void Save(){
		try {
			File f=new File(path+"OUTPUT.png");
			ImageIO.write(img, "png", f);
			File fff=new File(path+"OUTPUT.jpg");
			if(fff.exists()){
				fff.delete();
			}
			f.renameTo(fff);
			JOptionPane.showMessageDialog(null, "Saved !!!!!");
		
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Could not save the image!!");
		}
	}

	public static String getRcfile() {
		return rcfile;
	}

	public static void setRcfile(String rcfile) {
		SGUI.rcfile = rcfile;
		
	}
}