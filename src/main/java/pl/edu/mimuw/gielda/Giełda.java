package pl.edu.mimuw.gielda;

public abstract class Giełda {
  // Zwraca false, jeśli zaczynamy
  // transakcje od najbogatszego rodzica
  // lub false w przeciwnym wypadku.
  public abstract boolean skąd();
}
