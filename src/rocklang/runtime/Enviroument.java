package rocklang.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Enviroument {

    private Enviroument parent;
    private Map<String, Object> variables = new HashMap<>();


    public Enviroument(Enviroument parent) {
        this.parent = parent;
    }

    public Enviroument() {
        this.parent = null;
    }



    public boolean isRoot() {
        return parent == null;
    }

    public Enviroument root() {
        return parent == null ? this : parent.root();
    }

    public Object get(String variableName) {
        return variables.get(variableName);
    }


    public Object set(String variableName, Object value) {
        return variables.put(variableName, value);
    }

    public Object delete(String variableName) {
        return variables.remove(variableName);
    }


}
