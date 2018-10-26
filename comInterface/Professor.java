import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Professor extends Usuario{
  private Vector<Materia> materias;
  private int salario;

  Professor(Usuario u){
    super(u.getNome(), u.getSobrenome(), u.getMatricula(), u.getSenha());
    this.materias = new Vector<Materia>();
    this.salario = 8000;
  }

  Professor(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }

  Vector<Materia> getMaterias(){
    return this.materias;
  }

  void cadastrarMateria(Materia materia){
    this.materias.add(materia);
  }

  Integer getSalario(){
    return this.salario;
  }

  void setSalario(Integer salario){
    this.salario = salario;
  }

  void setNota(Materia materia, Estudante aluno, Vector<Integer> nota){
    aluno.setNota(materia, nota);
    System.out.println("O aluno "+aluno.getNome()+" tem notas "+aluno.getNotas(materia)+" na materia "+materia+" agora.");
  }

  public String toString(){
    return "Professor "+this.getNomeCompleto()+" de matrícula "+this.getMatricula();
  }

  void area() throws IOException{
    boolean logado = true;
    while(logado){
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("\nOlá Professor "+this.getNome()+", aqui estão suas matérias cadastradas, digite o número correspondente à materia para ver suas opções:");
      try{
        for(int i =0; i<this.materias.size(); i++){
          System.out.println(i+". "+this.materias.get(i).getCodigo() + " - "+this.materias.get(i).getNome());
        }
        System.out.println(this.materias.size()+". Sair da plataforma");
        int in = Integer.parseInt(input.readLine());
        if(in == this.materias.size()){
          break;
        }
    		Materia materia = this.materias.get(in);
        System.out.println("--------------------");
        System.out.println(materia);
        System.out.println("Alunos e notas: ");
        try{
          int i = 0;
          for(Estudante aluno : materia.getAlunos()){
            String notasFormatadas = "";
            int p = 1;
            for(int n : aluno.getNotas(materia)){
              notasFormatadas += ("P" + p + ": " + n + "  ");
              p++;
            }
            System.out.println(i+". "+aluno.getNomeCompleto()+": "+aluno.getNotas(materia));
            i++;
          }
          System.out.println("Digite o número correspondente ao aluno, e o conjunto de notas separados por espaço");
          System.out.println("e.g. 0 7 6 8 -> aluno 0 recebe [7,6,8] nas notas");
          Vector<String> props= new Vector<String>(Arrays.asList(input.readLine().split(" ")));
          in = Integer.parseInt(props.get(0));
          props.remove(0);
          Vector<Integer> notas = new Vector<Integer>();
          for(String nota : props){
            notas.add(Integer.parseInt(nota));
          }
          materia.getAlunos().get(in).setNota(materia, notas);
        }catch (Exception e) {
          System.out.println(e);
          System.out.println("Não há alunos cadastrados nessa matéria ainda.");
        }

        System.out.println("--------------------");
      }catch (java.lang.ArrayIndexOutOfBoundsException e) {
        System.out.println(e);
        System.out.println("\nFavor selecione uma opcção válida.");
      }catch (Exception e) {
        System.out.println(e);
        System.out.println("\nVocê ainda não tem matérias cadastradas, contate o chefe do departamento.");
      }
    }
  }
}
