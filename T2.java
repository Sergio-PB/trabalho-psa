import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class T2{
	//BufferedReader input;
	HashMap<Integer, Usuario> usuarios;

	public static void main(String[] args) throws IOException{
		static usuarios = new HashMap<Integer, Usuario>(); // matricula, obj

		File file = new File("./cadastros.txt");
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String line = "ok";
		while(line != null){
			line = fr.readLine();
			String[] prop = line.split(" ");
			if(prop[0] == "estudante"){
				usuarios.put(Integer.parseInt(prop[3]), new Estudante(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0] == "professor"){
				usuarios.put(Integer.parseInt(prop[3]), new Professor(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0] == "chefe"){
				usuarios.put(Integer.parseInt(prop[3]), new Chefe(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0] == "administrador"){
				usuarios.put(Integer.parseInt(prop[3]), new Administrador(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}
		}

		System.out.println(usuarios);

		//input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Bem-vindo ao Magister");
		/*
		Usuario usuario = null;
		while(usuario == null){usuario = login();}
		area(usuario);*/
	}
/*
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
*/
}
