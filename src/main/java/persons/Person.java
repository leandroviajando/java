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
    return father;
  }

  /**
   * Returns the set of this person's children.
   * 
   * @creates | result
   * @post | result != null
   * @post | result.stream().allMatch(child -> child.getFather() == this)
   */
  public Set<Person> getChildren() {
    return Set.copyOf(children);
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
    if (father == this)
      throw new IllegalArgumentException("A person cannot be their own father");

    if (this.father != null)
      this.father.children.remove(this);

    this.father = father;

    if (father != null)
      father.children.add(this);
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
