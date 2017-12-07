package App;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia
{
	public static String criptografar(String senha)
	{
		try
		{
			MessageDigest criptografia = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = criptografia.digest(senha.getBytes("UTF-8"));
		 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest)
			{
				hexString.append(String.format("%02X", 0xFF & b));
			}
			
			senha = hexString.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			
		}
		catch(UnsupportedEncodingException e)
		{
			
		}
		
		return senha;
	}
}
