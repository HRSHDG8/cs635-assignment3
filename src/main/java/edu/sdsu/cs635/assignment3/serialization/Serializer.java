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
  private final ClassLoader serializerClassLoader;

  private Serializer() {
    //use class loader to get relative path of the files in the resource folder
    //note when you run the program these files will be present in the target/classes folder
    serializerClassLoader = this.getClass().getClassLoader();
  }

  synchronized public static Serializer instance() {
    return SingleTonHolder.INSTANCE;
  }

  private static class SingleTonHolder {
    private final static Serializer INSTANCE = new Serializer();
  }

  public void write(String fileName, Object writable) throws IOException {
    Path resourcePath = getPath(fileName);
    try (FileOutputStream outputStream = new FileOutputStream(resourcePath.toString());
         ObjectOutputStream objectWriter = new ObjectOutputStream(outputStream)) {
      objectWriter.writeObject(writable);
    }
  }

  public Object read(String fileName) throws IOException, ClassNotFoundException {
    Path resourcePath = getPath(fileName);
    try (FileInputStream inputStream = new FileInputStream(resourcePath.toString());
         ObjectInputStream objectReader = new ObjectInputStream(inputStream)) {
      return objectReader.readObject();
    }
  }

  private Path getPath(String fileName) {
    return Paths.get(Objects.requireNonNull(serializerClassLoader.getResource(fileName)).getPath().replaceFirst("/", ""));
  }
}
