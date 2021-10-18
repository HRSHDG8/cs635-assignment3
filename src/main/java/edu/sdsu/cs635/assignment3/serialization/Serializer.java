package edu.sdsu.cs635.assignment3.serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Common place to manage file IO using binary serialization.
 * Handles auto closing of resources using try with resource at a common place.
 */
public class Serializer {
  private static Serializer binarySerializer;

  private Serializer() {
  }

  synchronized public static Serializer getInstance() {
    if (binarySerializer == null) {
      binarySerializer = new Serializer();
    }
    return binarySerializer;
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
