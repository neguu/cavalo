package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import WaterClasses.UsuarioCasa;

public class UsuarioCasaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;

	public void adciona(UsuarioCasa usuarioCasa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			//configurando relações
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Usuario_Casa_tb(Id_Casa, Id_Usuario) VALUES(?,?)");
			stmt.setInt(1, usuarioCasa.getIdCasa());
			stmt.setInt(2, usuarioCasa.getIdUsuario());	

			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel associar usuario e casa");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}
	}
	
	public List<UsuarioCasa> busca(int idUsuario)
	{
		List<UsuarioCasa> usuarioCasaLista = new ArrayList<UsuarioCasa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Usuario_Casa_tb.Id_Casa, Usuario_Casa_tb.Id_Usuario"
									+ " FROM Usuario_Casa_tb"
									+ " WHERE Usuario_Casa_tb.Id_Usuario = " + idUsuario);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next())
			{
				UsuarioCasa usuarioCasa = new UsuarioCasa();
				
				usuarioCasa.setIdCasa(resultSet.getInt(1));
				usuarioCasa.setIdUsuario(resultSet.getInt(2));
				
				usuarioCasaLista.add(usuarioCasa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar associaçao usuario e casa");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}
		
		if(usuarioCasaLista.size() == 0)
			return null;
		
		return usuarioCasaLista;
	}
	
	public void remove(UsuarioCasa usuarioCasa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Usuario_Casa_tb WHERE Id_Usuario = " + usuarioCasa.getIdUsuario());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover associaçao usuario casa");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}	
	}
	
	public List<UsuarioCasa> recupera()
	{
		List<UsuarioCasa> usuarioCasaLista = new LinkedList<UsuarioCasa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Usuario_Casa_tb.Id_Casa, Usuario_Casa_tb.Id_Usuario"
																+ " FROM Usuario_Casa_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				UsuarioCasa usuarioCasa = new UsuarioCasa();

				usuarioCasa.setIdCasa(resultSet.getInt(1));
				usuarioCasa.setIdUsuario(resultSet.getInt(2));

				usuarioCasaLista.add(usuarioCasa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar associaçao usuario casa");
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}

		return usuarioCasaLista;
	}
}
