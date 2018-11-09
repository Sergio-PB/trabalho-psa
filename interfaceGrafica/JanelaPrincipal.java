import java.awt.*;
import java.util.Vector;

public class JanelaPrincipal extends Frame{
  private Label infoUsuario;
  private Button botaoSair;
  private GridLayout gl;

  // Aluno
  private Button acessarMateriasNotas;
  private TextArea materiasNotas;
  private Button fazerPedido;
  private Button confirmarPedido;
  private List listaPedidos;

  // Professor
  private Panel listaMaterias;
    //Vector<Button, Materia> botoesMaterias;
  private Panel textAlunosNotas;

  // Chefe

  // Tesoureiro

  public JanelaPrincipal(Usuario user){
    this.setTitle("Area de " + user.getNomeCompleto());
    infoUsuario = new Label(user.toString());
    this.add(infoUsuario);

    // user.configuraJanela(this);
    if(user instanceof Estudante){
      configEstudante((Estudante) user);
    } else if(user instanceof Professor){
      configProfessor((Professor) user);
    } else if(user instanceof Chefe){
      configChefe((Chefe) user);
    } else if(user instanceof Tesoureiro){
      configTesoureiro((Tesoureiro) user);
    }

    botaoSair = new Button("SAIR");
    this.add(botaoSair);
    this.pack();
    this.setVisible(true);
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 800);
  }

  public void configEstudante(Estudante u){

  }

  public void configProfessor(Professor u){
    // this.gl = new GridLayout(1, 1);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.VERTICAL;
    this.setLayout(new GridBagLayout());
    listaMaterias = new Panel(new GridBagLayout());
    int py = 0;
    for(Materia m: u.getMaterias()){
      System.out.println("add button");
      listaMaterias.add(new Button(m.getNome() + " - " + m.getCodigo()), c);
      py += 1;
      c.gridy = py;
    }
    c.gridy = 1;
    this.add(listaMaterias, c);
  }

  public void configChefe(Chefe u){

  }

  public void configTesoureiro(Tesoureiro u){

  }



}
