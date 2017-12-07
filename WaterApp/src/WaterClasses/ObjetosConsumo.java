package WaterClasses;

public class ObjetosConsumo
{
	private String nomeObjeto;
	private double quantidadeAgua;
	private int idObjetoConsumo;
	
	public String getNomeObjeto()
	{
		return nomeObjeto;
	}
	public void setNomeObjeto(String nomeObjeto)
	{
		this.nomeObjeto = nomeObjeto;
	}
	public double getQuantidadeAgua()
	{
		return quantidadeAgua;
	}
	public void setQuantidadeAgua(double quantidadeAgua)
	{
		this.quantidadeAgua = quantidadeAgua;
	}
	public int getIdObjetoConsumo()
	{
		return idObjetoConsumo;
	}
	public void setIdObjetoConsumo(int idObjetoConsumo)
	{
		this.idObjetoConsumo = idObjetoConsumo;
	}
	
	public String toString()
	{
		return String.format("Objetos de Consumo\nId: %s\nNome: %s\nN° Agua: %s\n",
							 idObjetoConsumo, nomeObjeto, quantidadeAgua);
	}
}
