import java.awt.*;
import java.util.Vector;

public class JanelaPrincipal extends Frame{
  Label infoUsuario;
  Button botaoSair;

  // Aluno
  Button acessarMateriasNotas;
  TextArea materiasNotas;
  Button fazerPedido;
  Button confirmarPedido;
  List listaPedidos;

  // Professor
  Panel listaMaterias;
    //Vector<Button, Materia> botoesMaterias;
  Panel textAlunosNotas;

  // Chefe

  // Tesoureiro

  public JanelaPrincipal(Usuario user){
    this.setTitle("Area de " + user.getNomeCompleto());
    // infoUsuario = new Label(user.toString());
    // this.add(infoUsuario);

    user.configuraJanela(this);

    botaoSair = new Button("SAIR");
    this.add(botaoSair);
    this.pack();
    this.setVisible(true);
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 800);
  }

}
