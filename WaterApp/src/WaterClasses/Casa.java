package WaterClasses;

public class Casa
{
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String tipoCasa;
	private int numero;
	private int quantidadeBanheiros;
	private int quantidadePessoas;
	private int idCasa;
	
	public String getRua()
	{
		return rua;
	}
	public void setRua(String rua)
	{
		this.rua = rua;
	}
	public String getBairro()
	{
		return bairro;
	}
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}
	public String getCidade()
	{
		return cidade;
	}
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}
	public String getEstado()
	{
		return estado;
	}
	public void setEstado(String estado)
	{
		this.estado = estado;
	}
	public String getTipoCasa()
	{
		return tipoCasa;
	}
	public void setTipoCasa(String tipoCasa)
	{
		this.tipoCasa = tipoCasa;
	}
	public int getNumero()
	{
		return numero;
	}
	public void setNumero(int numero)
	{
		this.numero = numero;
	}
	public int getQuantidadeBanheiros()
	{
		return quantidadeBanheiros;
	}
	public void setQuantidadeBanheiros(int quantidadeBanheiros)
	{
		this.quantidadeBanheiros = quantidadeBanheiros;
	}
	public int getQuantidadePessoas()
	{
		return quantidadePessoas;
	}
	public void setQuantidadePessoas(int quantidadePessoas)
	{
		this.quantidadePessoas = quantidadePessoas;
	}
	public int getIdCasa()
	{
		return idCasa;
	}
	public void setIdCasa(int idCasa)
	{
		this.idCasa = idCasa;
	}
	
	public String toString()
	{
		return String.format("Casa\nId: %s\nRua: %s\nBairro: %s\nCidade: %s\nEstado: %s\nTipo: %s\nNumero: %s\nN° Pessoas: %s\nN° Banheiros: %s\n",
			idCasa, rua, bairro, cidade, estado, tipoCasa,
			numero, quantidadeBanheiros, quantidadePessoas);
	}
}
