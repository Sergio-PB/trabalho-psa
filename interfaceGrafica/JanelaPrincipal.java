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
    // this.setTitle("Area de " + user.getNomeCompleto());
    // infoUsuario = new Label(user.toString());
    // this.add(infoUsuario);

    // user.configuraJanela(this);
    if(user == null){
      throw new NullPointerException();
    } else if(user instanceof Estudante){
      configEstudante((Estudante) user);
    } else if(user instanceof Professor){
      configProfessor((Professor) user);
    } else if(user instanceof Chefe){
      configChefe((Chefe) user);
    } else if(user instanceof Tesoureiro){
      configTesoureiro((Tesoureiro) user);
    }

    // botaoSair = new Button("SAIR");
    // this.add(botaoSair);

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
    c.anchor = GridBagConstraints.PAGE_START;

    this.setLayout(new GridBagLayout());

    this.setTitle("Area de " + u.getNomeCompleto());
    infoUsuario = new Label(u.toString());
    this.add(infoUsuario);
    c.anchor = GridBagConstraints.CENTER;

    listaMaterias = new Panel(new GridBagLayout());
    c.gridwidth = GridBagConstraints.REMAINDER;
    for(Materia m: u.getMaterias()){
      c.gridy += 1;
      System.out.println("add button");
      listaMaterias.add(new Button(m.getNome() + " - " + m.getCodigo()), c);
    }
    c.gridheight = 10;
    this.add(listaMaterias, c);

    botaoSair = new Button("SAIR");
    c.gridy += 2;
    c.anchor = GridBagConstraints.PAGE_END;
    this.add(botaoSair, c);
  }

  public void configChefe(Chefe u){

  }

  public void configTesoureiro(Tesoureiro u){

  }



}
