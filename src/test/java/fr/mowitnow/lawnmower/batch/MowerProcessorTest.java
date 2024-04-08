package fr.mowitnow.lawnmower.batch;

import fr.mowitnow.lawnmower.enums.Orientation;
import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.processor.MowerProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerProcessorTest {

  private MowerProcessor mowerProcessor;

  @BeforeEach
  public void setUp() {
    mowerProcessor = new MowerProcessor();
  }

  @Test
  void testProcessTurningRight() {
    Mower mower = new Mower(0, 0, 'N', "D");
    mowerProcessor.process(mower);
    assertEquals('E', mower.getOrientation());
  }

  @Test
  void testProcessTurningLeft() {
    Mower mower = new Mower(0, 0, 'N', "G");
    mowerProcessor.process(mower);
    assertEquals('W', mower.getOrientation());
  }

  @Test
  void testProcessMovingForward() {
    Mower mower = new Mower(0, 0, 'N', "A");
    mowerProcessor.process(mower);
    assertEquals(1, mower.getY());
  }

}


