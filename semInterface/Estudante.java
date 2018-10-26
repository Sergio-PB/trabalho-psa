import java.util.*;

public class Estudante extends Usuario{
  private Vector<Materia> materias;
  private HashMap<Materia, Vector<Integer>> notas;

  Estudante(String nome, String sobrenome, int matricula, String senha){
    super(nome, sobrenome, matricula, senha);
    this.materias = new Vector<Materia>();
    this.notas = new HashMap<Materia, Vector<Integer>>();
  }

  Estudante(Usuario u){
    this(u.getNome(), u.getSobrenome(), u.getMatricula(), u.getSenha());
  }

  Vector<Materia> getMaterias(){
    return this.materias;
  }

  void addMateria(Materia materia){
    this.materias.add(materia);
  }

  HashMap<Materia, Vector<Integer>> getNotas(){
    return this.notas;
  }

  Vector<Integer> getNotas(Materia materia){
    return this.notas.get(materia);
  }

  void setNota(Materia materia, Vector<Integer> notas){
    this.notas.replace(materia, notas);
  }

  void fazPedido(Materia materia){
    Pedido p = new Pedido(this, materia);
    Chefe.novoPedido(p);
  }

  public String toString(){
    return "Estudante "+this.getNomeCompleto()+" de matrícula "+this.getMatricula();
  }
}
