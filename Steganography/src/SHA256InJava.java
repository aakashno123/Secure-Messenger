import java.security.MessageDigest;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

 

/**
6
 * Demonstrates how to generate SHA256 hash in Java
7
 * @author JJ
8
 */

public class SHA256InJava extends JFrame{

 

    public static void main(String[] args) {

       // Scanner sn = new Scanner(System.in);

        //System.out.print("Please enter data for which SHA256 is required:");
    	String data=JOptionPane.showInputDialog(null, "Please enter data for which SHA256 is required:");
     //   String data = sn.nextLine();

         

        SHA256InJava sj = new SHA256InJava();

        String hash = sj.getSHA256Hash(data);

        //System.out.println("The SHA256 (hexadecimal encoded) hash is:"+hash);
JOptionPane.showMessageDialog(null,"The SHA256 (hexadecimal encoded) hash is:"+hash);
    }

 

    /**
22
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
23
     * @param data
24
     * @return
25
     */

    public String getSHA256Hash(String data) {

        String result = null;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(data.getBytes("UTF-8"));

            return bytesToHex(hash); // make it printable

        }catch(Exception ex) {

            ex.printStackTrace();

        }

        return result;

    }

     

    /**
39
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
40
     * to a hexadecimal string. Note that this generates hexadecimal in upper case.
41
     * @param hash
42
     * @return
43
     */

    private String  bytesToHex(byte[] hash) {

        return DatatypeConverter.printHexBinary(hash);

    }

}