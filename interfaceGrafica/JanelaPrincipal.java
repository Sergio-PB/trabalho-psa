import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class JanelaPrincipal extends Frame{
  private JanelaPrincipal janelaExterna = this;
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
      if(materiaEscolhida!=null){
        aluno.fazPedido(materiaEscolhida);
        listaPedidos.remove(listaPedidos.getSelectedItem());
        materiaEscolhida = null;
      }
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
  private List infoMateria;
  private Estudante alunoSelecionado;
  private Materia materiaSelecionada;
  private TextArea novaNota;
  class BtnMateriaListener implements ActionListener {
    private Materia m;
    public BtnMateriaListener(Materia materia){
      this.m = materia;
    }
    public void actionPerformed(ActionEvent e) {
      materiaSelecionada = m;
      infoMateria.removeAll();
      for(Estudante est: m.getAlunos()){
        String item = est.getNomeCompleto()+" - [";
        String adder = "";
        for(Integer nota: est.getNotas().get(m)){
          item += (adder+nota.toString());
          adder = ", ";
        }
        infoMateria.add(item+"]");
      }
    }
  }
  class ListListenerProf implements ItemListener {
    public void itemStateChanged( ItemEvent e) {
      String selecionado = infoMateria.getSelectedItem().split(" - ")[0];
      for(Estudante est: materiaSelecionada.getAlunos()){
        if(est.getNomeCompleto().equals(selecionado)){
          alunoSelecionado = est;
        }
      }
    }
  }
  class BtnListenerProfNota implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(alunoSelecionado!=null && novaNota.getText()!= null){
        Vector<Integer> notas = new Vector<Integer>();
        for(String n: novaNota.getText().split(" ")){
          notas.add(Integer.parseInt(n));
        }
        alunoSelecionado.setNota(materiaSelecionada, notas);
      }
    }
  }

  // Chefe
  private Button btnCadastrarMateria;

  private Panel novaMateria;
  private TextArea novaMateriaNome;
  private TextArea novaMateriaCodigo;
  private TextArea novaMateriaProfessor;
  private Button novaMateriaConfirmar;

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
        if(pedidoSelecionado!=null){
          c.aprovarPedido(pedidoSelecionado);
          listaPedidosPendentes.remove(listaPedidosPendentes.getSelectedItem());
          pedidoSelecionado = null;
        }
      }else{
        novaMateria.setVisible(true);
        janelaExterna.pack();
      }
    }
  }
  class BtnListenerNovaMateria implements ActionListener{
    private Chefe c;
    public BtnListenerNovaMateria(Chefe chefe){
      this.c = chefe;
    }

    public void actionPerformed( ActionEvent e) {
      Professor p = null;
      for(Usuario u : T3.usuarios.values()){
        if(u instanceof Professor && u.getNome().equals(novaMateriaProfessor.getText())){
          p = (Professor) u;
          break;
        }
      }
      c.cadastrarMateria(novaMateriaNome.getText(), novaMateriaCodigo.getText(), p);
      novaMateriaNome.setText("");
      novaMateriaCodigo.setText("");
      novaMateriaProfessor.setText("");
      novaMateria.setVisible(false);
      janelaExterna.pack();
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
  private List listaProfessores;
  private TextArea dadosProfessor;
  private Professor professorSelecionado = null;
  private TextArea novoSalario;
  private Label valorNovoSalario;
  private Button calcularNovoSalario;
  private Button confirmarNovoSalario;
  private int valor;
  class BtnListenerTes implements ActionListener{
    public void actionPerformed( ActionEvent e) {
      if(((Button)e.getSource()).getLabel().equals("Calcular")){
        try{
          valor = Integer.parseInt(novoSalario.getText())*professorSelecionado.getMaterias().size();
          valorNovoSalario.setText("Novo salario total: R$"+Integer.toString(valor)+",00");
        }catch (Exception ex){
          valorNovoSalario.setText("entrada invalida");
        }
      }else{
        try{
          valor = Integer.parseInt(novoSalario.getText())*professorSelecionado.getMaterias().size();
          professorSelecionado.setSalario(valor);
        }catch (Exception ex){
          valorNovoSalario.setText("entrada invalida");
        }
      }
    }
  }
  class ListListenerTes implements ItemListener {
		public void itemStateChanged( ItemEvent e) {
      String selecionado = listaProfessores.getSelectedItem();
      for(Professor p: Tesoureiro.getProfessores()){
        if(p.toString().equals(selecionado)){
          professorSelecionado = p;
          break;
        }
      }
      if(professorSelecionado != null){
        dadosProfessor.setText(professorSelecionado.getNomeCompleto()+"\n");
        dadosProfessor.append("Ministra "+professorSelecionado.getMaterias().size()+" matérias\n");
        dadosProfessor.append("e recebe "+(professorSelecionado.getSalario()/professorSelecionado.getMaterias().size())+" por matéria,\n");
        dadosProfessor.append("totalizando R$"+professorSelecionado.getSalario()+",00");
      }
		}
	}

  // Login
  private TextArea campoSenha;
  private TextArea campoUsuario;

  // Listeners
  class LoginListener implements ActionListener{
		public void actionPerformed( ActionEvent e) {
      if(T3.usuarios.containsKey(Integer.parseInt(campoUsuario.getText()))){
    			if(T3.usuarios.get(Integer.parseInt(campoUsuario.getText())).getSenha().equals(campoSenha.getText())){
    				new JanelaPrincipal(T3.usuarios.get(Integer.parseInt(campoUsuario.getText())));
            T3.janelaLogin.setVisible(false);
    			}else{
            System.out.println("Essa senha está incorreta.");
    			}
  		}else{
  			System.out.println("Essa matrícula não está cadastrada.");
  		}
		}
	}

  // Layout
  GridBagConstraints cons;


  class ExitListener implements ActionListener{
		public void actionPerformed( ActionEvent e) {
      T3.janelaLogin.setVisible(true);
      dispose();
		}
	}

  public JanelaPrincipal(Usuario user){
    //Logo
		ImageIcon logo = new ImageIcon("Logo.png"); //Por local da imagem (Enderecos com acentuacao pode dar ruim)
		this.setIconImage(logo.getImage());
		this.setTitle("MAGISTER");

		this.setFont( new Font("SansSerif", Font.PLAIN, 14) );

    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);
    this.setBackground(new Color(170, 203, 255));

    cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 10, 5, 10);
    try{
      infoUsuario = new Label("Area de "+user.getNomeCompleto(), Label.CENTER);
    }catch(Exception exp){
      infoUsuario = new Label("Area de Login", Label.CENTER);
    }
    infoUsuario.setFont( new Font("SansSerif", Font.PLAIN, 28) );
    cons.anchor = GridBagConstraints.PAGE_START;
    cons.gridy = 0;
    cons.weightx = 1;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(infoUsuario, cons);

    if(user == null){
      configLogin();
    } else if(user instanceof Estudante){
      configEstudante((Estudante) user);
    } else if(user instanceof Professor){
      configProfessor((Professor) user);
    } else if(user instanceof Chefe){
      configChefe((Chefe) user);
    } else if(user instanceof Tesoureiro){
      configTesoureiro((Tesoureiro) user);
    }

    // BOTAO DE SAIR //
    botaoSair = new Button("SAIR");
    cons.gridy = 8;
    cons.fill = GridBagConstraints.NONE;
    cons.anchor = GridBagConstraints.LAST_LINE_END;
    this.add(botaoSair, cons);
    ExitListener el = new ExitListener();
		botaoSair.addActionListener(el);

    this.pack();
    this.setVisible(true);
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 400);
  }

  public void configLogin(){
    Label labelUsuario = new Label("Usuario:");
    cons.gridy += 1;
    cons.weightx = 1;
    this.add(labelUsuario, cons);

    campoUsuario = new TextArea(1, 10);
    cons.gridy += 1;
    cons.weightx = 1;
    this.add(campoUsuario, cons);

    Label labelSenha = new Label("Senha:");
    cons.gridy += 1;
    cons.weightx = 1;
    this.add(labelSenha, cons);

    campoSenha = new TextArea(1, 10);
    cons.gridy += 1;
    cons.weightx = 1;
    this.add(campoSenha, cons);

    // BOTAO DE SAIR //
    Button botaoEntrar = new Button("ENTRAR");
    cons.gridy += 1;
    cons.weightx = 0.5;
    this.add(botaoEntrar, cons);
    LoginListener ll = new LoginListener();
		botaoEntrar.addActionListener(ll);
  }

  public void configEstudante(Estudante u){
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 10, 5, 10);

    // PAINEL DE MATERIAS E NOTAS//
    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(new Label("MATERIA - NOTAS"), cons);
    materiasNotas = new TextArea(u.getMaterias().size()+1, 40);
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
  }

  public void configProfessor(Professor u){
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 10, 5, 10);

    cons.gridy = 0;
    for (Materia m : u.getMaterias()) {
      Button botaoMateria = new Button (m.getNome());
      cons.gridy += 1;
      cons.weightx = 1;
      cons.gridwidth = 2;
      cons.gridheight = 1;
      this.add(botaoMateria, cons);
      botaoMateria.addActionListener(new BtnMateriaListener(m));
    }
    Panel painelMudarNota = new Panel(new GridBagLayout());
    Label tituloMateria = new Label("ALUNOS - NOTAS: ");
    cons.gridy += 1;
    cons.gridheight = 1;
    painelMudarNota.add(tituloMateria, cons);

    infoMateria = new List();
    cons.gridy += 1;
    cons.weightx = 3;
    cons.gridheight = 3;
    painelMudarNota.add(infoMateria, cons);
    ListListenerProf im = new ListListenerProf();
		infoMateria.addItemListener(im);

    Button botaoMudarNota = new Button("Mudar nota");
    cons.gridy += 1;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.fill = GridBagConstraints.NONE;
    painelMudarNota.add(botaoMudarNota, cons);
    BtnListenerProfNota ml = new BtnListenerProfNota();
		botaoMudarNota.addActionListener(ml);

    novaNota = new TextArea(1, 10);
    painelMudarNota.add(novaNota, cons);
    cons.fill = GridBagConstraints.BOTH;
    this.add(painelMudarNota, cons);
  }

  public void configChefe(Chefe u){
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 10, 5, 10);

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

    // PAINEL CRIAR MATERIA //
    novaMateria = new Panel();
    novaMateria.setLayout(new GridBagLayout());
    novaMateriaNome = new TextArea(1, 20);
    novaMateriaCodigo = new TextArea(1, 8);
    novaMateriaProfessor = new TextArea(1, 16);
    novaMateriaConfirmar = new Button("Confirmar nova materia");
    novaMateria.add(novaMateriaNome);
    novaMateria.add(novaMateriaCodigo);
    novaMateria.add(novaMateriaProfessor);
    novaMateria.add(novaMateriaConfirmar);
    BtnListenerNovaMateria nml = new BtnListenerNovaMateria(u);
    novaMateriaConfirmar.addActionListener(nml);

    cons.gridy = 2;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    novaMateria.setVisible(false);
    this.add(novaMateria, cons);

    // SELECIONAR PEDIDO//
    cons.gridy = 3;
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
    cons.gridy = 4;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(listaPedidosPendentes, cons);
    ListListenerChefe pl = new ListListenerChefe();
    listaPedidosPendentes.addItemListener(pl);
    // BOTAO FAZER PEDIDO//
    confirmarPedido = new Button("Confirmar pedido");
    cons.gridy = 5;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(confirmarPedido, cons);
    BtnListenerChefe fp = new BtnListenerChefe(u);
		confirmarPedido.addActionListener(fp);
  }

  public void configTesoureiro(Tesoureiro u){
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 10, 5, 10);

    // SELECIONAR PROFESSOR //
    cons.gridy = 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridwidth = GridBagConstraints.REMAINDER;
    cons.gridheight = 1;
    cons.anchor = GridBagConstraints.CENTER;
    this.add(new Label("SELECIONE UM PROFESSOR PARA MUDAR O SALARIO"), cons);
    // PAINEL DE PROFESSORES //
    listaProfessores = new List();
    for(Professor p: Tesoureiro.getProfessores()){
      listaProfessores.add(p.toString());
    }
    cons.gridy += 1;
    cons.gridx = 0;
    cons.weightx = 2;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(listaProfessores, cons);
    ListListenerTes lp = new ListListenerTes();
    listaProfessores.addItemListener(lp);

    dadosProfessor = new TextArea(4, 26);
    cons.gridx = 1;
    cons.gridwidth = GridBagConstraints.REMAINDER;
    cons.weightx = 1;
    cons.gridheight = 1;
    this.add(dadosProfessor, cons);

    novoSalario = new TextArea("valor/matéria", 1, 6, TextArea. SCROLLBARS_NONE);
    cons.gridy += 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(novoSalario, cons);

    calcularNovoSalario = new Button("Calcular");
    cons.gridx = 1;
    cons.weightx = 1;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(calcularNovoSalario, cons);
    BtnListenerTes cn = new BtnListenerTes();
    calcularNovoSalario.addActionListener(cn);

    valorNovoSalario = new Label("novo salario");
    cons.gridy += 1;
    cons.gridx = 0;
    cons.weightx = 1;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(valorNovoSalario, cons);

    confirmarNovoSalario = new Button("Confirmar valor");
    cons.gridx = 1;
    cons.weightx = 1;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    this.add(confirmarNovoSalario, cons);
    BtnListenerTes cl = new BtnListenerTes();
    confirmarNovoSalario.addActionListener(cl);
  }
}
