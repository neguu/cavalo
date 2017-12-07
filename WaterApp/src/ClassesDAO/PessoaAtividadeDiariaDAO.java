package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import WaterClasses.PessoaAtividadeAdiaria;

public class PessoaAtividadeDiariaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(PessoaAtividadeAdiaria pessoaAtividade)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Pessoa_Atividade_Adiaria_tb(Id_Atividade, Id_Pessoa) VALUES(?,?)");
			stmt.setInt(1, pessoaAtividade.getIdAtividade());
			stmt.setInt(2, pessoaAtividade.getIdPessoa());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel associar pessoa e atividade diaria");
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
	
	public List<PessoaAtividadeAdiaria> busca(int idPessoa)
	{
		List<PessoaAtividadeAdiaria> pessoaAtividadeDiariaLista = new ArrayList<PessoaAtividadeAdiaria>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Id_Atividade"
																  + " FROM Pessoa_Atividade_Adiaria_tb"
																  + " WHERE Id_Pessoa = " + idPessoa);
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				PessoaAtividadeAdiaria pessoaAtividade = new PessoaAtividadeAdiaria();

				pessoaAtividade.setIdPessoa(resultSet.getInt(1));
				pessoaAtividade.setIdAtividade(resultSet.getInt(2));

				pessoaAtividadeDiariaLista.add(pessoaAtividade);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar associaçao pessoa e atividade");
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

		if(pessoaAtividadeDiariaLista.size() == 0)
			return null;

		return pessoaAtividadeDiariaLista;
	}
	
	public void remove(PessoaAtividadeAdiaria pessoaAtividadeDiaria)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Pessoa_Atividade_Adiaria_tb WHERE Id_Pessoa = " + pessoaAtividadeDiaria.getIdPessoa());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover associaçao pessoa e atividade");
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
	
	public List<PessoaAtividadeAdiaria> recupera()
	{
		List<PessoaAtividadeAdiaria> pessoaAtividadeDiariaLista = new ArrayList<PessoaAtividadeAdiaria>();
		
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Pessoa, Id_Atividade"
																  + " FROM Pessoa_Atividade_Adiaria_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				PessoaAtividadeAdiaria pessoaAtividade = new PessoaAtividadeAdiaria();

				pessoaAtividade.setIdPessoa(resultSet.getInt(1));
				pessoaAtividade.setIdAtividade(resultSet.getInt(2));

				pessoaAtividadeDiariaLista.add(pessoaAtividade);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar associaçao pessoa e atividade");
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

		return pessoaAtividadeDiariaLista;
	}
}
