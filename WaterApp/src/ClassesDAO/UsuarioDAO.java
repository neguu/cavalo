package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.Usuario;

public class UsuarioDAO
{
	Connection connection = null;
	PreparedStatement stmt = null;
	
	public void adciona(Usuario usuario) throws MySQLIntegrityConstraintViolationException
	{
	
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Usuario_tb(Nome, Senha, Email) VALUE(?,?,?)");
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Impossivel criar novo usuario");
			throw new MySQLIntegrityConstraintViolationException();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao encerrar a conexão");
			}
		}
		
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Usuario) FROM Usuario_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			usuario.setIdUsuario(resultSet.getInt(1));

		}
		catch(SQLException e)
		{
			System.out.println("Impossivel configurar novo usuario");
			throw new MySQLIntegrityConstraintViolationException();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao encerrar a conexão");
			}
		}
	}
	
	public Usuario busca(String email, String senha)
	{
		Usuario usuario = null;
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Email, Nome, Senha, Id_Usuario FROM Usuario_tb WHERE Usuario_tb.Email = \"" + email + "\""
					+ " AND Senha = \"" + senha + "\"");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			usuario = new Usuario();
			usuario.setEmail(resultSet.getString(1));
			usuario.setNome(resultSet.getString(2));
			usuario.setSenha(resultSet.getString(3));
			usuario.setIdUsuario(resultSet.getInt(4));
		}
		catch(SQLException e)
		{
			System.out.println("Impossivel buscar usuario");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao encerrar a conexão");
			}
		}
		
		return usuario;
	}
	
	public void remove(Usuario usuario)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Usuario_tb WHERE Id_Usuario = " + usuario.getIdUsuario());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Impossivel remover atividade diaria");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao encerrar a conexão");
			}
		}	
	}
	
	public List<Usuario> recupera()
	{
		List<Usuario> usuarioLista = new LinkedList<Usuario>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Email, Nome, Senha, Id_Usuario FROM Usuario_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				Usuario usuario = new Usuario();
				usuario.setEmail(resultSet.getString(1));
				usuario.setNome(resultSet.getString(2));
				usuario.setSenha(resultSet.getString(3));
				usuario.setIdUsuario(resultSet.getInt(4));
				
				usuarioLista.add(usuario);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Impossivel buscar usuario");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao encerrar a conexão");
			}
		}

		return usuarioLista;
	}
}
