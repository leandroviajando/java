package persons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class PersonTest {
  @Test
  void testNewPerson() {
    Person person = new Person();
    assertNull(person.getFather());
    assertTrue(person.getChildren().isEmpty());
  }

  @Test
  void testSetFather() {
    Person child = new Person();
    Person father = new Person();

    child.setFather(father);

    assertEquals(father, child.getFather());
    assertTrue(father.getChildren().contains(child));
    assertEquals(1, father.getChildren().size());
  }

  @Test
  void testRemoveFather() {
    Person child = new Person();
    Person father = new Person();

    child.setFather(father);
    child.removeFather();

    assertNull(child.getFather());
    assertFalse(father.getChildren().contains(child));
    assertTrue(father.getChildren().isEmpty());
  }

  @Test
  void testChangeFather() {
    Person child = new Person();
    Person father1 = new Person();
    Person father2 = new Person();

    child.setFather(father1);
    child.setFather(father2);

    assertEquals(father2, child.getFather());
    assertFalse(father1.getChildren().contains(child));
    assertTrue(father2.getChildren().contains(child));
  }

  @Test
  void testMultipleChildren() {
    Person father = new Person();
    Person child1 = new Person();
    Person child2 = new Person();
    Person child3 = new Person();

    child1.setFather(father);
    child2.setFather(father);
    child3.setFather(father);

    assertEquals(3, father.getChildren().size());
    assertTrue(father.getChildren().contains(child1));
    assertTrue(father.getChildren().contains(child2));
    assertTrue(father.getChildren().contains(child3));
  }

  @Test
  void testCannotBeOwnFather() {
    Person person = new Person();
    assertThrows(IllegalArgumentException.class, () -> person.setFather(person));
  }

  @Test
  void testChildrenSetImmutable() {
    Person father = new Person();
    Person child = new Person();
    child.setFather(father);

    Set<Person> children = father.getChildren();
    assertThrows(UnsupportedOperationException.class, () -> children.add(new Person()));
    assertThrows(UnsupportedOperationException.class, () -> children.remove(child));
  }

  @Test
  void testComplexRelationships() {
    Person grandfather = new Person();
    Person father = new Person();
    Person child1 = new Person();
    Person child2 = new Person();

    father.setFather(grandfather);
    child1.setFather(father);
    child2.setFather(father);

    assertEquals(grandfather, father.getFather());
    assertEquals(father, child1.getFather());
    assertEquals(father, child2.getFather());
    assertTrue(grandfather.getChildren().contains(father));
    assertTrue(father.getChildren().contains(child1));
    assertTrue(father.getChildren().contains(child2));
    assertEquals(1, grandfather.getChildren().size());
    assertEquals(2, father.getChildren().size());
  }
}
