public class Tesoureiro extends Administrador{
  Tesoureiro(Usuario u){
    super(u);
  }
  Tesoureiro(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }
  void definirSalario(Professor professor, int salario){
    professor.setSalario(salario);
  }
}
