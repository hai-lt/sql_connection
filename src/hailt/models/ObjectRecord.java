package hailt.models;

/**
 * 
 * @author tanhai - 2017/05/01
 *
 */
public class ObjectRecord {
  protected String[] values;

  public ObjectRecord(String... values) {
    this.values = values;
  }

  public String[] getValues() {
    return values;
  }
}
