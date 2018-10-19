import java.util.*;

public class Professor extends Usuario{
  private int matricula;
  private int senha;
  private Vector<Materia> materias;

  Professor(Usuario p, int matricula, int senha){
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

  void cadastrarMateria(Materia materia){
    this.materias.add(materia);
  }

  public String toString(){
    return "Professor "+this.getNomeCompleto()+" de matrícula "+this.getMatricula();
  }
}
