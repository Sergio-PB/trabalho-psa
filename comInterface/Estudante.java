import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    this.notas.put(materia, new Vector<Integer>());
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

  void area() throws IOException{
    boolean logado = true;
    while(logado){
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("\nOlá, "+this.getNome()+", digite o numero correspondente a acao desejada:");
  		System.out.println("1. Acessar minhas matérias e notas");
  		System.out.println("2. Fazer um pedido de matrícula em uma matéria");
      System.out.println("3. Sair da plataforma");
  		switch(input.readLine()){
  		case "1": System.out.println("Aqui estão suas matérias cadastradas e respectivas notas:");
        System.out.println("--------------------");
        if(!notas.isEmpty()){
          notas.forEach((m,n) -> System.out.println(m + "-" + n));
        }else{
          System.out.println("Você não está matriculado em nenhuma matéria ainda.");
        }
        System.out.println("--------------------");
  		break;
  		case "2":
        System.out.println("Selecione uma matéria para matrícula:");
        System.out.println("--------------------");
        int count = 0;
        for(Materia m : Administrador.listMaterias()){
          System.out.println(count + ". "+m);
          count++;
        }
        System.out.println("--------------------");
        try{
          Materia matEscolhida = Administrador.listMaterias().get(Integer.parseInt(input.readLine()));
          System.out.println(matEscolhida);
          System.out.println(matEscolhida.getAlunos());
          if(matEscolhida.getAlunos().contains(this)){
            System.out.println("Você já está cadastrado nessa matéria.");
          }else{
            fazPedido(matEscolhida);
            System.out.println("Pedido de matrícula para "+matEscolhida.getNome() + " feito.");
          }
        }catch (java.lang.ArrayIndexOutOfBoundsException e) {
          System.out.println(e);
          System.out.println("Opção inválida.");
        }catch (Exception e) {
          System.out.println(e);
          System.out.println("Entrada inválida.");
        }

  		break;
      case "3": System.out.println("\nObrigado, bons estudos.\n");
        logado = false;
  		break;
  		default:  System.out.println("Essa opção não é válida...");
  		break;
  	  }
    }
  }
}
