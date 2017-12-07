package App;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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

public class CadastrarUsuario
{
	private Usuario usuario;
	private Casa casa;
	private AtividadeDiaria atividade;
	private ContaAgua conta;
	private ObjetosConsumo objeto;
	private Pessoa pessoa;
	
	public void registrar()
	{
		boolean status = true;
		
		System.out.println("╔════════════════════════════╗");
		System.out.println("║    Cadastrando Usuario     ║");
		System.out.println("╠════════════════════════════╝");
		while(status)
		{
			Scanner scan = new Scanner(System.in);
			try
			{
				System.out.print("║ Digite seu nome: ");
				String nome = scan.nextLine();
			
				System.out.print("║ Digite seu Email: ");
				String email = scan.nextLine();
			
				System.out.print("║ Digite sua senha: ");
				String senha = scan.nextLine();
		
				usuario = new Usuario();
				
				usuario.setNome(nome);
				usuario.setSenha(senha);
				usuario.setEmail(email);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.adciona(usuario);
			
				status = false;
			}
			catch(MySQLIntegrityConstraintViolationException e)
			{
				System.out.println("Usuario já existe");
				System.out.println("Tente novamente\n");
			}
		}
		
		System.out.println("╠════════════════════════════╗");
		System.out.println("║Cadastrando Casa do Usuario ║");
		System.out.println("╠════════════════════════════╝");
		
		List <Casa> casas = new LinkedList<Casa>();
		List <UsuarioCasa> casasUsuarios = new LinkedList<UsuarioCasa>();
		List<ObjetosConsumo> objetos = new LinkedList<ObjetosConsumo>();
		List<CasaObjetosConsumo> objetosCasa = new LinkedList<CasaObjetosConsumo>();
		
		status = true;
		while(status)
		{
			Scanner scan = new Scanner(System.in);
			try
			{
				System.out.print("║ Digite sua rua: ");
				String rua = scan.nextLine();
				
				System.out.print("║ Digite seu bairro: ");
				String bairro = scan.nextLine();
				
				System.out.print("║ Digite sua cidade: ");
				String cidade = scan.nextLine();
				
				System.out.print("║ Digite seu estado: ");
				String estado = scan.nextLine();
				
				System.out.print("║ Digite o tipo de sua casa: ");
				String tipoCasa = scan.nextLine();
				
				System.out.print("║ Digite o numero de sua casa: ");
				int numero = scan.nextInt();
				
				System.out.print("║ Digite a quantidade de banheiros: ");
				int quantidadeBanheiros = scan.nextInt();
				
				System.out.print("║ Digite a quantidade de pessoas: ");
				int quantidadePessoas = scan.nextInt();

				casa = new Casa();
				casa.setRua(rua);
				casa.setBairro(bairro);
				casa.setCidade(cidade);
				casa.setEstado(estado);
				casa.setTipoCasa(tipoCasa);
				casa.setNumero(numero);
				casa.setQuantidadeBanheiros(quantidadeBanheiros);
				casa.setQuantidadePessoas(quantidadePessoas);
				
				CasaDAO casaDAO = new CasaDAO();
				casaDAO.adciona(casa);
			
				UsuarioCasa usuarioCasa = new UsuarioCasa();
				usuarioCasa.setIdCasa(casa.getIdCasa());
				usuarioCasa.setIdUsuario(usuario.getIdUsuario());
				
				casas.add(casa);
				casasUsuarios.add(usuarioCasa);
				
				UsuarioCasaDAO usuarioCasaDAO = new UsuarioCasaDAO();
				usuarioCasaDAO.adciona(usuarioCasa);
				
				
				System.out.println("╠══════════════════════════════╗");
				System.out.println("║Cadastrando Objetos de Consumo║");
				System.out.println("╠══════════════════════════════╝");
				
				boolean statusObjetosConsumo = true;
				while(statusObjetosConsumo)
				{
					scan = new Scanner(System.in);
					try
					{
						System.out.print("║ Digite o nome do objeto: ");
						String nomeObjeto = scan.nextLine();
						
						System.out.print("║ Digite a quantidade de agua gasta pelo objeto: ");
						double quantidadeAgua = scan.nextDouble();

						objeto = new ObjetosConsumo();
						objeto.setNomeObjeto(nomeObjeto);
						objeto.setQuantidadeAgua(quantidadeAgua);
						
						ObjetosConsumoDAO objetoDAO = new ObjetosConsumoDAO();
						objetoDAO.adciona(objeto);
						
						objetos.add(objeto);
						
						CasaObjetosConsumo coc = new CasaObjetosConsumo();
						coc.setIdCasa(casa.getIdCasa());
						coc.setIdObjetoConsumo(objeto.getIdObjetoConsumo());
						
						objetosCasa.add(coc);
						
						CasaObjetosDeConsumoDAO cocDAO = new CasaObjetosDeConsumoDAO();
						cocDAO.adciona(coc);
						
						String escolha = "s";
						Scanner inputEscolha = new Scanner(System.in);
						System.out.print("║ Deseja inserir outro objeto(s/n): ");
						escolha = inputEscolha.nextLine();
						if(!escolha.equals("s"))
							statusObjetosConsumo = false;
					}
					catch(MySQLIntegrityConstraintViolationException e)
					{
						System.out.println("║ Erro ao Cadastrar o Objeto de Consumo!");
						System.out.println("║ Tente novamente\n");
					}
				}
				
				String escolha = "s";
				Scanner inputEscolha = new Scanner(System.in);
				System.out.print("║ Deseja inserir outra casa(s/n): ");
				escolha = inputEscolha.nextLine();
				if(!escolha.equals("s"))
					status = false;
			}
			catch(MySQLIntegrityConstraintViolationException e)
			{
				System.out.println("║ Erro ao cadastrar! Casa já existe!");
				System.out.println("║ Tente novamente\n");
			}
		}
		
		System.out.println("╠══════════════════════════════╗");
		System.out.println("║       Cadastrando Conta      ║");
		System.out.println("╠══════════════════════════════╝");
		
		status = true;
		while(status)
		{
			Scanner scan = new Scanner(System.in);
			try
			{
				System.out.print("║ Digite a quantidade de agua mensal: ");
				double quantidadeAguaMensal = scan.nextDouble();

				conta = new ContaAgua();
				conta.setQuantidadeAguaMensal(quantidadeAguaMensal);
				
				ContaAguaDAO contaDAO = new ContaAguaDAO();
				contaDAO.adciona(conta);
				
				status = false;
			}
			catch(MySQLIntegrityConstraintViolationException e)
			{
				System.out.println("║ Erro ao cadastrar a Conta");
				System.out.println("║ Tente novamente\n");
			}
		}
		
		List<CasaContaAgua> casaContaAgua = new LinkedList<CasaContaAgua>();
		List<CasaContaAguaDAO> casaContaAguaDAO = new LinkedList<CasaContaAguaDAO>();
		
		for(Casa casa1 : casas)
		{
			CasaContaAgua cca = new CasaContaAgua();
			cca.setIdCasa(casa1.getIdCasa());
			cca.setIdConta(conta.getIdConta());
			
			casaContaAgua.add(cca);
			
			CasaContaAguaDAO ccaDAO = new CasaContaAguaDAO();
			
			ccaDAO.adciona(cca);
			
			casaContaAguaDAO.add(ccaDAO);
		}
		
		System.out.println("╠══════════════════════════════╗");
		System.out.println("║      Cadastrando Pessoa      ║");
		System.out.println("╠══════════════════════════════╝");
		
		List<Pessoa> pessoas = new LinkedList<Pessoa>();
		List<AtividadeDiaria> atividades = new LinkedList<AtividadeDiaria>();
		List<PessoaAtividadeAdiaria> pessoasAtividades = new LinkedList<PessoaAtividadeAdiaria>();
		
		for(Casa c : casas)
		{
		for(int qPessoas = 0; qPessoas < c.getQuantidadePessoas(); qPessoas++)
		{
			Scanner scan = new Scanner(System.in);
			try
			{
				System.out.print("║ Digite o tipo de pessoa: ");
				String tipoPessoa = scan.nextLine();

				pessoa = new Pessoa();
				pessoa.setTipoPessoa(tipoPessoa);
				
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.adciona(pessoa);
				
				pessoas.add(pessoa);
				
				System.out.println("╠══════════════════════════════╗");
				System.out.println("║Cadastrando Atividades diarias║");
				System.out.println("╠══════════════════════════════╝");

				boolean statusAtividade = true;
				while(statusAtividade)
				{
					scan = new Scanner(System.in);
					try
					{
						System.out.print("║ Digite uma atividade diaria: ");
						String tipoAtividade = scan.nextLine();

						System.out.print("║ Digite o tempo da atividade: ");
						String tempo = scan.nextLine();

						System.out.print("║ Digite quantas vezes esta atividade é realizada por dia: ");
						int contadorAtividade = scan.nextInt();

						System.out.print("║ Digite a quantidade de agua necessária: ");
						double quantidadeAgua = scan.nextDouble();

						atividade = new AtividadeDiaria();
						atividade.setTipoAtividade(tipoAtividade);
						atividade.setTempo(tempo);
						atividade.setContadorAtividade(contadorAtividade);
						atividade.setQuantidadeAgua(quantidadeAgua);

						AtividadeDiariaDAO atividadeDAO = new AtividadeDiariaDAO();
						atividadeDAO.adciona(atividade);

						atividades.add(atividade);
						
						PessoaAtividadeAdiaria psa = new PessoaAtividadeAdiaria();
						psa.setIdAtividade(atividade.getIdAtividade());
						psa.setIdPessoa(pessoa.getIdPessoa());
						
						PessoaAtividadeDiariaDAO pessoaAtividadeDAO = new PessoaAtividadeDiariaDAO();
						pessoaAtividadeDAO.adciona(psa);
						
						pessoasAtividades.add(psa);
						
						String escolha = "s";
						Scanner inputEscolha = new Scanner(System.in);
						System.out.print("║ Deseja inserir outra Atividade(s/n): ");
						escolha = inputEscolha.nextLine();
						if(!escolha.equals("s"))
							statusAtividade = false;
						
					}
					catch(MySQLIntegrityConstraintViolationException e)
					{
						System.out.println("║ Erro! Atividade já foi registrada");
						System.out.println("║ Tente novamente\n");
					}
				}
			}
			
			catch(MySQLIntegrityConstraintViolationException e)
			{
				System.out.println("║ Erro! Pessoa ja foi cadastrada");
				System.out.println("║ Tente novamente\n");
			}
		}
		
		List<CasaPessoa> casaPessoa = new LinkedList<CasaPessoa>();
		for(int x = 0; x < casas.size(); x++)
		{
			for(int y = 0; y < pessoas.size(); y++)
			{
				CasaPessoa cp = new CasaPessoa();
				cp.setIdCasa(casas.get(x).getIdCasa());
				cp.setIdPessoa(pessoas.get(y).getIdPessoa());
				
				casaPessoa.add(cp);
				CasaPessoaDAO cpDAO = new CasaPessoaDAO();
				cpDAO.adciona(cp);
			}
		}
		}
		System.out.println("╠════════════════════════════");
		
		System.out.println("║ Completando cadastro...");
		

		System.out.println("║ Cadastro concluido.");

	}
	
