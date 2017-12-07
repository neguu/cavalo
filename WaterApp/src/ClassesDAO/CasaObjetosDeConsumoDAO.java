package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import WaterClasses.CasaObjetosConsumo;

public class CasaObjetosDeConsumoDAO
{
	Connection connection = null;
	PreparedStatement stmt = null;
	
	public void adciona(CasaObjetosConsumo casaObjetoConsumo)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");
			
			stmt = (PreparedStatement)connection.prepareStatement("INSERT INTO Casa_Objetos_De_Consumo_tb(Id_Casa, Id_Objeto_Consumo) VALUES(?,?)");
			stmt.setInt(1, casaObjetoConsumo.getIdCasa());
			stmt.setInt(2, casaObjetoConsumo.getIdObjetoConsumo());
			stmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel associar casa e os objetos de consumo");
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
	
	public List<CasaObjetosConsumo> busca(int idCasa)
	{
		List<CasaObjetosConsumo> casaObjetosConsumoLista = new ArrayList<CasaObjetosConsumo>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Casa, Id_Objeto_Consumo"
																  + " FROM Casa_Objetos_De_Consumo_tb"
																  + " WHERE Id_Casa = " + idCasa);
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				CasaObjetosConsumo casaObjetosConsumo = new CasaObjetosConsumo();

				casaObjetosConsumo.setIdCasa(resultSet.getInt(1));
				casaObjetosConsumo.setIdObjetoConsumo(resultSet.getInt(2));
				
				casaObjetosConsumoLista.add(casaObjetosConsumo);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel buscar associaçao da casa e dos objetos de consumo");
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

		if(casaObjetosConsumoLista.size() == 0)
			return null;

		return casaObjetosConsumoLista;
	}
	
	public void remove(CasaObjetosConsumo casaObjetosConsumo)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("DELETE FROM Casa_Objetos_De_Consumo_tb WHERE Id_Casa = " + casaObjetosConsumo.getIdCasa());

			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel remover associaçao entre casa e objetos");
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
	
	public List<CasaObjetosConsumo> recupera()
	{
		List<CasaObjetosConsumo> casaObjetosConsumoLista = new LinkedList<CasaObjetosConsumo>();
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/waterApp", "root", "");

			stmt = (PreparedStatement)connection.prepareStatement("SELECT Id_Casa, Id_Objeto_Consumo"
																  + " FROM Casa_Objetos_De_Consumo_tb");
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				CasaObjetosConsumo casaObjetosConsumo = new CasaObjetosConsumo();

				casaObjetosConsumo.setIdCasa(resultSet.getInt(1));
				casaObjetosConsumo.setIdObjetoConsumo(resultSet.getInt(2));

				casaObjetosConsumoLista.add(casaObjetosConsumo);
			}
		}
		catch(SQLException e)
		{
			System.out.println("║ Impossivel recuperar associaçao entre casa e objetos");
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

		return casaObjetosConsumoLista;
	}
}
