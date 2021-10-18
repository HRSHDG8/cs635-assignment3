package edu.sdsu.cs635.assignment3.serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serialization {
  private static Serialization objectMapper;

  private Serialization() {
  }

  synchronized public static Serialization getInstance() {
    if (objectMapper == null) {
      objectMapper = new Serialization();
    }
    return objectMapper;
  }

  public void write(String fileName, Object object) throws IOException {
    Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).getPath().replaceFirst("/", ""));
    try (FileOutputStream file = new FileOutputStream(path.toString());
         ObjectOutputStream out = new ObjectOutputStream(file)) {
      out.writeObject(object);
    }
  }

  public Object read(String fileName) throws IOException, ClassNotFoundException {
    Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).getPath().replaceFirst("/", ""));
    try (FileInputStream file = new FileInputStream(path.toString());
         ObjectInputStream in = new ObjectInputStream(file)) {
      return in.readObject();
    }
  }
}
