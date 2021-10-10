package edu.sdsu.cs635.assignment3.command;

import java.io.Serializable;

@FunctionalInterface
public interface Command extends Serializable {
  void execute();
}
