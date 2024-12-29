/**
 * The UnoValue enum represents the possible values of Uno cards.
 */
public enum UnoValue {
    /** Number cards from one to nine. */
    one, two, three, four, five, six, seven, eight, nine,

    /** Special action cards. */
    draw_one, reverse, skip, flip,

    /** Wild cards */
    wild, wild_draw_two, draw_five, skip_everyone, wild_draw_color;
}
