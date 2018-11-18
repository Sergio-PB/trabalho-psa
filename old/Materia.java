import java.util.*;

public class Materia{
  private String nome;
  private String codigo;
  private Professor professor;
  private Vector<Estudante> alunos;

  Materia(String nome, String codigo, Professor professor){
    this.nome = nome;
    this.codigo = codigo;
    this.professor = professor;
  }

  Materia(String nome, String codigo){
    this(nome, codigo, null);
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

  void setProfessor(Professor professor){
    this.professor = professor;
  }

  Vector<Estudante> getAlunos(){
    return this.alunos;
  }

  void addEstudante(Estudante aluno){
    this.alunos.add(aluno);
  }

  public String toString(){
    return "Materia "+this.nome+" de código "+this.codigo+", ministrada por"+this.professor;
  }
}
