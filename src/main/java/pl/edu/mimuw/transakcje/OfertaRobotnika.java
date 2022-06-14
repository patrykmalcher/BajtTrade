package pl.edu.mimuw.transakcje;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.zasoby.Zasób;

public class OfertaRobotnika implements Comparable<OfertaRobotnika> {
  private Zasób zasób;
  private int ilość;
  private Robotnik robotnik;

  // Jeśli kupujemy ta wartość nie ma znaczenia.
  private int poziom;

  public OfertaRobotnika(Zasób zasób, int ilość, Robotnik robotnik, int poziom) {
    this.zasób = zasób;
    this.ilość = ilość;
    this.robotnik = robotnik;
    this.poziom = poziom;
  }

  public Robotnik getRobotnik() {
    return robotnik;
  }

  // Ustawienie ofert w odpowiedniej kolejności, żeby móc zaczynać
  // transakcje według zasad giełdowych.
  @Override
  public int compareTo(OfertaRobotnika o) {
    if (robotnik.getDiamenty() == o.getRobotnik().getDiamenty())
      return robotnik.getId() - o.getRobotnik().getId();
    if (robotnik.getDiamenty() < o.getRobotnik().getDiamenty())
      return -1;
    return 1;
  }

  public Zasób getZasób() {
    return zasób;
  }

  public int getIlość() {
    return ilość;
  }

  public int getPoziom() {
    return poziom;
  }
}
