package App;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Menu
{
	public static void menu()
	{
		while(true)
		{
			System.out.println("╔════════════════════════════╗");
			System.out.println("║            Menu            ║");
			System.out.println("╠════════════════════════════╣");
			System.out.println("║   1 - Cadastrar Usuário    ║");
			System.out.println("║   2 - Buscar Usuario       ║");
			System.out.println("║   3 - Recuperar Dados      ║");
			System.out.println("║   4 - Remover Usuário      ║");
			System.out.println("║   5 - Sair                 ║");
			System.out.println("╠════════════════════════════╝");
		
			Scanner scan = new Scanner(System.in);
		
			System.out.print("║ Digite sua escolha: ");
			int escolha = scan.nextInt();
		
			switch(escolha)
			{
				case 1:
					subMenuInsercao();
					break;
				case 2:
					subMenuBusca();
					break;
				case 3:
					subRecuperar();
					break;
				case 4:
					remove();
					break;
				default:
					System.out.println("║ Encerrando...");
					System.exit(1);
					break;
			}
		
			System.out.println("╚════════════════════════════╝");
		}
	}
	
	private static void subMenuInsercao()
	{
		System.out.println("╠════════════════════════════╗");
		System.out.println("║      Sub-Menu Inserção     ║");
		System.out.println("╠════════════════════════════╣");
		System.out.println("║   1 - Manualmente          ║");
		System.out.println("║   2 - Arquivamente         ║");
		System.out.println("║   3 - Voltar               ║");
		System.out.println("║   4 - Sair                 ║");
		System.out.println("╠════════════════════════════╝");

		Scanner scan = new Scanner(System.in);

		System.out.print("║ Digite sua escolha: ");
		int escolha = scan.nextInt();

		switch(escolha)
		{
			case 1:
				CadastrarUsuario cadastro = new CadastrarUsuario();
				cadastro.registrar();
				break;
			case 2:
				CadastrarUsuario cadastroAutomatico = new CadastrarUsuario();
				cadastroAutomatico.Automatico();
				break;
			case 3:
				return;
			default:
				System.out.println("║ Encerrando...");
				System.exit(1);
				break;
		}
	}
	
	private static void subMenuBusca()
	{
		System.out.println("╠════════════════════════════╗");
		System.out.println("║           Busca            ║");
		System.out.println("╠════════════════════════════╝");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("║ Digite seu email: ");
		String email = scan.nextLine();
		
		System.out.print("║ Digite sua senha: ");
		String senha = scan.nextLine();
		
		Busca busca = new Busca();
		busca.busca(email, senha);
		
		busca.exibeEncontrados();
	}
	
	private static void subRecuperar()
	{
		System.out.println("╠════════════════════════════╗");
		System.out.println("║         Recuperaçao        ║");
		System.out.println("╠════════════════════════════╣");
		System.out.println("║   1 - Exibir na Tela       ║");
		System.out.println("║   2 - Arquivar             ║");
		System.out.println("║   3 - Voltar               ║");
		System.out.println("║   4 - Sair                 ║");
		System.out.println("╠════════════════════════════╝");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite sua escolha: ");
		int escolha = scan.nextInt();
		
		switch(escolha)
		{
			case 1:
				Recupera.exibeNaTela();
				break;
			case 2:
				Scanner diretorio = new Scanner(System.in);
				System.out.print("Digite o diretorio e o nome do arquivo para onde salvar: ");
				String diretorioNome = diretorio.nextLine();
				
				Recupera.arquivar(diretorioNome);
				break;
			case 3:
				return;
			default:
				System.out.println("║ Encerrando...");
				System.exit(1);
				break;
		}
	}
	
	public static void remove()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("║ Digite seu email: ");
		String email = scan.nextLine();
		
		System.out.print("║ Digite sua senha: ");
		String senha = scan.nextLine();
		
		Remove.remover(email, senha);
	}
}
