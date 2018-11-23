import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class T3{
	static HashMap<Integer, Usuario> usuarios;
	static JanelaPrincipal janelaLogin;

	public static void main(String[] args) throws IOException{
		cadastra();
		System.out.println("\nBem-vindo ao Magister");
		janelaLogin = new JanelaPrincipal(usuarios.get(1234570));
	}

	static void cadastra() throws IOException{
		usuarios = new HashMap<Integer, Usuario>(); // matricula, obj
		Chefe chefe = null;

		File file = new File("./cadastros.txt");
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String line = fr.readLine();
		while(line != null){
			String[] prop = line.split(" ");
			if(prop[0].equals("estudante")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Estudante(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
				System.out.println(usuarios.get(Integer.parseInt(prop[3]))+" cadastrado.");
			}else if(prop[0].equals("professor")){
				Professor professor = new Professor(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]);
				Tesoureiro.addProfessor(professor);
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), professor);
				System.out.println(usuarios.get(Integer.parseInt(prop[3]))+" cadastrado.");
			}else if(prop[0].equals("chefe")){
				chefe = new Chefe(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]);
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), chefe);
				System.out.println(usuarios.get(Integer.parseInt(prop[3]))+" cadastrado.");
			}else if(prop[0].equals("tesoureiro")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Tesoureiro(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
				System.out.println(usuarios.get(Integer.parseInt(prop[3]))+" cadastrado.");
			}else if(prop[0].equals("materia")){
				// eu sei que o primeiro usuario eh o Chefe
				Professor p = null;
				for(Usuario u : usuarios.values()){
					if(u instanceof Professor && u.getNome().equals(prop[3])){
						p = (Professor) u;
						break;
					}
				}
				if(p != null){
					chefe.cadastrarMateria(prop[1], prop[2], p);
					System.out.println("Nova matéria "+prop[1]+" - "+prop[2]+" cadastrada com o professor "+p);
				}else{
					System.out.println("Professor para materia "+prop[1]+" não foi encontrado, criando matéria sem professor.");
					chefe.cadastrarMateria(prop[1], prop[2], null);
				}

			}
			line = fr.readLine();
		}
	}

	// static Usuario login() throws IOException{
	// 	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	// 	Usuario user = null;
	// 	System.out.println("Por favor insira sua matrícula: ");
	// 	int matr = Integer.parseInt(input.readLine());
	// 	if(usuarios.containsKey(matr)){
	// 		System.out.println("Por favor insira sua senha: ");
	// 		String senha = input.readLine();
	// 		if(usuarios.get(matr).getSenha().equals(senha)){
	// 			user =  usuarios.get(matr);
	// 		}else{
	// 			System.out.println("Essa senha está incorreta.");
	// 		}
	// 	}else{
	// 		System.out.println("Essa matrícula não está cadastrada.");
	// 	}
	// 	return user;
	//
	// }
}
