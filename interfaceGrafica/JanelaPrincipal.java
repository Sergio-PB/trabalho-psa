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
  private Materia materiaEscolhida;
  private List listaPedidos;
  class BtnListenerEst implements ActionListener{
    private Estudante aluno;
    public BtnListenerEst(Estudante u){
      aluno = u;
    }
		public void actionPerformed( ActionEvent e) {
      aluno.fazPedido(materiaEscolhida);
    }
	}
  class ListListenerEst implements ItemListener {
		public void itemStateChanged( ItemEvent e) {
      String selecionado = listaPedidos.getSelectedItem();
      for(Materia m: Administrador.listMaterias()){
        if(m.toString() == selecionado){
          materiaEscolhida = m;
        }
      }
		}
	}

  // Professor
  private Panel listaMaterias;
  private Panel listaMostraMaterias;
  //Vector<Button, Materia> botoesMaterias;
  private Panel textAlunosNotas;
  class BtnListenerProf implements ActionListener{
    private Vector<Materia> materias;
    public BtnListenerProf(Professor p){
      materias = p.getMaterias();
    }
    public void actionPerformed( ActionEvent e) {


    }
  }

  // Chefe
  private Button btnCadastrarMateria;
  private Button confirmarPedido;
  private List listaPedidosPendentes;
  private Pedido pedidoSelecionado;
  class BtnListenerChefe implements ActionListener{
    private Chefe c;
    public BtnListenerChefe(Chefe c){
      c = c;
      System.out.println(c);
    }
    public void actionPerformed( ActionEvent e) {
      System.out.println("Btn" + c);
      if(((Button)e.getSource()).getLabel().equals("Confirmar pedido")){
        // try{
          for(Pedido p: Chefe.getPedidos()){
            if(p.toString().equals(pedidoSelecionado.toString())){
              System.out.println("Aprovou");
              System.out.println(c);
              c.aprovarPedido(p);
            }else{

              System.out.println("N Aprovou: \n"+pedidoSelecionado+"\n"+p.toString());
              System.out.println(pedidoSelecionado.equals(p.toString()));
            }
          }
        // }catch (Exception ex){
        //   System.out.println(ex);
        //   System.out.println("Erro"+pedidoSelecionado);
        // }
      }else{
        System.out.println(((Button)e.getSource()).getLabel());
      }
    }
  }
  class ListListenerChefe implements ItemListener {
		public void itemStateChanged( ItemEvent e) {
      String selecionado = listaPedidosPendentes.getSelectedItem();
      for(Pedido p: Chefe.getPedidos()){
        if(p.toString().equals(selecionado)){
          pedidoSelecionado = p;
        }
      }
		}
	}

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

    // PAINEL DE MATERIAS E NOTAS//
    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(new Label("MATERIA - NOTAS"), cons);
    materiasNotas = new TextArea();
    materiasNotas.setEditable(false);
    cons.gridy = 2;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.PAGE_START;
    for(Materia m: u.getMaterias()){
      materiasNotas.append("Nota de "+m.getCodigo()+" - "+m.getNome()+": "+u.getNotas().get(m)+"\n");
    }
    this.add(materiasNotas, cons);

    // SELECIONAR MATERIA  PARA PEDIDO//
    cons.gridy = 3;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(new Label("SELECIONE UMA MATÉRIA PARA FAZER O PEDIDO DE MATRÍCULA"), cons);
    // PAINEL DE MATERIAS //
    listaPedidos = new List();
    for(Materia m: Administrador.listMaterias()){
      if(!u.getMaterias().contains(m)){
        listaPedidos.add(m.toString());
      }
    }
    cons.gridy = 4;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(listaPedidos, cons);
    ListListenerEst pl = new ListListenerEst();
    listaPedidos.addItemListener(pl);
    // BOTAO FAZER PEDIDO//
    fazerPedido = new Button("Confirmar pedido");
    cons.gridy = 5;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(fazerPedido, cons);
    BtnListenerEst fp = new BtnListenerEst(u);
		fazerPedido.addActionListener(fp);

    // BOTAO DE SAIR //
    botaoSair = new Button("SAIR");
    cons.gridy = 7;
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
      btnList = new BtnListenerProf(u);
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
    listaMostraMaterias = new Panel();
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

    // BOTAO DE SAIR //
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

    // BOTAO CADASTRAR MATERIA //
    btnCadastrarMateria = new Button("Cadastrar nova matéria");
    cons.anchor = GridBagConstraints.PAGE_START;
    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(btnCadastrarMateria, cons);
    System.out.println("Criando btn");
    BtnListenerChefe nm = new BtnListenerChefe(u);
    btnCadastrarMateria.addActionListener(nm);

    // SELECIONAR PEDIDO//
    cons.gridy = 2;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(new Label("SELECIONE UM PEDIDO PARA APROVAR"), cons);
    // PAINEL DE PEDIDOS //
    listaPedidosPendentes = new List();
    for(Pedido p: Chefe.getPedidos()){
      listaPedidosPendentes.add(p.toString());
    }
    cons.gridy = 3;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(listaPedidosPendentes, cons);
    ListListenerChefe pl = new ListListenerChefe();
    listaPedidosPendentes.addItemListener(pl);
    // BOTAO FAZER PEDIDO//
    confirmarPedido = new Button("Confirmar pedido");
    cons.gridy = 4;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(confirmarPedido, cons);
    System.out.println("Criando btn");
    BtnListenerChefe fp = new BtnListenerChefe(u);
		confirmarPedido.addActionListener(fp);

    // BOTAO DE SAIR //
    botaoSair = new Button("SAIR");
    cons.gridy = 8;
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

  public void configTesoureiro(Tesoureiro u){

  }



}
