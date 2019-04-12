package pack1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



import org.apache.commons.codec.binary.Base64;
// AES 256 Encryption Algorithm

public class AESAlgorithm {

private static final String ALGORITHM = "AES";
private static final int ITERATIONS = 2;
private static final byte[] keyValue = 
    new byte[] { '0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};

public static String decrypt(String text, String secretKey) {
    Cipher cipher;
    String encryptedString;
    byte[] encryptText = null;
    byte[] raw;
    SecretKeySpec skeySpec;
    try {
        raw = Base64.decodeBase64(secretKey);
        skeySpec = new SecretKeySpec(raw, "AES");
        encryptText = Base64.decodeBase64(text);
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        encryptedString = new String(cipher.doFinal(encryptText));
    } catch (Exception e) {
        e.printStackTrace();
        return "Error";
    }
    return encryptedString;
}
public static String encrypt(String text, String secretKey) {
    byte[] raw;
    String encryptedString;
    SecretKeySpec skeySpec;
    byte[] encryptText = text.getBytes();
    Cipher cipher;
    try {
        raw = Base64.decodeBase64(secretKey);
        skeySpec = new SecretKeySpec(raw, "AES");
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
    } 
    catch (Exception e) {
        e.printStackTrace();
        return "Error";
    }
    return encryptedString;
}

public static void main(String[] args) throws Exception {
	String data=null;
    String line;
    BufferedReader br11 = new BufferedReader(new FileReader("E:\\workspace_for_BESTool\\AttributeBased\\WebContent\\tempfile\\New Text Document.txt"));
    while ((line = br11.readLine()) != null) {
        data=data+line;
    }
	String enc;
		String digest1=null;
	
		enc = AESAlgorithm.encrypt(data,"XMzDdG4D03CKm2IxIWQw7g==");
		FileWriter fw1=new FileWriter("E:\\workspace_for_BESTool\\AttributeBased\\WebContent\\uploadFile\\New Text Document.txt");  
		fw1.write(enc);  
		fw1.close();
    
   // String passwordEnc = AESAlgorithm.encrypt("hello good morning","a");
    //String passwordDec = AESAlgorithm.decrypt(passwordEnc,"a");
    //System.out.println("Encrypted : " + passwordEnc);
   //System.out.println("Decrypted : " + passwordDec);
	/*String data=new String();
    String line;
    String filepath = "E:\\workspace_for_BESTool\\AttributeBased\\WebContent\\decrypt\\abc.txt";
    String filepath1 = "E:\\workspace_for_BESTool\\AttributeBased\\WebContent\\uploadFile\\abc.txt";
    BufferedReader br11 = new BufferedReader(new FileReader(filepath1));
    while ((line = br11.readLine()) != null) {
        data=data+line;
    }
    System.out.println(data);
    String decrypt=null;
	
		System.out.println("in decrypt try");
		decrypt = AESAlgorithm.decrypt(data,"XMzDdG4D03CKm2IxIWQw7g==");
		//decrypt.replace
		decrypt=decrypt.replaceFirst("null","");
	System.out.println(decrypt);
	FileWriter fw1=new FileWriter(filepath);  
	fw1.write(decrypt);  
	fw1.close();*/
}
}