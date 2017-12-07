package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.AtividadeDiaria;

public class AtividadeDiariaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(AtividadeDiaria atividade) throws MySQLIntegrityConstraintViolationException
	{
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Atividade_Diaria_tb(Tipo_Atividade,  Contador_Atividade, Quantidade_Agua, Tempo_Atividade) VALUE(?,?,?,?)");
			stmt.setString(1, atividade.getTipoAtividade());
			stmt.setInt(2, atividade.getContadorAtividade());
			stmt.setDouble(3, atividade.getQuantidadeAgua());
			stmt.setString(4, atividade.getTempo());

			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel inserir nova atividade");
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
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Atividade) FROM Atividade_Diaria_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			atividade.setIdAtividade(resultSet.getInt(1));
			
			

		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar dados cadastrados da atividade");
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
	
	public AtividadeDiaria busca(int idAtividade)
	{
		AtividadeDiaria atividadeDiaria = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Tipo_Atividade, Contador_Atividade, Quantidade_Agua, Tempo_Atividade, Id_Atividade"
																  + " FROM Atividade_Diaria_tb"
																  + " WHERE Id_Atividade = " + idAtividade);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			atividadeDiaria = new AtividadeDiaria();
			atividadeDiaria.setTipoAtividade(resultSet.getString(1));
			atividadeDiaria.setContadorAtividade(resultSet.getInt(2));
			atividadeDiaria.setQuantidadeAgua(resultSet.getDouble(3));
			atividadeDiaria.setTempo(resultSet.getString(4));
			atividadeDiaria.setIdAtividade(resultSet.getInt(5));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar atividade diaria");
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
		
		return atividadeDiaria;
	}
	
	public void remove(AtividadeDiaria atividadeDiaria)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Atividade_Diaria_tb WHERE Id_Atividade = " + atividadeDiaria.getIdAtividade());
			
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover atividade diaria");
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
	
	public List<AtividadeDiaria> recupera()
	{
		List<AtividadeDiaria> atividadesDiariasLista = new LinkedList<AtividadeDiaria>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Tipo_Atividade, Contador_Atividade, Quantidade_Agua, Tempo_Atividade, Id_Atividade"
																  + " FROM Atividade_Diaria_tb");
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next())
			{
				AtividadeDiaria atividadeDiaria = new AtividadeDiaria();
				
				atividadeDiaria.setTipoAtividade(resultSet.getString(1));
				atividadeDiaria.setContadorAtividade(resultSet.getInt(2));
				atividadeDiaria.setQuantidadeAgua(resultSet.getDouble(3));
				atividadeDiaria.setTempo(resultSet.getString(4));
				atividadeDiaria.setIdAtividade(resultSet.getInt(5));
				
				atividadesDiariasLista.add(atividadeDiaria);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover atividade diaria");
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
		
		return atividadesDiariasLista;
	}
}
