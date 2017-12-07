package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import WaterClasses.CasaPessoa;

public class CasaPessoaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(CasaPessoa casaPessoa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Casa_Pessoa_tb(Id_Casa, Id_Pessoa) VALUES(?,?)");
			stmt.setInt(1, casaPessoa.getIdCasa());
			stmt.setInt(2, casaPessoa.getIdPessoa());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel Associar casa e pessoa");
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
	
	public List<CasaPessoa> busca(int idCasa)
	{
		List<CasaPessoa> casaPessoaLista = new ArrayList<CasaPessoa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Id_Casa"
																  + " FROM Casa_Pessoa_tb"
																  + " WHERE Casa_Pessoa_tb.Id_Casa = " + idCasa);
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				CasaPessoa casaPessoa = new CasaPessoa();
				casaPessoa.setIdPessoa(resultSet.getInt(1));
				casaPessoa.setIdCasa(resultSet.getInt(2));
				
				casaPessoaLista.add(casaPessoa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar associaçao casa e pessoa");
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

		if(casaPessoaLista.size() == 0)
			return null;

		return casaPessoaLista;
	}
	
	public void remove(CasaPessoa casaPessoa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Casa_Pessoa_tb WHERE Id_Casa = " + casaPessoa.getIdCasa());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover associaçao casa e pessoa");
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
	
	public List<CasaPessoa> recupera()
	{
		List<CasaPessoa> casaPessoaLista = new LinkedList<CasaPessoa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Id_Casa"
																  + " FROM Casa_Pessoa_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				CasaPessoa casaPessoa = new CasaPessoa();
				casaPessoa.setIdPessoa(resultSet.getInt(1));
				casaPessoa.setIdCasa(resultSet.getInt(2));

				casaPessoaLista.add(casaPessoa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar associaçao casa e pessoa");
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

		return casaPessoaLista;
	}
}
