import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;

/**
 * Represents an Uno card with two sides (light and dark).
 * /requie serilaizations
 */
public class UnoCard {

    // Attributes for the light side of the card
    private final UnoColor lightColor;
    private final UnoValue lightValue;

    // Attributes for the dark side of the card
    private final UnoColor darkColor; //save them to toString();
    private final UnoValue darkValue;

    /**
     * Constructs an UnoCard with specified attributes for both sides.
     *
     * @param lightColor Color of the light side.
     * @param lightValue Value of the light side.
     * @param darkColor  Color of the dark side.
     * @param darkValue  Value of the dark side.
     */
    public UnoCard(UnoColor lightColor, UnoValue lightValue, UnoColor darkColor, UnoValue darkValue) {
        this.lightColor = lightColor;
        this.lightValue = lightValue;
        this.darkColor = darkColor;
        this.darkValue = darkValue;
    }

    /**
     * Gets the color of the light side of the card.
     *
     * @return The color of the light side.
     */
    public UnoColor getLightColor() {
        return this.lightColor;
    }

    /**
     * Gets the value of the light side of the card.
     *
     * @return The value of the light side.
     */
    public UnoValue getLightValue() {
        return this.lightValue;
    }

    /**
     * Gets the color of the dark side of the card.
     *
     * @return The color of the dark side.
     */
    public UnoColor getDarkColor() {
        return this.darkColor;
    }


    /**
     * Gets the value of the dark side of the card.
     *
     * @return The value of the dark side.
     */
    public UnoValue getDarkValue() {
        return this.darkValue;
    }


    /**
     * Gets the image file name based on the current side.
     *
     * @param currentSide The current side (light or dark).
     * @return The image file name of the card.
     */
    public String getImageFileName(UnoSide currentSide) {
        String color;
        String value;

        if (currentSide == UnoSide.LIGHT) {
            color = lightColor.toString().toLowerCase();
            value = lightValue.toString().toLowerCase();
        } else {
            color = darkColor.toString().toLowerCase();
            value = darkValue.toString().toLowerCase();
        }
        if (value.equals("draw_one") && (color.equals("orange") || color.equals("pink") || color.equals("teal")) || color.equals("purple")) {
            value = "draw_five";
        }
        return color + "_" + value + ".png";
    }

    /**
     * Returns a string representation of the UnoCard.
     *
     * @return A string representation of the UnoCard.
     */
    @Override
    public String toString() {
        return UnoGame.currentSide == UnoSide.LIGHT ? this.lightColor + " " + this.lightValue : this.darkColor + " " + this.darkValue;
    }

    /**
     * Checks if this UnoCard is equal to another object.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnoCard otherCard = (UnoCard) obj;
        return this.lightColor == otherCard.lightColor
                && this.lightValue == otherCard.lightValue
                && this.darkColor == otherCard.darkColor
                && this.darkValue == otherCard.darkValue;
    }

    /**
     * Generates a hash code for the UnoCard.
     *
     * @return The hash code for the UnoCard.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lightColor, lightValue, darkColor, darkValue);
    }

    public JsonObject saveAttributesToJson() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("Light Card", lightColor.toString())
                .add("Light Card's Value", lightValue.toString())
                .add("Dark Card", darkColor.toString())
                .add("Dark Card's Value",darkValue.toString())
                .build();
        return jsonObject;
    }


}
