import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tesoureiro extends Administrador{
  private static Vector<Professor> listaProfessores = new Vector<Professor>();

  Tesoureiro(Usuario u){
    super(u);
  }
  Tesoureiro(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }
  void definirSalario(Professor professor, int salario){
    professor.setSalario(salario);
  }

  static void addProfessor(Professor professor){
    Tesoureiro.listaProfessores.add(professor);

  }

  static Vector<Professor> getProfessores(){
    return Tesoureiro.listaProfessores;
  }

  public String toString(){
    return "Tesoureiro "+this.getNomeCompleto() + " de matricula "+this.getMatricula();
  }

  void area() throws IOException{
    boolean logado = true;
    while(logado){
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("\nOlá Tesoureiro "+this.getNome()+", aqui estão os professor do departamento, o número de matérias ministradas e seus salários:");
      System.out.println("\nDigite o número respectivo ao professor para mudar seu salário, e o valor do novo salário:");
      System.out.println("e.g. 0 8900 -> professor 0 recebe agora R$8900,00");
      System.out.println("ps. apenas valores inteiros");
      System.out.println("--------------------");
      for(int i =0; i<Tesoureiro.listaProfessores.size(); i++){
        Professor prof = Tesoureiro.listaProfessores.get(i);
        System.out.println(i+". Professor "+prof.getNomeCompleto() + " ministra "+prof.getMaterias().size()+" matérias, e recebe R$"+prof.getSalario()+",00");
      }
      System.out.println("--------------------");
      String[] in = input.readLine().split(" ");
      try{
        Professor p = Tesoureiro.listaProfessores.get(Integer.parseInt(in[0]));
        int valor = Integer.parseInt(in[1]);
        p.setSalario(valor);
        System.out.println("Salário de "+p.getNome()+" alterado para R$"+valor+",00");
      }catch (Exception e) {
        System.out.println("Entrada inválida.");
      }
    }
  }
}
