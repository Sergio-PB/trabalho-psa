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
        if(m.toString().equals(selecionado)){
          materiaEscolhida = m;
        }
      }
		}
	}

  // Professor
  class BtnMateriaListener implements ActionListener {
    private Materia m;
    public BtnMateriaListener(Materia materia){
      this.m = materia;
    }
    public void actionPerformed(ActionEvent evento) {

    }
  }

  // Chefe
  private Button btnCadastrarMateria;
  private Button confirmarPedido;
  private List listaPedidosPendentes;
  private Pedido pedidoSelecionado;
  class BtnListenerChefe implements ActionListener{
    private Chefe c;
    public BtnListenerChefe(Chefe chefe){
      this.c = chefe;
    }

    public void actionPerformed( ActionEvent e) {
      if(((Button)e.getSource()).getLabel().equals("Confirmar pedido")){
        c.aprovarPedido(pedidoSelecionado);
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
    this.add(new Label("SELECIONE UMA MATÉRIA PARA FAZER O PEDIDO DE MATRICULA"), cons);
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

    for (Materia m : u.getMaterias()) {
      Button botaoMateria = new Button (m.getNome());
      cons.gridy += 1;
      cons.weightx = 1;
      cons.gridheight = 1;
      this.add(botaoMateria, cons);

    }

    Label tituloMateria = new Label("ALUNOS - NOTAS: ");
    cons.gridy += 1;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(tituloMateria, cons);

    TextArea infoMateria = new TextArea();
    cons.gridy += 1;
    cons.weightx = 1;
    cons.gridheight = 3;
    this.add(infoMateria, cons);


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
    System.out.println("Area do chefe: "+u);
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
