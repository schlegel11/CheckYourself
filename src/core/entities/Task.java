package core.entities;

import java.awt.*;
import java.io.Serializable;

public class Task implements Serializable {

    private final String name;
    private final Color farbe;

    public Task(String name, Color farbe) {
        this.name = name;
        this.farbe = farbe;
    }

    public String getName() {
        return name;
    }

    public Color getFarbe() {
        return farbe;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((farbe == null) ? 0 : farbe.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (farbe == null) {
            if (other.farbe != null)
                return false;
        } else if (!farbe.equals(other.farbe))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
