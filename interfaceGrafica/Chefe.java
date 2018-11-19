import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Chefe extends Administrador{
  private static Vector<Pedido> pedidos = new Vector<Pedido>();

  Chefe(Usuario u){
    super(u);
  }

  Chefe(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }

  void cadastrarMateria(String nome, String codigo, Professor professor){
    Materia materia = new Materia(nome, codigo, professor);
    Administrador.addMateria(materia);
    professor.cadastrarMateria(materia);
  }

  void definirProfessor(Professor p, Materia m){
    m.setProfessor(p);
  }

  static Vector<Pedido> getPedidos(){
    return Chefe.pedidos;
  }

  static void novoPedido(Pedido pedido){
    Chefe.pedidos.add(pedido);
  }

  public void aprovarPedido(Pedido pedido){
    System.out.println("Aprovando");
    Estudante estudante = pedido.getEstudante();
    Materia materia = pedido.getMateria();
    estudante.addMateria(materia);
    Chefe.pedidos.remove(pedido);
    materia.addEstudante(estudante);
    System.out.println("O estudante "+estudante+" agora está cadastrado na materia "+estudante.getMaterias().get(estudante.getMaterias().indexOf(materia))+".");
  }

  public String toString(){
    return "Chefe de Dept. "+this.getNomeCompleto() + " de matricula "+this.getMatricula();
  }

  void area() throws IOException{
    boolean logado = true;
    Professor p;
    Materia matEscolhida;
    while(logado){
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("\nOlá Chefe "+this.getNome()+", digite o numero correspondente a acao desejada:");
      System.out.println("1. Cadastrar nova matéria");
      System.out.println("2. Definir um professor em uma matéria existente");
  		System.out.println("3. Aprovar um pedido de matrícula em uma matéria");
      System.out.println("4. Sair da plataforma");

      switch(input.readLine()){
  		case "1":
        System.out.println("Digite os dados da nova matéria no seguinte formato, sem os parênteses:");
        System.out.println("(nome) (código) (primeiro nome do professor)");
        System.out.println("e.g. FundamentosDaInformacao DAS5122 Jomi");
        String[] prop = input.readLine().split(" ");
        p = null;
				for(Usuario u : T3.usuarios.values()){
					if(u instanceof Professor && u.getNome().equals(prop[2])){
						p = (Professor) u;
						break;
					}
				}
				if(p != null){
					cadastrarMateria(prop[0], prop[1], p);
          System.out.println("Materia cadastrada com sucesso!");
          System.out.println(Administrador.listMaterias());
				}else{
					System.out.println("Professor para materia "+prop[0]+" não foi encontrado, criando matéria sem professor.");
					cadastrarMateria(prop[0], prop[1], null);
				}
  		break;
  		case "2":
        System.out.println("Aqui está a lista de matérias:");
        System.out.println("--------------------");
        int mcount = 0;
        for(Materia m : Administrador.listMaterias()){
          System.out.println(mcount+". "+m);
          mcount++;
        }
        System.out.println("--------------------");
        System.out.println("\nEscreva o primeiro nome do professor e o número correspondente à matéria.");
        System.out.println("e.g. Jomi 2");
        p = null;
        String[] read = input.readLine().split(" ");
        matEscolhida = Administrador.listMaterias().get(Integer.parseInt(read[1]));
				for(Usuario u : T3.usuarios.values()){
					if(u instanceof Professor && u.getNome().equals(read[0])){
						p = (Professor) u;
            //newProf = new Professor("ok", "ok", 1, "ok");
						break;
					}
				}
				if(p != null){
					definirProfessor(p, matEscolhida);
          System.out.println("\n"+matEscolhida);
				}else{
					System.out.println("\nProfessor não encontrado.");
				}
  		break;
      case "3":
        if(!Chefe.pedidos.isEmpty()){
          System.out.println("Aqui está a lista de pedidos de matrícula:");
          System.out.println("--------------------");
          int pcount = 0;
          for(Pedido ped : Chefe.pedidos){
            System.out.println(pcount+". "+ped);
            pcount++;
          }
          System.out.println("--------------------");
          System.out.println("\nInsira o número correspondente ao pedido para aprová-lo");
          try {
            Pedido pedEscolhida = Chefe.pedidos.get(Integer.parseInt(input.readLine()));
            aprovarPedido(pedEscolhida);
          }catch (Exception e) {
            System.out.println(e);
            System.out.println("\nEntrada inválida.");
          }
        }else{
          System.out.println("Não há pedidos de matrícula!");
          System.out.println("--------------------");
        }
  		break;
      case "4": System.out.println("\nObrigado.\n");
        logado = false;
  		break;
  		default:  System.out.println("Essa opção não é válida...");
  		break;
  	  }
    }
  }
}
