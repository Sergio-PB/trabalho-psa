import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class T2{
	BufferedReader input;
	HashMap<String, Usuario> usuarios;

	public void main(String[] args) throws IOException{
		File file = new File("./cadastros.txt");
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String line;
		while(line != null){
			line = fr.readLine();
			if(line == "estudante"){
				//Usuario user = new Usuario();
			}

		}

		input = new BufferedReader(new InputStreamReader(System.in));

		usuarios = new HashMap<String, Usuario>(); // matricula, obj
		// map.put("0", "admin");

		System.out.println("Bem-vindo ao Magister");

		Usuario usuario = null;
		while(usuario == null){usuario = login();}
		area(usuario);
	}

	Usuario login() throws IOException{
		Usuario user = null;
		System.out.println("Por favor insira sua matrícula: ");
		String matr = input.readLine();
		if(usuarios.containsKey(matr)){
			System.out.println("Por favor insira sua senha: ");
			String senha = input.readLine();
			if(usuarios.get(matr).getSenha() == senha){
				user =  usuarios.get(matr);
			}
		}else{
			System.out.println("Essa matrícula não está cadastrada.");
		}
		return user;

	}

	void area(Estudante usuario) throws IOException{
		System.out.println("Olá, "+usuario.getNome()+", digite o numero correspondente a acao desejada:");
		System.out.println("1. Acessar minhas matérias e notas");
		System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		switch(input.readLine()){
		case "1": System.out.println("Aqui estão suas matérias cadastradas e respectivas notas:");
		break;
		case "2": System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		break;
		default:  System.out.println("Essa opção não é válida...");
		break;
		}
	}

	void area(Professor usuario) throws IOException{
		System.out.println("Olá, "+usuario.getNome()+", aqui estão suas matérias cadastradas, digite o número correspondente à materia para ver suas opções:");
		System.out.println("Digite um número correspondente a ação desejada:");
		System.out.println("1. Acessar minhas matérias");
		System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		switch(input.readLine()){
		case "1": System.out.println("Aqui estão suas matérias cadastradas e respectivas notas:");
		break;
		case "2": System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		break;
		default:  System.out.println("Essa opção não é válida...");
		break;
		}
	}

	void area(Administrador usuario) throws IOException{
		System.out.println("Olá, "+usuario.getNome()+", digite o número correspondente a ação desejada:");
		System.out.println("1. Acessar minhas matérias e notas");
		System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		switch(input.readLine()){
		case "1": System.out.println("Aqui estão suas matérias cadastradas e respectivas notas:");
		break;
		case "2": System.out.println("2. Fazer um pedido de matrícula em uma matéria");
		break;
		default:  System.out.println("Essa opção não é válida...");
		break;
		}
	}

}
