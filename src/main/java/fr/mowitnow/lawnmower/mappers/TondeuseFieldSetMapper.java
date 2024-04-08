package fr.mowitnow.lawnmower.mappers;

import fr.mowitnow.lawnmower.model.Mower;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class TondeuseFieldSetMapper implements FieldSetMapper<Mower> {

  @Override
  public Mower mapFieldSet(FieldSet fieldSet) throws BindException {
    Mower mower = new Mower();
    mower.setX(fieldSet.readInt("x"));
    mower.setY(fieldSet.readInt("y"));
    mower.setOrientation(fieldSet.readChar("orientation"));
    mower.setInstructions(fieldSet.readString("instructions"));
    return mower;
  }
}
