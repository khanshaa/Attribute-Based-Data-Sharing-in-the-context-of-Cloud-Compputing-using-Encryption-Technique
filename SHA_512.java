package pack1;
import javax.xml.bind.DatatypeConverter;

import java.io.File;

public class SHA_512 {

	public static void main(String[] args) {
		File file = new File("F:\\2018 student temp project\\attribute base\\a.txt");
		File file1 = new File("F:\\2018 student temp project\\attribute base\\b.txt");

        //System.out.println("MD5    : " + toHex(Hash.MD5.checksum(file)));
        //System.out.println("SHA1   : " + toHex(Hash.SHA1.checksum(file)));
        //System.out.println("SHA256 : " + toHex(Hash.SHA256.checksum(file)));
        System.out.println("SHA512 : " + toHex(Hash.SHA1.checksum(file)));
        System.out.println("SHA512 : " + toHex(Hash.SHA1.checksum(file1)));

	}

	private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }
	public static String getSH1(File f)
	{
		return toHex(Hash.SHA1.checksum(f));
	}

}
