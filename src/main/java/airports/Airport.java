package airports;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an airport in a network of connected airports.
 * 
 * @invar | getConnections() != null
 * @invar | getConnections().stream().allMatch(other -> other != null &&
 *        other.getConnections().contains(this))
 */
public class Airport {
  private final Set<Airport> connections = new HashSet<>();

  /**
   * Initializes this airport with no connections.
   * 
   * @post | getConnections().isEmpty()
   */
  public Airport() {
  }

  /**
   * Returns the set of airports that are directly connected to this airport.
   * 
   * @creates | result
   * @post | result != null
   * @post | result.stream().allMatch(other ->
   *       other.getConnections().contains(this))
   */
  public Set<Airport> getConnections() {
    return Set.copyOf(connections);
  }

  /**
   * Connects this airport to the given airport.
   * 
   * @throws IllegalArgumentException if other is null
   * @throws IllegalArgumentException if other is this airport
   * @mutates | this, other
   * @post | getConnections().contains(other)
   * @post | other.getConnections().contains(this)
   * @post | getConnections().stream().allMatch(a ->
   *       | a == other || old(getConnections()).contains(a))
   * @post | other.getConnections().stream().allMatch(a ->
   *       | a == this || old(other.getConnections()).contains(a))
   */
  public void connectTo(Airport other) {
    if (other == null)
      throw new IllegalArgumentException("other is null");
    if (other == this)
      throw new IllegalArgumentException("Cannot connect airport to itself");

    connections.add(other);
    other.connections.add(this);
  }

  /**
   * Removes the connection between this airport and the given airport.
   * 
   * @throws IllegalArgumentException if other is null
   * @throws IllegalArgumentException if other is not connected to this airport
   * @mutates | this, other
   * @post | !getConnections().contains(other)
   * @post | !other.getConnections().contains(this)
   * @post | getConnections().stream().allMatch(a ->
   *       old(getConnections()).contains(a))
   * @post | other.getConnections().stream().allMatch(a ->
   *       old(other.getConnections()).contains(a))
   */
  public void disconnect(Airport other) {
    if (other == null)
      throw new IllegalArgumentException("other is null");
    if (!connections.contains(other))
      throw new IllegalArgumentException("Airports are not connected");

    connections.remove(other);
    other.connections.remove(this);
  }
}
