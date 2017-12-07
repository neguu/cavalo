package App;

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
import WaterClasses.CasaObjetosConsumo;
import WaterClasses.CasaPessoa;
import WaterClasses.ObjetosConsumo;
import WaterClasses.Pessoa;
import WaterClasses.PessoaAtividadeAdiaria;
import WaterClasses.UsuarioCasa;

public class Remove
{
	public static void remover(String email, String senha)
	{
		Busca busca = new Busca();
		busca.busca(email, senha);
		
		try
		{
			for(PessoaAtividadeAdiaria pa : busca.getPessoasAtividadesDiariasLista())
			{
				new PessoaAtividadeDiariaDAO().remove(pa);
			}
		
			for(CasaPessoa cp : busca.getCasaPessoaLista())
			{
				new CasaPessoaDAO().remove(cp);
			}
		
			for(AtividadeDiaria atividade : busca.getAtividadeDiariaLista())
			{
				new AtividadeDiariaDAO().remove(atividade);
			}
		
			for(Pessoa pessoa : busca.getPessoaLista())
			{
				new PessoaDAO().remove(pessoa);
			}
		
			for(CasaObjetosConsumo casaObjeto : busca.getCasaObjetosConsumoLista())
			{
				new CasaObjetosDeConsumoDAO().remove(casaObjeto);
			}
		
			for(ObjetosConsumo objeto : busca.getObjetosConsumoLista())
			{
				new ObjetosConsumoDAO().remove(objeto);
			}
		
			new CasaContaAguaDAO().remove(busca.getCasaContaAgua());
		
			new ContaAguaDAO().remove(busca.getContaAgua());
		
			for(UsuarioCasa usuarioCasa : busca.getUsuarioCasaLista())
			{
				new UsuarioCasaDAO().remove(usuarioCasa);
			}
		
			for(Casa casa : busca.getCasaLista())
			{
				new CasaDAO().remove(casa);
			}
		
			new UsuarioDAO().remove(busca.getUsuario());
		}
		catch(NullPointerException e)
		{
			System.out.println("Não foi possivel remover os dados com exito.");
		}
	}	
}
