package fr.mowitnow.lawnmower.model;

import lombok.Data;

@Data
public class Mower {

  private int x;
  private int y;
  private char orientation;
  private String instructions;

  public Mower() {
  }

  public Mower(int x, int y, char orientation, String instructions) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
    this.instructions = instructions;
  }
}
