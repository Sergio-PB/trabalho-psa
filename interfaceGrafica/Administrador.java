import java.util.*;

public class Administrador extends Usuario{
  private static Vector<Materia> listaDeMaterias = new Vector<Materia>();

  Administrador(Usuario u){
    super(u.getNome(), u.getSobrenome(), u.getMatricula(), u.getSenha());
  }
  Administrador(String nome, String sobrenome, int matricula, String senha){
    this(new Usuario(nome, sobrenome, matricula, senha));
  }
  static Vector<Materia> listMaterias(){
    return Administrador.listaDeMaterias;
  }
  static void addMateria(Materia m){
    Administrador.listaDeMaterias.add(m);
  }
  public String toString(){
    return "Administrador "+this.getNomeCompleto() + " de matricula "+this.getMatricula();
  }
}
