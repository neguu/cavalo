package WaterClasses;

public class CasaPessoa
{
	private int idCasa;
    private int idPessoa;
	public int getIdCasa() {
		return idCasa;
	}
	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
    
	public String toString()
	{
		return String.format("Associaçao entre Casa e Pessoa\nId Casa: %s\nId Pessoa: %s\n", idCasa, idPessoa);
	}
}
