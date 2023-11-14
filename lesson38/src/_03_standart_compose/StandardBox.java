package _03_standart_compose;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class StandardBox implements Serializable {
    List<String> things;

    public StandardBox(List<String> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StandardBox.class.getSimpleName() + "[", "]")
                .add("things=" + things)
                .toString();
    }
}
