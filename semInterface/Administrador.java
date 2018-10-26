import java.util.*;

public class Administrador extends Usuario{

  Administrador(Usuario u){
    super(u.getNome(), u.getSobrenome(), u.getMatricula(), u.getSenha());
  }
  Administrador(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }
  Vector<Materia> getMaterias(){
    return null;
  }
  public String toString(){
    return "Administrador "+this.getNomeCompleto() + " de matricula "+this.getMatricula();
  }
}
