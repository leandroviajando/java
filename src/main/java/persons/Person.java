package persons;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a person with father-child relationships.
 * 
 * @invar | getFather() == null || getFather().getChildren().contains(this)
 * @invar | getChildren().stream().allMatch(child -> child.getFather() == this)
 * @invar | getChildren() != null
 */
public class Person {
  private Person father;
  private final Set<Person> children = new HashSet<>();

  /**
   * @post | getFather() == null
   * @post | getChildren().isEmpty()
   */
  public Person() {
  }

  /**
   * Returns this person's father.
   * 
   * @post | result == null || result.getChildren().contains(this)
   */
  public Person getFather() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the set of this person's children.
   * 
   * @creates | result
   * @post | result != null
   * @post | result.stream().allMatch(child -> child.getFather() == this)
   */
  public Set<Person> getChildren() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Sets this person's father to the given person.
   * 
   * @throws IllegalArgumentException if the given father is this person
   * @mutates | this
   * @post | getFather() == father
   * @post | father == null || father.getChildren().contains(this)
   */
  public void setFather(Person father) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Removes the link between this person and their father.
   * 
   * @mutates | this
   * @post | getFather() == null
   */
  public void removeFather() {
    setFather(null);
  }
}
