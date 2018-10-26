import java.util.Vector;
import java.io.IOException;

public class Usuario{
  private String nome;
  private String sobrenome;
  private int matricula;
  private String senha;

  Vector getMaterias(){return null;}
  void area() throws IOException{};

  Usuario(String nome, String sobrenome, int matricula, String senha){
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.matricula = matricula;
    this.senha = senha;
  }

  String getNome(){
    return this.nome;
  }
  String getSobrenome(){
    return this.sobrenome;
  }
  String getNomeCompleto(){
    return this.nome +" "+ this.sobrenome;
  }
  int getMatricula(){
    return this.matricula;
  }
  String getSenha(){
    return this.senha;
  }

  public String toString(){
    return "Usuário "+this.getNomeCompleto() + " de matricula "+this.matricula;
  }
}
