package App;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

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

public class Recupera
{
	public static void exibeNaTela()
	{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioCasaDAO usuarioCasaDAO = new UsuarioCasaDAO();
		CasaDAO casaDAO = new CasaDAO();
		CasaPessoaDAO casaPessoaDAO = new CasaPessoaDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaAtividadeDiariaDAO pessoaAtividadeDiariaDAO = new PessoaAtividadeDiariaDAO();
		AtividadeDiariaDAO atividadeDiariaDAO = new AtividadeDiariaDAO();
		CasaObjetosDeConsumoDAO casaObjetosDeConsumoDAO = new CasaObjetosDeConsumoDAO();
		ObjetosConsumoDAO objetosConsumoDAO = new ObjetosConsumoDAO();
		CasaContaAguaDAO casaContaAguaDAO = new CasaContaAguaDAO();
		ContaAguaDAO contaAguaDAO = new ContaAguaDAO();
		
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║            Exibiçao dos dados na tela            ║");
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║                 Dados de Usuarios                ║");
		System.out.println("╠══════════════════════════════════════════════════╝");
	
		int pagina = 1;
		for(Usuario usuario : usuarioDAO.recupera())
		{
			System.out.println("║ Id do Usuario            ║ " + usuario.getIdUsuario());
			System.out.println("║ Nome do Usuario                     ║ " + usuario.getNome());
			System.out.println("║ Email do Usuario                    ║ "+ usuario.getEmail());
			System.out.println("║ Senha do Usuario                    ║ " + usuario.getSenha());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║    Dados de Associaçoes Entre Usuarios e Casas   ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(UsuarioCasa usuarioCasa : usuarioCasaDAO.recupera())
		{
			System.out.println("║ Id do Usuario              ║ " + usuarioCasa.getIdUsuario());
			System.out.println("║ Id da Casa                 ║ " + usuarioCasa.getIdCasa());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║                   Dados das Casas                ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(Casa casa : casaDAO.recupera())
		{
			System.out.println("║ Id da Casa                 ║ " + casa.getIdCasa());
			System.out.println("║ Rua                        ║ " + casa.getRua());
			System.out.println("║ Bairro                     ║ " + casa.getBairro());
			System.out.println("║ Cidade                     ║ " + casa.getCidade());
			System.out.println("║ Estado                     ║ " + casa.getEstado());
			System.out.println("║ Tipo de Casa               ║ " + casa.getTipoCasa());
			System.out.println("║ Numero                     ║ " + casa.getNumero());
			System.out.println("║ Quantidade de Banheiros    ║ " + casa.getQuantidadeBanheiros());
			System.out.println("║ Quantidade de Pessoas      ║ " + casa.getQuantidadePessoas());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║    Dados de Associaçoes Entre Casas e Pessoas    ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(CasaPessoa casaPessoa : casaPessoaDAO.recupera())
		{
			System.out.println("║ Id da Casa                 ║ " + casaPessoa.getIdCasa());
			System.out.println("║ Id da Pessoa               ║ " + casaPessoa.getIdPessoa());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║                 Dados das Pessoas                ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(Pessoa pessoa : pessoaDAO.recupera())
		{
			System.out.println("║ Id do Pessoa               ║ " + pessoa.getIdPessoa());
			System.out.println("║ Tipo de Pessoa             ║ " + pessoa.getTipoPessoa());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║ Dados de Associaçoes Entre Atividades E Pessoas  ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(PessoaAtividadeAdiaria pessoaAtividadeDiaria : pessoaAtividadeDiariaDAO.recupera())
		{
			System.out.println("║ Id da Pessoa               ║ " + pessoaAtividadeDiaria.getIdPessoa());
			System.out.println("║ Id da Atividade Diaria     ║ " + pessoaAtividadeDiaria.getIdAtividade());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣ ");
		System.out.println("║            Dados das Atividades Diarias          ║ ");
		System.out.println("╠══════════════════════════════════════════════════╝ ");

		for(AtividadeDiaria atividadeDiaria : atividadeDiariaDAO.recupera())
		{
			System.out.println("║ Id da Atividade            ║ " + atividadeDiaria.getIdAtividade());
			System.out.println("║ Tipo de Atividade          ║ " + atividadeDiaria.getTipoAtividade());
			System.out.println("║ Tempo                      ║ " + atividadeDiaria.getTempo());
			System.out.println("║ Quantidade de Atividades   ║ " + atividadeDiaria.getContadorAtividade());
			System.out.println("║ Quantidade de Agua         ║ " + atividadeDiaria.getQuantidadeAgua());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║    Dados de Associaçoes Entre Casas e Objetos    ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(CasaObjetosConsumo casaObjetosDeConsumo : casaObjetosDeConsumoDAO.recupera())
		{
			System.out.println("║ Id da Casa                 ║ " + casaObjetosDeConsumo.getIdCasa());
			System.out.println("║ Id do Objeto               ║ " + casaObjetosDeConsumo.getIdObjetoConsumo());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║           Dados dos Objetos de Consumo           ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(ObjetosConsumo objetosConsumo : objetosConsumoDAO.recupera())
		{
			System.out.println("║ Id do Objeto               ║ " + objetosConsumo.getIdObjetoConsumo());
			System.out.println("║ Nome do Objeto             ║ " + objetosConsumo.getNomeObjeto());
			System.out.println("║ Quantidade de Agua         ║ " + objetosConsumo.getQuantidadeAgua());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║    Dados de Associaçoes Entre Casas e Contas     ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(CasaContaAgua casaContaAgua : casaContaAguaDAO.recupera())
		{
			System.out.println("║ Id da Casa                 ║ " + casaContaAgua.getIdCasa());
			System.out.println("║ Id da Conta                ║ " + casaContaAgua.getIdConta());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════Pressione enter para continuar lendo════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		
		System.out.println("╠══════════════════════════════════════════════════╣");
		System.out.println("║              Dados das Contas Aguas              ║");
		System.out.println("╠══════════════════════════════════════════════════╝");

		for(ContaAgua contaAgua : contaAguaDAO.recupera())
		{
			System.out.println("║ Id da Conta                ║ " + contaAgua.getIdConta());
			System.out.println("║ Quantidade de Agua Mensal  ║ " + contaAgua.getQuantidadeAguaMensal());
			System.out.println("║ Quantidade de Consumo      ║ " + contaAgua.getQuantidadeConsumo());
			
			if((pagina % 10) == 0)
			{
				System.out.print("╠═════════════════ Pressione enter para continuar lendo ════════════");
				new Scanner(System.in).nextLine();
			}
			pagina += 1;
		}
		System.out.println("╠════════════════════════════╝");
		
	}
	
	public static void arquivar(String diretorioNome)
	{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioCasaDAO usuarioCasaDAO = new UsuarioCasaDAO();
		CasaDAO casaDAO = new CasaDAO();
		CasaPessoaDAO casaPessoaDAO = new CasaPessoaDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaAtividadeDiariaDAO pessoaAtividadeDiariaDAO = new PessoaAtividadeDiariaDAO();
		AtividadeDiariaDAO atividadeDiariaDAO = new AtividadeDiariaDAO();
		CasaObjetosDeConsumoDAO casaObjetosDeConsumoDAO = new CasaObjetosDeConsumoDAO();
		ObjetosConsumoDAO objetosConsumoDAO = new ObjetosConsumoDAO();
		CasaContaAguaDAO casaContaAguaDAO = new CasaContaAguaDAO();
		ContaAguaDAO contaAguaDAO = new ContaAguaDAO();
		
		try
		{
			OutputStream os = new FileOutputStream(diretorioNome);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			for(Usuario usuario : usuarioDAO.recupera())
			{
				bw.write(usuario.toString());
			}

			for(UsuarioCasa usuarioCasa : usuarioCasaDAO.recupera())
			{
				bw.write(usuarioCasa.toString());
			}
			
			for(Casa casa : casaDAO.recupera())
			{
				bw.write(casa.toString());
			}
			
			for(CasaPessoa casaPessoa : casaPessoaDAO.recupera())
			{
				bw.write(casaPessoa.toString());
			}
			
			for(Pessoa pessoa : pessoaDAO.recupera())
			{
				bw.write(pessoa.toString());
			}
			
			for(PessoaAtividadeAdiaria pessoaAtividadeDiaria : pessoaAtividadeDiariaDAO.recupera())
			{
				bw.write(pessoaAtividadeDiaria.toString());
			}

			for(AtividadeDiaria atividadeDiaria : atividadeDiariaDAO.recupera())
			{
				bw.write(atividadeDiaria.toString());
			}
			
			for(CasaObjetosConsumo casaObjetosDeConsumo : casaObjetosDeConsumoDAO.recupera())
			{
				bw.write(casaObjetosDeConsumo.toString());
			}

			for(ObjetosConsumo objetosConsumo : objetosConsumoDAO.recupera())
			{
				bw.write(objetosConsumo.toString());
			}

			for(CasaContaAgua casaContaAgua : casaContaAguaDAO.recupera())
			{
				bw.write(casaContaAgua.toString());
			}

			for(ContaAgua contaAgua : contaAguaDAO.recupera())
			{
				bw.write(contaAgua.toString());
			}
			
			bw.close();
			osw.close();
			os.close();
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e )
		{
			
		}
	}
}
