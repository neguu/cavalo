package WaterClasses;

public class PessoaAtividadeAdiaria
{
	private int idPessoa;
    private int idAtividade;
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public int getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}
    
	public String toString()
	{
		return String.format("Associaçao entre Pessoa e Atividade\nId Pessoa: %s\nId Atividade: %s\n",
			idPessoa, idAtividade);
	}
}
