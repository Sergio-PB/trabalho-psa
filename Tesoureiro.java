public class Tesoureiro extends Administrador{
  Tesoureiro(Usuario u){
    super(u);
  }
  Tesoureiro(String nome, String sobrenome, int matricula, String senha){
    Tesoureiro(new Usuario(nome, sobrenome, matricula, senha));
  }
  void definirSalario(Professor professor){
    professor.salario = salario;
  }
}
