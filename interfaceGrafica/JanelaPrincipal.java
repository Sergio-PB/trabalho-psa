import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class JanelaPrincipal extends Frame{
  private Label infoUsuario;
  private Button botaoSair;

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
  class BtnListenerProf implements ActionListener{
		public void actionPerformed( ActionEvent e) {
      System.out.println(e.getSource());
		}
	}

  // Chefe

  // Tesoureiro

  // Listeners
  class ExitListener implements ActionListener{
		public void actionPerformed( ActionEvent e) {
      dispose();
		  // System.exit(0);
		}
	}

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

  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 800);
  }

  public void configEstudante(Estudante u){

  }

  public void configProfessor(Professor u){
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(10, 10, 10, 10);

    infoUsuario = new Label("Area de "+u);
    cons.anchor = GridBagConstraints.PAGE_START;
    cons.gridy = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(infoUsuario, cons);

    // PAINEL DE SELEÇÃO DE MATERIAS //
    GridBagLayout playout = new GridBagLayout();
    listaMaterias = new Panel();
    listaMaterias.setLayout(playout);
    cons.fill = GridBagConstraints.BOTH;
    cons.anchor = GridBagConstraints.LINE_START;
    cons.insets = new Insets(5, 2, 0, 2); // top left bot right
    int panelY = 1;
    Button btnMateria;
    BtnListenerProf btnList;
    for(Materia m: u.getMaterias()){
      panelY += 1;
      cons.gridy = panelY;
      btnMateria = new Button(m.getNome());
      listaMaterias.add(btnMateria, cons);
      btnList = new BtnListenerProf();
      btnMateria.addActionListener(btnList);
    }
    cons.anchor = GridBagConstraints.CENTER;
    cons.gridy = 1;
    cons.weightx = 2;
    cons.weighty = 2;
    cons.gridheight = 2;
    this.add(listaMaterias, cons);

    // PAINEL DE MOSTRAR MATERIAS //
    playout = new GridBagLayout();
    Panel listaMostraMaterias = new Panel();
    listaMostraMaterias.setLayout(playout);
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 2, 5, 2); // top left bot right

    cons.anchor = GridBagConstraints.CENTER;
    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 2;
    cons.weighty = 2;
    cons.gridheight = 2;
    this.add(listaMostraMaterias, cons);

    // PAINEL DE MOSTRAR MATERIAS //
    botaoSair = new Button("SAIR");
    cons.gridy = 3;
    cons.weightx = 0.5;
    cons.gridheight = 1;
    cons.fill = GridBagConstraints.NONE;
    cons.anchor = GridBagConstraints.LAST_LINE_END;
    this.add(botaoSair, cons);
    ExitListener el = new ExitListener();
		botaoSair.addActionListener(el);


    this.pack();
    this.setVisible(true);
  }

  public void configChefe(Chefe u){

  }

  public void configTesoureiro(Tesoureiro u){

  }



}
