package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.Pessoa;

public class PessoaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(Pessoa pessoa) throws MySQLIntegrityConstraintViolationException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Pessoa_tb(Tipo_Pessoa) VALUE(?)");
			stmt.setString(1, pessoa.getTipoPessoa());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel inserir nova pessoa");
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
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Pessoa) FROM Pessoa_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			pessoa.setIdPessoa(resultSet.getInt(1));
			

		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar dados da pessoa");
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
	
	public Pessoa busca(int idPessoa)
	{
		Pessoa pessoa = null;
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Tipo_Pessoa FROM Pessoa_tb WHERE Pessoa_tb.Id_Pessoa = " + idPessoa);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			pessoa = new Pessoa();
			pessoa.setIdPessoa(resultSet.getInt(1));
			pessoa.setTipoPessoa(resultSet.getString(2));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar pessoa");
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
		
		return pessoa;
	}
	
	public void remove(Pessoa pessoa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Pessoa_tb WHERE Id_Pessoa = " + pessoa.getIdPessoa());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover pessoa");
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
	
	public List<Pessoa> recupera()
	{
		List<Pessoa> pessoaLista = new LinkedList<Pessoa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Tipo_Pessoa FROM Pessoa_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(resultSet.getInt(1));
				pessoa.setTipoPessoa(resultSet.getString(2));
				
				pessoaLista.add(pessoa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar pessoas");
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

		return pessoaLista;
	}
}
