import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class T2{
	static HashMap<Integer, Usuario> usuarios;

	public static void main(String[] args) throws IOException{
		cadastra();
		System.out.println("\nBem-vindo ao Magister");
	}

	static void cadastra() throws IOException{
		usuarios = new HashMap<Integer, Usuario>(); // matricula, obj

		File file = new File("./cadastros.txt");
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String line = fr.readLine();
		while(line != null){
			String[] prop = line.split(" ");
			if(prop[0].equals("estudante")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Estudante(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0].equals("professor")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Professor(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0].equals("chefe")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Chefe(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}else if(prop[0].equals("tesoureiro")){
				usuarios.putIfAbsent(Integer.parseInt(prop[3]), new Tesoureiro(prop[1], prop[2], Integer.parseInt(prop[3]), prop[4]));
			}
			System.out.println(usuarios.get(Integer.parseInt(prop[3])));
			line = fr.readLine();
		}
	}
}
