public class Pedido{
  private Estudante estudante;
  private Materia materia;

  Pedido(Estudante estudante, Materia materia){
    this.estudante = estudante;
    this.materia = materia;
  }
  Estudante getEstudante(){
    return this.estudante;
  }
  Materia getMateria(){
    return this.materia;
  }
}
