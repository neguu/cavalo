package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import WaterClasses.CasaContaAgua;

public class CasaContaAguaDAO
{
	private Connection connection = null;
	private PreparedStatement stmt = null;
	
	public void adciona(CasaContaAgua casaContaAgua)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			//configurando relações
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Casa_Conta_Agua_tb(Id_Casa, Id_Conta) VALUES(?,?)");
			stmt.setInt(1, casaContaAgua.getIdCasa());
			stmt.setInt(2, casaContaAgua.getIdConta());
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel associar Conta Agua com a(s) Casa(s)");
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
	
	public CasaContaAgua busca(int idCasa)
	{
		CasaContaAgua casaContaAgua = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Casa, Id_Conta"
																  + " FROM Casa_Conta_Agua_tb"
																  + " WHERE Id_Casa = " + idCasa);
			ResultSet resultSet = stmt.executeQuery();

			resultSet.next();
			
			casaContaAgua = new CasaContaAgua();	
			casaContaAgua.setIdCasa(resultSet.getInt(1));
			casaContaAgua.setIdConta(resultSet.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar associaçao da conta com a casa");
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
		return casaContaAgua;
	}
	
	public void remove(CasaContaAgua casaContaAgua)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Casa_Conta_Agua_tb WHERE Id_Conta = " + casaContaAgua.getIdConta());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover associacao da conta com a agua");
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
	
	public List<CasaContaAgua> recupera()
	{
		List<CasaContaAgua> casaContaAguaLista = new LinkedList<CasaContaAgua>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Casa, Id_Conta"
																  + " FROM Casa_Conta_Agua_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				CasaContaAgua casaContaAgua = new CasaContaAgua();
				
				casaContaAgua.setIdCasa(resultSet.getInt(1));
				casaContaAgua.setIdConta(resultSet.getInt(2));
			
				casaContaAguaLista.add(casaContaAgua);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar associaçao da conta com a casa");
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

		return casaContaAguaLista;
	}
}
