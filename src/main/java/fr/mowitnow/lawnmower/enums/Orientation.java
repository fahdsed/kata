package fr.mowitnow.lawnmower.enums;

public enum Orientation {

  N('N'), E('E'), S('S'), W('W');

  private final char orientation;

  Orientation(char orientation) {
    this.orientation = orientation;
  }

  public char getOrientation() {
    return orientation;
  }
}
