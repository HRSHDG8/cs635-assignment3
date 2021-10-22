package edu.sdsu.cs635.assignment3.serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Common place to manage file IO using binary serialization.
 * Handles auto closing of resources using try with resource at a common place.
 * Due to java classes being final we can't add these methods on the {@link Object} class
 */
public class Serializer {
  private static Serializer binarySerializer;
  private final ClassLoader serializerClassLoader;

  private Serializer() {
    //use class loader to get relative path of the files in the resource folder
    //note when you run the program these files will be present in the target/classes folder
    serializerClassLoader = this.getClass().getClassLoader();
  }

  synchronized public static Serializer getInstance() {
    if (binarySerializer == null) {
      binarySerializer = new Serializer();
    }
    return binarySerializer;
  }

  public void write(String fileName, Object object) throws IOException {
    Path path = getPath(fileName);
    try (FileOutputStream file = new FileOutputStream(path.toString());
         ObjectOutputStream out = new ObjectOutputStream(file)) {
      out.writeObject(object);
    }
  }

  public Object read(String fileName) throws IOException, ClassNotFoundException {
    Path path = getPath(fileName);
    try (FileInputStream file = new FileInputStream(path.toString());
         ObjectInputStream in = new ObjectInputStream(file)) {
      return in.readObject();
    }
  }

  private Path getPath(String fileName) {
    return Paths.get(Objects.requireNonNull(serializerClassLoader.getResource(fileName)).getPath().replaceFirst("/", ""));
  }
}
