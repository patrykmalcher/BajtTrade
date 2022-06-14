package pl.edu.mimuw.agenci.robotnicy.strategieKariery;

public abstract class StrategiaKariery {
  public abstract KarieraRobotnika zmieńKarierę(KarieraRobotnika kariera, int id);

  @Override
  public abstract String toString();
}
