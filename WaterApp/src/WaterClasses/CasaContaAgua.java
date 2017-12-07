package WaterClasses;

public class CasaContaAgua
{
	private int idCasa;
    private int idConta;
	public int getIdCasa() {
		return idCasa;
	}
	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
    
    public String toString()
	{
		return String.format("Associaçao entre Casa e Conta\nId Casa: %s\nId Conta: %s\n",
						idCasa, idConta);
	}
}
