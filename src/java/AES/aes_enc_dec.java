/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;

/**
 *
 * @author sumit
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class aes_enc_dec {
public void encrypt(String file_path,String e_out_path)
{
try {
			String key = "squirrel123"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream(file_path);
			FileOutputStream fos = new FileOutputStream(e_out_path);
			encrypt(key, fis, fos);

			
		} catch (Throwable e) {
			e.printStackTrace();
		}



}

public void decrypt(String file_path,String d_out_path)
{
try {
			String key = "squirrel123"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream(file_path);
			FileOutputStream fos = new FileOutputStream(d_out_path);
			decrypt(key, fis, fos);

			
		} catch (Throwable e) {
			e.printStackTrace();
		}



}
	public static void main(String[] args) {
		try {
			String key = "squirrel123"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream("D://e/a.txt");
			FileOutputStream fos = new FileOutputStream("D://e/ea.txt");
			encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("D://e/ea.txt");
			FileOutputStream fos2 = new FileOutputStream("D://e/da.txt");
			decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

}