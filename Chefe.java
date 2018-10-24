public static class Chefe{
  private final static String NOME;
  private static Vector<Materia> listaDeMaterias;
  private static Vector<Pedido> pedidos;

  Chefe(String nome, String sobrenome, int matricula, String senha){
    Chefe(new Usuario(nome, sobrenome, matricula, senha));
  }

  Chefe(Usuario u){
    super(u);
    this.NOME = u.getNome();
  }

  Vector<Materia> getMaterias(){
    return Chefe.listaDeMaterias;
  }

  void cadastrarMateria(String nome, String codigo, Professor professor){
    Materia materia = new Materia(nome, codigo, professor);
    Chefe.listaDeMaterias.add(materia);
  }

  void novoPedido(Pedido pedido){
    Chefe.pedidos.add(pedido);
  }

  void aprovarPedido(Pedido pedido){
    Estudante estudante = pedido.getEstudante();
    Materia materia = pedido.getMateria();
    estudante.addMateria(materia);
    System.out.println("O estudante "+estudante+" agora está cadastrado na materia "+estudante.getMaterias().get(materia)+".");
  }
}
