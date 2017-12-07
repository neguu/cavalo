package WaterClasses;

public class CasaObjetosConsumo
{
	private int idCasa;
	private int idObjetoConsumo;
	public int getIdCasa() {
		return idCasa;
	}
	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}
	public int getIdObjetoConsumo() {
		return idObjetoConsumo;
	}
	public void setIdObjetoConsumo(int idObjetoConsumo) {
		this.idObjetoConsumo = idObjetoConsumo;
	}
	
	public String toString()
	{
		return String.format("Associaçao entre Casa e Objetos\nId Casa: %s\nIs Objeto: %s\n",
					idCasa, idObjetoConsumo);
	}
}
