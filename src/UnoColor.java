/**
 * Enumeration of Uno card colors, including standard colors and a wildcard color.
 */
public enum UnoColor {
    // Standard colors
    blue,
    green,
    red,
    yellow,
    pink,
    teal,
    orange,
    purple,

    // Wildcard color
    wild;

    /**
     * Private default constructor for UnoColor.
     * This constructor is not meant to be called directly.
     */
    private UnoColor() {
        // This constructor is intentionally left empty
        // It's private to prevent instantiation of additional UnoColor instances
    }
}
