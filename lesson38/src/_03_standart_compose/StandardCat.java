package _03_standart_compose;

import java.io.Serializable;
import java.util.StringJoiner;

public class StandardCat implements Serializable {

    static final long serialVersionUID = 1L;
    static String staticField = "Static";
    final String name;
    StandardBox box;

    public StandardCat(String name, StandardBox box) {
        this.name = name;
        this.box = box;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StandardCat.class.getSimpleName() + "[", "]")
                .add("static='" + staticField + "'")
                .add("name='" + name + "'")
                .add("box=" + box)
                .toString();
    }
}
