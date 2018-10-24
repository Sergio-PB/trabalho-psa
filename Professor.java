import java.util.*;

public class Professor extends Usuario{
  private Vector<Materia> materias;
  private int salario;

  Professor(Usuario u){
    super(u.getNome(), u.getSobrenome(), u.getMatricula(), u.getSenha());
    this.materias = new Vector<Materia>;
    this.salario = null;
  }

  Professor(String nome, String sobrenome, int matricula, String senha){
    Professor(new Usuario(nome, sobrenome, matricula, senha));
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
    materia.getAlunos().get(aluno).setNota(materia, nota);
    System.out.println("O aluno "+aluno.getNome()+" tem notas "+aluno.getNota(materia)+" na materia "+materia+" agora.");
  }

  public String toString(){
    return "Professor "+this.getNomeCompleto()+" de matrícula "+this.getMatricula();
  }
}
