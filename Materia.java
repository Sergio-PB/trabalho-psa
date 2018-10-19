import java.util.*;

public class Materia{
  private String nome;
  private String codigo;
  private Professor professor;
  private Vector<Estudante> alunos;
  private HashMap<Estudante, Vector<Double>> notas;

  Materia(String nome, String codigo){
    this.nome = nome;
    this.codigo = codigo;
  }

  Materia(String nome, String codigo, Professor professor){
    this.nome = nome;
    this.codigo = codigo;
    this.professor = professor;
  }

  String getNome(){
    return this.nome;
  }

  String getCodigo(){
    return this.codigo;
  }

  Professor getProfessor(){
    return this.professor;
  }

  Vector<Estudante> getAlunos(){
    return this.alunos;
  }
  void setNome(String nome){
    this.nome = nome;
  }

  void setCodigo(String codigo){
    this.codigo = codigo;
  }

  void cadastrarProfessor(Professor professor){
    this.professor = professor;
    professor.cadastrarMateria(this);
    System.out.println("O professor da matéria "+this.nome+" agora é "+professor.getNomeCompleto()+".");
  }

  void cadastrarEstudante(Estudante estudante){
    this.alunos.add(estudante);
    estudante.cadastrarMateria(this);
    System.out.println("O aluno "+estudante.getNomeCompleto()+" foi cadastrado com sucesso.");
  }

  public String toString(){
    return "Materia "+this.nome+" de código "+this.codigo+", ministrada por"+this.professor;
  }
}
