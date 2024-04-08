package fr.mowitnow.lawnmower.processor;

import fr.mowitnow.lawnmower.enums.Orientation;
import fr.mowitnow.lawnmower.model.Mower;
import org.springframework.batch.item.ItemProcessor;

public class MowerProcessor implements ItemProcessor<Mower, Mower> {

  @Override
  public Mower process(Mower mower) {
    String instructions = mower.getInstructions();
    for (char instruction : instructions.toCharArray()) {
      switch (instruction) {
        case 'D' -> turnRight(mower);
        case 'G' -> turnLeft(mower);
        case 'A' -> moveForward(mower);
        default -> {
          //
        }
      }
    }
    return mower;
  }

  private void turnRight(Mower mower) {
    mower.setOrientation(switch (mower.getOrientation()) {
      case 'N' -> Orientation.E.getOrientation();
      case 'E' -> Orientation.S.getOrientation();
      case 'S' -> Orientation.W.getOrientation();
      case 'W' -> Orientation.N.getOrientation();
      default -> mower.getOrientation();
    });
  }

  private void turnLeft(Mower mower) {
    char orientation = mower.getOrientation();
    mower.setOrientation(switch (orientation) {
      case 'N' -> Orientation.W.getOrientation();
      case 'E' -> Orientation.N.getOrientation();
      case 'S' -> Orientation.E.getOrientation();
      case 'W' -> Orientation.S.getOrientation();
      default -> orientation;
    });
  }


  private void moveForward(Mower mower) {
    char orientation = mower.getOrientation();
    switch (orientation) {
      case 'N' -> mower.setY(mower.getY() + 1);
      case 'E' -> mower.setX(mower.getX() + 1);
      case 'S' -> mower.setY(mower.getY() - 1);
      case 'W' -> mower.setX(mower.getX() - 1);
      default -> {//
      }
    }
  }

}