	public void Automatico()
	{
		boolean usuariosNaoAdcionados = false;
		try
		{
			Scanner scan = new Scanner(System.in);
			
			System.out.print("║ Digite o nome do Arquivo a ser analisado: ");
			String nomeArquivo = scan.nextLine();
			
			InputStream is = new FileInputStream(nomeArquivo);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String primeiraLinha = br.readLine();
			
			while(br != null)
			{
				try
				{
					usuario = new Usuario();
					usuario.setNome(primeiraLinha);
					usuario.setSenha(br.readLine());
					usuario.setEmail(br.readLine());

					casa = new Casa();
					casa.setRua(br.readLine());
					casa.setBairro(br.readLine());
					casa.setCidade(br.readLine());
					casa.setEstado(br.readLine());
					casa.setTipoCasa(br.readLine());
					
					int valor = Integer.parseInt(br.readLine());
					casa.setNumero(valor);
					valor = Integer.parseInt(br.readLine());
					casa.setQuantidadeBanheiros(valor);
					valor = Integer.parseInt(br.readLine());
					casa.setQuantidadePessoas(valor);
				
					objeto = new ObjetosConsumo();
					objeto.setNomeObjeto(br.readLine());
					objeto.setQuantidadeAgua(Double.parseDouble(br.readLine()));
				
					conta = new ContaAgua();
					conta.setQuantidadeAguaMensal(Double.parseDouble(br.readLine()));
				
					HashMap<Pessoa, AtividadeDiaria> pessoasAtividades = new HashMap<Pessoa, AtividadeDiaria>();
				
					for(int p = 0; p < casa.getQuantidadePessoas(); p++)
					{
						pessoa = new Pessoa();
						pessoa.setTipoPessoa(br.readLine());
					
						atividade = new AtividadeDiaria();
						atividade.setTipoAtividade(br.readLine());
						atividade.setTempo(br.readLine());
						atividade.setContadorAtividade(Integer.parseInt(br.readLine()));
						atividade.setQuantidadeAgua(Double.parseDouble(br.readLine()));

						pessoasAtividades.put(pessoa, atividade);
					}
					
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					usuarioDAO.adciona(usuario);
			
					CasaDAO casaDAO = new CasaDAO();
					casaDAO.adciona(casa);
		
					UsuarioCasa usuarioCasa = new UsuarioCasa();
					usuarioCasa.setIdCasa(casa.getIdCasa());
					usuarioCasa.setIdUsuario(usuario.getIdUsuario());
			
					UsuarioCasaDAO usuarioCasaDAO = new UsuarioCasaDAO();
					usuarioCasaDAO.adciona(usuarioCasa);

					ObjetosConsumoDAO objetoDAO = new ObjetosConsumoDAO();
					objetoDAO.adciona(objeto);

					CasaObjetosConsumo coc = new CasaObjetosConsumo();
					coc.setIdCasa(casa.getIdCasa());
					coc.setIdObjetoConsumo(objeto.getIdObjetoConsumo());
			
					CasaObjetosDeConsumoDAO cocDAO = new CasaObjetosDeConsumoDAO();
					cocDAO.adciona(coc);

					ContaAguaDAO contaDAO = new ContaAguaDAO();
					contaDAO.adciona(conta);
		
					CasaContaAgua cca = new CasaContaAgua();
					cca.setIdCasa(casa.getIdCasa());
					cca.setIdConta(conta.getIdConta());

					CasaContaAguaDAO ccaDAO = new CasaContaAguaDAO();
					ccaDAO.adciona(cca);
					
					List<PessoaAtividadeAdiaria> pessoasAtividadesDiarias = new LinkedList<PessoaAtividadeAdiaria>();
					for(Pessoa pessoa1: pessoasAtividades.keySet())
					{
						PessoaAtividadeAdiaria pessoaAtividadeDiaria = new PessoaAtividadeAdiaria();
						
						pessoaAtividadeDiaria.setIdAtividade(pessoasAtividades.get(pessoa1).getIdAtividade());
						pessoaAtividadeDiaria.setIdPessoa(pessoa1.getIdPessoa());
						
						pessoasAtividadesDiarias.add(pessoaAtividadeDiaria);
						
						PessoaAtividadeDiariaDAO pessoaAtividadeDiariaDAO = new PessoaAtividadeDiariaDAO();
						
						pessoaAtividadeDiariaDAO.adciona(pessoaAtividadeDiaria);
					}
				}
				catch(NullPointerException e)
				{
					usuariosNaoAdcionados = true;		
				}
				catch(MySQLIntegrityConstraintViolationException e)
				{
					usuariosNaoAdcionados = true;
				}
			}
		}
		catch(IOException e)
		{
			usuariosNaoAdcionados = true;
		}
		
		if(usuariosNaoAdcionados == true)
			System.out.println("║ Alguns usuarios nao puderam ser adcionados");
			
		System.out.println("║ Cadastro concluido!\n");
	}
}
