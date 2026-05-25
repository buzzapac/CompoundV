package com.compoundv;

/**
 * All available superpowers that can be granted by Temp V or Compound V.
 */
public enum PowerType {

    FLIGHT(
            "Flight",
            "Soar freely through the sky",
            "✈"
    ),
    SUPER_SPEED(
            "Super Speed",
            "Move at incredible velocity — Passive",
            "⚡"
    ),
    LASER_EYES(
            "Laser Eyes",
            "Fire laser beams from your eyes — Right-click (empty hand)",
            "\uD83D\uDD25"
    ),
    TELEPORTATION(
            "Teleportation",
            "Teleport up to 30 blocks forward — Sneak + Right-click (empty hand)",
            "\uD83C\uDF00"
    ),
    INVISIBILITY(
            "Invisibility",
            "Become completely unseen — Passive",
            "\uD83D\uDC41"
    ),
    SIZE_SHIFTING(
            "Size Shifting",
            "Toggle between tiny and giant form — /cv size",
            "\u2696"
    ),
    SUPER_STRENGTH(
            "Super Strength",
            "Incredible power & leap ability — Passive",
            "\uD83D\uDCAA"
    );

    private final String displayName;
    private final String description;
    private final String icon;

    PowerType(String displayName, String description, String icon) {
        this.displayName = displayName;
        this.description = description;
        this.icon = icon;
    }

    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
    public String getIcon() { return icon; }

    /** Friendly name suitable for commands. */
    public String key() { return name().toLowerCase(); }

    /** Look up by case-insensitive name. */
    public static PowerType fromKey(String key) {
        for (PowerType pt : values()) {
            if (pt.name().equalsIgnoreCase(key) || pt.displayName.equalsIgnoreCase(key)) {
                return pt;
            }
        }
        return null;
    }
}
