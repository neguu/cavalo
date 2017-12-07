package App;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import ClassesDAO.AtividadeDiariaDAO;
import ClassesDAO.CasaContaAguaDAO;
import ClassesDAO.CasaDAO;
import ClassesDAO.CasaObjetosDeConsumoDAO;
import ClassesDAO.CasaPessoaDAO;
import ClassesDAO.ContaAguaDAO;
import ClassesDAO.ObjetosConsumoDAO;
import ClassesDAO.PessoaAtividadeDiariaDAO;
import ClassesDAO.PessoaDAO;
import ClassesDAO.UsuarioCasaDAO;
import ClassesDAO.UsuarioDAO;
import WaterClasses.AtividadeDiaria;
import WaterClasses.Casa;
import WaterClasses.CasaContaAgua;
import WaterClasses.CasaObjetosConsumo;
import WaterClasses.CasaPessoa;
import WaterClasses.ContaAgua;
import WaterClasses.ObjetosConsumo;
import WaterClasses.Pessoa;
import WaterClasses.PessoaAtividadeAdiaria;
import WaterClasses.Usuario;
import WaterClasses.UsuarioCasa;

public class Busca
{
	private Usuario usuario;
	private List<UsuarioCasa> usuarioCasaLista;
	private List<Casa> casaLista;
	private List<CasaPessoa> casaPessoaLista;
	private List<Pessoa> pessoaLista;
	private List<PessoaAtividadeAdiaria> pessoasAtividadesDiariasLista;
	private List<AtividadeDiaria> atividadeDiariaLista;
	private List<CasaObjetosConsumo> casaObjetosConsumoLista;
	private List<ObjetosConsumo> objetosConsumoLista;
	private CasaContaAgua casaContaAgua;
	private ContaAgua contaAgua;
	
	public void busca(String email, String senha)
	{
		try
		{
			usuario = new UsuarioDAO().busca(email, Criptografia.criptografar(senha));
			usuarioCasaLista = new UsuarioCasaDAO().busca(usuario.getIdUsuario());
		
			casaLista = new LinkedList<Casa>();
			
			for(UsuarioCasa usuarioCasa : usuarioCasaLista)
			{
				casaLista.add(new CasaDAO().busca(usuarioCasa.getIdCasa()));
			}
		
			casaPessoaLista = new LinkedList<CasaPessoa>();
		
			for(Casa casa : casaLista)
			{
				for(CasaPessoa casaPessoa : new CasaPessoaDAO().busca(casa.getIdCasa()))
				{
					casaPessoaLista.add(casaPessoa);
				}
			}
		
			pessoaLista = new LinkedList<Pessoa>();
		
			for(CasaPessoa casaPessoa : casaPessoaLista)
			{
				pessoaLista.add(new PessoaDAO().busca(casaPessoa.getIdPessoa()));
			}
		
			pessoasAtividadesDiariasLista = new LinkedList<PessoaAtividadeAdiaria>();
		
			for(Pessoa pessoa : pessoaLista)
			{
				for(PessoaAtividadeAdiaria atividades : new PessoaAtividadeDiariaDAO().busca(pessoa.getIdPessoa()))
				{
					pessoasAtividadesDiariasLista.add(atividades);	
				}
			}
		
			atividadeDiariaLista = new LinkedList<AtividadeDiaria>();
			for(PessoaAtividadeAdiaria atividades : pessoasAtividadesDiariasLista)
			{
				atividadeDiariaLista.add(new AtividadeDiariaDAO().busca(atividades.getIdAtividade()));
			}
		
			casaObjetosConsumoLista = new LinkedList<CasaObjetosConsumo>();
			for(Casa casa : casaLista)
			{
				for(CasaObjetosConsumo casaObjetoConsumo : new CasaObjetosDeConsumoDAO().busca(casa.getIdCasa()))
				{
					casaObjetosConsumoLista.add(casaObjetoConsumo);
				}
			}
		
			objetosConsumoLista = new LinkedList<ObjetosConsumo>();
			for(CasaObjetosConsumo casaObjetosConsumo : casaObjetosConsumoLista)
			{
				objetosConsumoLista.add(new ObjetosConsumoDAO().busca(casaObjetosConsumo.getIdObjetoConsumo()));
			}
		
			casaContaAgua = new CasaContaAguaDAO().busca(casaLista.get(0).getIdCasa());
		
			contaAgua = new ContaAguaDAO().busca(casaContaAgua.getIdConta());
		}
		catch(NullPointerException e)
		{
			System.out.println("A pesquisa não teve resultados");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<UsuarioCasa> getUsuarioCasaLista() {
		return usuarioCasaLista;
	}

	public List<Casa> getCasaLista() {
		return casaLista;
	}

	public List<CasaPessoa> getCasaPessoaLista() {
		return casaPessoaLista;
	}

	public List<Pessoa> getPessoaLista() {
		return pessoaLista;
	}

	public List<PessoaAtividadeAdiaria> getPessoasAtividadesDiariasLista() {
		return pessoasAtividadesDiariasLista;
	}

	public List<AtividadeDiaria> getAtividadeDiariaLista() {
		return atividadeDiariaLista;
	}

	public List<CasaObjetosConsumo> getCasaObjetosConsumoLista() {
		return casaObjetosConsumoLista;
	}

	public List<ObjetosConsumo> getObjetosConsumoLista() {
		return objetosConsumoLista;
	}

	public CasaContaAgua getCasaContaAgua() {
		return casaContaAgua;
	}

	public ContaAgua getContaAgua() {
		return contaAgua;
	}
	
	public void exibeEncontrados()
	{
		try
		{
			System.out.println(usuario);
		
			for(Casa casa : casaLista)
				System.out.println(casa);
			for(Pessoa pessoa : pessoaLista)
				System.out.println(pessoa);
			for(AtividadeDiaria atividade : atividadeDiariaLista)
				System.out.println(atividade);
			for(ObjetosConsumo objeto : objetosConsumoLista)
				System.out.println(objeto);
			System.out.println(contaAgua);
		}
		catch(NullPointerException e)
		{
			
		}
	}
}
