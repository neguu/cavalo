package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import WaterClasses.ContaAgua;

public class ContaAguaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	public void adciona(ContaAgua conta) throws MySQLIntegrityConstraintViolationException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Conta_Agua_tb(Quantidade_Agua_Mensal) VALUE(?)");
			stmt.setDouble(1, conta.getQuantidadeAguaMensal());

			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel criar conta agua");
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
			
			stmt = (PreparedStatement)connection.prepareStatement("SELECT MAX(Id_Conta) FROM Conta_Agua_tb");
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			conta.setIdConta(resultSet.getInt(1));

		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar dados da nova conta");
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
	
	public ContaAgua busca(int idConta)
	{
		ContaAgua contaAgua = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Quantidade_Agua_Mensal, Quantidade_Consumo, Id_Conta"
																+ " FROM Conta_Agua_tb"
																+ " WHERE Id_Conta = " + idConta);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			contaAgua = new ContaAgua();
			contaAgua.setQuantidadeAguaMensal(resultSet.getDouble(1));
			contaAgua.setQuantidadeConsumo(resultSet.getDouble(2));
			contaAgua.setIdConta(resultSet.getInt(3));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar conta agua");
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
		
		return contaAgua;
	}
	
	public void remove(ContaAgua contaAgua)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Conta_Agua_tb WHERE Id_Conta = " + contaAgua.getIdConta());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover conta agua");
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
	
	public List<ContaAgua> recupera()
	{
		List<ContaAgua> contaAguaLista = new LinkedList<ContaAgua>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Quantidade_Agua_Mensal, Quantidade_Consumo, Id_Conta"
																  + " FROM Conta_Agua_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				ContaAgua contaAgua = new ContaAgua();
				contaAgua.setQuantidadeAguaMensal(resultSet.getDouble(1));
				contaAgua.setQuantidadeConsumo(resultSet.getDouble(2));
				contaAgua.setIdConta(resultSet.getInt(3));
				
				contaAguaLista.add(contaAgua);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar contas");
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

		return contaAguaLista;
	}
}
