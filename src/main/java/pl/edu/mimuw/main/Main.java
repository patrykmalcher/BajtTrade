package pl.edu.mimuw.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import pl.edu.mimuw.input.SymulacjaInput;
import pl.edu.mimuw.output.SymulacjaOutput;
import pl.edu.mimuw.symulacja.Symulacja;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    Scanner s = new Scanner(file);

    String json = "";

    while (s.hasNext())
      json += s.next();

    s.close();

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<SymulacjaInput> inputAdapter = moshi.adapter(SymulacjaInput.class);
    SymulacjaInput symulacjaInput = inputAdapter.fromJson(json);

    Symulacja symulacja = new Symulacja(symulacjaInput);
    symulacja.rozegrajSymulację();

    JsonAdapter<SymulacjaOutput> outputAdapter = moshi.adapter(SymulacjaOutput.class);
    SymulacjaOutput symulacjaOutput = new SymulacjaOutput(symulacja);
    String res = outputAdapter.indent("\t").toJson(symulacjaOutput);
    

    BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
    writer.write(res);

    writer.close();
  }
}
