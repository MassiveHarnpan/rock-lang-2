package rocklang.runtime;

import rocklang.util.Functions;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Environment parent;
    private Map<String, Rock> variables = new HashMap<>();


    public Environment(Environment parent) {
        this.parent = parent;
    }

    public Environment() {
        this.parent = null;
    }



    public boolean isRoot() {
        return parent == null;
    }

    public Environment root() {
        return parent == null ? this : parent.root();
    }

    public Environment find(String variableName) {
        if (this.variables.containsKey(variableName)) {
            return this;
        }
        if (isRoot()) {
            return this;
        } else {
            return parent.find(variableName);
        }
    }



    public boolean has(String variableName) {
        return find(variableName).variables.containsKey(variableName);
    }

    public Rock get(String variableName) {
        return find(variableName).variables.get(variableName);
    }


    public Rock set(String variableName, Rock value) {
        return find(variableName).variables.put(variableName, value);
    }

    public Rock delete(String variableName) {
        return find(variableName).variables.remove(variableName);
    }




    public static Environment getDefaultEnvironment() {
        Environment environment = new Environment();
        environment.set("print", new RockFunction(environment, new String[] {"msg"}, Functions.PRINT));
        environment.set("time", new RockFunction(environment, new String[0], Functions.TIME));
        return environment;
    }
}
