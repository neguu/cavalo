package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.Casa;

public class CasaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(Casa casa) throws MySQLIntegrityConstraintViolationException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Casa_tb(Rua, Bairro, Cidade, Estado, Numero, Tipo_Casa, Quantidade_Banheiros, Quantidade_Pessoas) VALUE(?,?,?,?,?,?,?,?)");
			stmt.setString(1, casa.getRua());
			stmt.setString(2, casa.getBairro());
			stmt.setString(3, casa.getCidade());
			stmt.setString(4, casa.getEstado());
			stmt.setInt(5, casa.getNumero());
			stmt.setString(6, casa.getTipoCasa());
			stmt.setInt(7, casa.getQuantidadeBanheiros());
			stmt.setInt(8, casa.getQuantidadePessoas());
			
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel inserir nova casa");
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
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Casa) FROM Casa_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			casa.setIdCasa(resultSet.getInt(1));

		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar dados da nova casa");
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
	
	public Casa busca(int idCasa)
	{
		Casa casa = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Quantidade_Banheiros, Quantidade_Pessoas, Tipo_Casa, Id_Casa, Rua, Bairro, Cidade, Estado, Numero"
																  + " FROM Casa_tb"
																  + " WHERE Casa_tb.Id_Casa = " + idCasa);
			ResultSet resultSet = stmt.executeQuery();
			
			resultSet.next();
	
			casa = new Casa();
				
			casa.setQuantidadeBanheiros(resultSet.getInt(1));
			casa.setQuantidadePessoas(resultSet.getInt(2));
			casa.setTipoCasa(resultSet.getString(3));
			casa.setIdCasa(resultSet.getInt(4));
			casa.setRua(resultSet.getString(5));
			casa.setBairro(resultSet.getString(6));
			casa.setCidade(resultSet.getString(7));
			casa.setEstado(resultSet.getString(8));
			casa.setNumero(resultSet.getInt(9));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar informaçoes da casa");
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
		return casa;
	}
	
	public void remove(Casa casa)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Casa_tb WHERE Id_Casa = " + casa.getIdCasa());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover casa");
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
	
	public List<Casa> recupera()
	{
		List<Casa> casaLista = new LinkedList<Casa>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Quantidade_Banheiros, Quantidade_Pessoas, Tipo_Casa, Id_Casa, Rua, Bairro, Cidade, Estado, Numero"
																  + " FROM Casa_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				Casa casa = new Casa();
				casa.setQuantidadeBanheiros(resultSet.getInt(1));
				casa.setQuantidadePessoas(resultSet.getInt(2));
				casa.setTipoCasa(resultSet.getString(3));
				casa.setIdCasa(resultSet.getInt(4));
				casa.setRua(resultSet.getString(5));
				casa.setBairro(resultSet.getString(6));
				casa.setCidade(resultSet.getString(7));
				casa.setEstado(resultSet.getString(8));
				casa.setNumero(resultSet.getInt(9));
				
				casaLista.add(casa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar informaçoes das casas");
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

		return casaLista;
	}
}
