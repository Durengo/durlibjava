package io.github.durengo.durlib.people;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 * This enum has 2 simple options to select the type of crew member.
 * The object also has XML attributes for marshalling and unmarshalling.
 */
@XmlEnum(String.class)
public enum CrewMemberType {
    Pilot("Pilot"), Stewardess("Stewardess");
    private final String string;

    /**
     * @param name the name of the type.
     */
    CrewMemberType(String name) {
        string = name;
    }

    /**
     * @return the type of crew member as string.
     */
    @Override
    public String toString() {
        return string;
    }
}
