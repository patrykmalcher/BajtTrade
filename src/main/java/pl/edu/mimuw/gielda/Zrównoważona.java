package pl.edu.mimuw.gielda;

import pl.edu.mimuw.symulacja.Symulacja;

public class Zrównoważona extends Giełda {
  @Override
  public boolean skąd() {
    return Symulacja.getTura() % 2 == 1;
  }  
}
