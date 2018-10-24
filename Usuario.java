public class Usuario{
  private String nome;
  private String sobrenome;
  private int idade;
  private int cpf;

  Usuario(String nome, String sobrenome, int idade, int cpf){
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.idade = idade;
    this.cpf = cpf;
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
  int getIdade(){
    return this.idade;
  }
  int getCpf(){
    return this.cpf;
  }

  public String toString(){
    return "Usuário "+this.getNomeCompleto() + " de "+this.idade+" anos, e CPF:"+this.cpf;
  }
}
