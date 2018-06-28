import java.util.Random;

public class Masking {
public static String encrypt(String arg)
{
	//System.out.println(arg);
	StringBuilder s=new StringBuilder();
	Random obj=new Random();
	int num1=0,num2=0;
	for(int i=0;i<arg.length();i++)
	{
		while(true)
		{
			num1=obj.nextInt(255);
			//num2=(((int)arg.charAt(i))+num1)%256;
			num2=((int)arg.charAt(i))^num1;
			if (((num1<128 || num1>159) && (num2<128 || num2>159)))
				break;
		
		}
		s.append((char)num2);
		s.append((char)num1);
		
	}
	//System.out.println(s);
	return s.toString();
}
public static String decrypt(StringBuilder arg)
{
	//System.out.println(arg);
	StringBuilder s=new StringBuilder();
	int num1,num2,num3;
	for(int i=0;i<arg.length();i+=2)
	{
		//s.append((char)(((int)arg.charAt(i)-(int)arg.charAt(i+1))%256));
		num2=(int)arg.charAt(i);
		num1=(int)arg.charAt(i+1);
		num3=num2^num1;
		s.append((char)num3);
	}
	//System.out.println(s);
	return s.toString();
}
public static void main(String []arg) {
	Masking obj=new Masking();
	StringBuilder xyz=new StringBuilder();
	int i;
	/*for(i=0;i<256;i++)
	{
		xyz.append((char)i);
	}
	for(i=0;i<256;i++)
	{
		if ((int)xyz.charAt(i)!=i)
			System.out.println(i);
		else
			System.out.println("");
	}*/
	//String s=obj.encrypt("AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZ11223344556677889900aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz");
	//System.out.println(s);
	//StringBuilder sb=new StringBuilder(obj.encrypt("AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZ11223344556677889900aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz"));
	StringBuilder sb=new StringBuilder(obj.encrypt("A"));
	System.out.println(obj.decrypt(sb));
}
}