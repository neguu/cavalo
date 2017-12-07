package WaterClasses;

public class Pessoa
{
	private String tipoPessoa;
	private int idPessoa;
	
	public String getTipoPessoa()
	{
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa)
	{
		this.tipoPessoa = tipoPessoa;
	}
	public int getIdPessoa()
	{
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa)
	{
		this.idPessoa = idPessoa;
	}
	
	public String toString()
	{
		return String.format("Pessoas\nId: %s\nTipo: %s\n", idPessoa, tipoPessoa);
	}
}
