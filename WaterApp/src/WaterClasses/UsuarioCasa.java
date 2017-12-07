package WaterClasses;

public class UsuarioCasa
{
	private int idCasa;
    private int idUsuario;
	public int getIdCasa() {
		return idCasa;
	}
	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
    
    public String toString()
	{
		return String.format("Associaçao Usuario e Casa\nId Usuario: %s\nId Casa: %s\n", idUsuario, idCasa);
	}
}
