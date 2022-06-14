package pl.edu.mimuw.agenci.robotnicy.strategieKariery;

public class Konserwatysta extends StrategiaKariery {
  @Override
  public KarieraRobotnika zmieńKarierę(KarieraRobotnika kariera, int id) {
    return kariera;
  }

  @Override
  public String toString() {
    return "konserwatysta";
  }
}
