import java.util.*;

public class Estudante extends Usuario{
  private int matricula;
  private int senha;
  private Vector<Materia> materias;
  private Vector<Integer> notas;

  Estudante(Usuario p, int matricula, int senha){
    super(p.getNome(), p.getSobrenome(), p.getIdade(), p.getCpf());
    this.matricula = matricula;
    this.senha = senha;
  }

  int getMatricula(){
    return this.matricula;
  }

  Vector<Materia> getMaterias(){
    return this.materias;
  }

  Vector<Integer> getNotas(){
    return this.notas;
  }

  void cadastrarMateria(Materia materia){
    this.cadastrarMateria(materia, 0);
  }

  void cadastrarMateria(Materia materia, int nota){
    this.materias.add(materia);
    this.notas.add(nota);
    System.out.println("Nova matéria " + materia + " adicionada com sucesso!");
    System.out.println("A nota dessa matéria é "+nota);
  }

  void setNota(Materia materia, int nota){
    this.notas.set(this.materias.indexOf(materia), nota);
    System.out.println("A nota de "+materia+" agora é "+nota);
  }

  public String toString(){
    return "Estudante "+this.getNomeCompleto()+" de matrícula "+this.matricula;
  }
}
