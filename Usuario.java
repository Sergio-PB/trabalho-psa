public class Usuario{
  private String nome;
  private String sobrenome;
  private int matricula;
  private String senha;

  Usuario(String nome, String sobrenome, int matricula, String senha){
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.matricula = matricula;
    this.senha = senha;

    abstract Vector<Materia> getMaterias();
  }

  String getNome(){
    return this.nome;
  }
  String getSobrenome(){
    return this.sobrenome;
  }
  String getNomeCompleto(){
    return this.nome + this.sobrenome;
  }
  int getMatricula(){
    return this.matricula;
  }
  int getSenha(){
    return this.senha;
  }

  public String toString(){
    return "Usuário "+this.getNomeCompleto() + " de "+this.idade+" anos, e CPF:"+this.cpf;
  }
}
