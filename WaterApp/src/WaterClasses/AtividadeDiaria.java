package WaterClasses;

public class AtividadeDiaria
{
	private String tipoAtividade;
	private String tempo;
	private int contadorAtividade;
	private double quantidadeAgua;
	private int idAtividade;
	
	public String getTipoAtividade()
	{
		return tipoAtividade;
	}
	public void setTipoAtividade(String tipoAtividade)
	{
		this.tipoAtividade = tipoAtividade;
	}
	public String getTempo()
	{
		return tempo;
	}
	public void setTempo(String tempo)
	{
		this.tempo = tempo;
	}
	public int getContadorAtividade()
	{
		return contadorAtividade;
	}
	public void setContadorAtividade(int contadorAtividade)
	{
		this.contadorAtividade = contadorAtividade;
	}
	public double getQuantidadeAgua()
	{
		return quantidadeAgua;
	}
	public void setQuantidadeAgua(double quantidadeAgua)
	{
		this.quantidadeAgua = quantidadeAgua;
	}
	public int getIdAtividade()
	{
		return idAtividade;
	}
	public void setIdAtividade(int idAtividade)
	{
		this.idAtividade = idAtividade;
	}
	
	public String toString()
	{
		return String.format("Atividades Diarias\nId: %s\nTipo: %s\nTempo: %s\nQuantidade de Atividades: %s\nQuantidade de Agua: %s\n", 
					idAtividade, tipoAtividade, tempo,
					contadorAtividade, quantidadeAgua);
	}
}
