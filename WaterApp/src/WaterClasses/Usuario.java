package WaterClasses;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario
{
	private String nome;
	private String senha;
	private String email;
	private int idUsuario;
	
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getSenha()
	{
		return senha;
	}
	public void setSenha(String senha)
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
			
			this.senha = hexString.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			
		}
		catch(UnsupportedEncodingException e)
		{
			
		}
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getIdUsuario()
	{
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario)
	{
		this.idUsuario = idUsuario;
	}
	
	public String toString()
	{
		return String.format("Usuario\nId: %s\nNome: %s\nEmail: %s\nSenha: %s\n", idUsuario, nome, email, senha);
	}
}
