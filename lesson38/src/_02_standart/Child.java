package _02_standart;

import java.io.Serializable;
import java.util.StringJoiner;

public class Child extends Parent implements Serializable {
    int childField;

    public Child(int childField) {
        System.out.println("Child constructor");
        this.childField = childField;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Child.class.getSimpleName() + "[", "]")
                .add("childField=" + childField)
                .add("parentField=" + parentField)
                .toString();
    }
}
