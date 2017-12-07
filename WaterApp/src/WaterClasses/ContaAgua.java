package WaterClasses;

public class ContaAgua
{
	private double quantidadeAguaMensal;
	private double quantidadeConsumo;
	private int idConta;
	
	public double getQuantidadeAguaMensal()
	{
		return quantidadeAguaMensal;
	}
	public void setQuantidadeAguaMensal(double quantidadeAguaMensal)
	{
		this.quantidadeAguaMensal = quantidadeAguaMensal;
	}
	public double getQuantidadeConsumo()
	{
		return quantidadeConsumo;
	}
	public void setQuantidadeConsumo(double quantidadeConsumo)
	{
		this.quantidadeConsumo = quantidadeConsumo;
	}
	public int getIdConta()
	{
		return idConta;
	}
	public void setIdConta(int idConta)
	{
		this.idConta = idConta;
	}
	
	public String toString()
	{
		return String.format("Conta Agua\nId: %s\nN° Agua Mensal: %s\nConsumidos: %s\n",
							 idConta, quantidadeAguaMensal, quantidadeConsumo);
	}
}
