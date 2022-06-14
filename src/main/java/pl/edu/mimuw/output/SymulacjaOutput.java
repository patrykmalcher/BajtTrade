package pl.edu.mimuw.output;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.symulacja.Symulacja;

// Klasa wykorzystywana do wypisania do pliku JSON.
@SuppressWarnings("unused") // Moshi korzysta!!
public class SymulacjaOutput {
  private InfoOutput info;
  private List<RobotnikOutput> robotnicy;
  private List<SpekulantOutput> spekulanci;
  
  public SymulacjaOutput(Symulacja symulacja) {
    info = new InfoOutput(symulacja);

    List<Robotnik> robotnicyOrg = symulacja.getRobotnicy();
    robotnicy = new ArrayList<>();
    
    for (Robotnik i: robotnicyOrg)
      robotnicy.add(new RobotnikOutput(i));

    List<Spekulant> spekulanciOrg = symulacja.getSpekulanci();
    spekulanci = new ArrayList<>();
    
    for (Spekulant i: spekulanciOrg)
      spekulanci.add(new SpekulantOutput(i));
  }
}
