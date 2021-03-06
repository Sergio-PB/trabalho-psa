import java.util.Vector;

public class Chefe extends Administrador{
  private Vector<Materia> listaDeMaterias = new Vector<Materia>();
  private static Vector<Pedido> pedidos = new Vector<Pedido>();

  Chefe(Usuario u){
    super(u);
  }

  Chefe(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }

  Vector<Materia> getMaterias(){
    return this.listaDeMaterias;
  }

  void cadastrarMateria(String nome, String codigo, Professor professor){
    Materia materia = new Materia(nome, codigo, professor);
    this.listaDeMaterias.add(materia);
  }

  static void novoPedido(Pedido pedido){
    Chefe.pedidos.add(pedido);
  }

  void aprovarPedido(Pedido pedido){
    Estudante estudante = pedido.getEstudante();
    Materia materia = pedido.getMateria();
    estudante.addMateria(materia);
    System.out.println("O estudante "+estudante+" agora está cadastrado na materia "+estudante.getMaterias().get(estudante.getMaterias().indexOf(materia))+".");
  }

  void definirProfessor(Professor p, Materia m){
    m.setProfessor(p);
  }

  public String toString(){
    return "Chefe de Dept. "+this.getNomeCompleto() + " de matricula "+this.getMatricula();
  }
}
