package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.ObjetosConsumo;

public class ObjetosConsumoDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;

	public void adciona(ObjetosConsumo objeto) throws MySQLIntegrityConstraintViolationException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Objetos_De_Consumo_tb(Nome_Objeto, Quantidade_Agua) VALUE(?,?)");
			stmt.setString(1, objeto.getNomeObjeto());
			stmt.setDouble(2, objeto.getQuantidadeAgua());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel inserir novo objeto de consumo");
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
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Objeto_Consumo) FROM Objetos_De_Consumo_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			objeto.setIdObjetoConsumo(resultSet.getInt(1));
			

		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar dados do novo objeto");
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
				System.out.println("║ Erro ao encerrar a conexão");
			}
		}
	}
	
	public ObjetosConsumo busca(int idObjetoConsumo)
	{
		ObjetosConsumo objeto = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Nome_Objeto, Quantidade_Agua, Id_Objeto_Consumo"
																  + " FROM Objetos_De_Consumo_tb"
																  + " WHERE Id_Objeto_Consumo = " + idObjetoConsumo);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			objeto = new ObjetosConsumo();
			objeto.setNomeObjeto(resultSet.getString(1));
			objeto.setQuantidadeAgua(resultSet.getDouble(2));
			objeto.setIdObjetoConsumo(resultSet.getInt(3));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar objetos de consumo");
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
		
		return objeto;
	}
	
	public void remove(ObjetosConsumo objetosConsumo)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Objetos_De_Consumo_tb WHERE Id_Objeto_Consumo = " + objetosConsumo.getIdObjetoConsumo());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover objetos de consumo");
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
	
	public List<ObjetosConsumo> recupera()
	{
		List<ObjetosConsumo> objetosConsumoLista = new LinkedList<ObjetosConsumo>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Nome_Objeto, Quantidade_Agua, Id_Objeto_Consumo"
																  + " FROM Objetos_De_Consumo_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				ObjetosConsumo objeto = new ObjetosConsumo();
				objeto.setNomeObjeto(resultSet.getString(1));
				objeto.setQuantidadeAgua(resultSet.getDouble(2));
				objeto.setIdObjetoConsumo(resultSet.getInt(3));
				
				objetosConsumoLista.add(objeto);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar os objetos de consumo");
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

		return objetosConsumoLista;
	}
}
