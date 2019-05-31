package rocklang.ast;

import rocklang.token.Token;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASTFactory {

    protected Constructor<? extends AST> constructor;
    protected boolean astLeafFactory;

    public ASTFactory(Constructor<? extends AST> constructor, boolean astLeafFactory) {
        this.constructor = constructor;
        this.astLeafFactory = astLeafFactory;
    }

    public AST make(List<AST> res) {
        if (astLeafFactory) {
            return null;
        }
        try {
            AST[] asts = res.toArray(new AST[res.size()]);
            return constructor.newInstance((Object) asts);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AST make(Token token) {
        if (!astLeafFactory) {
            return null;
        }
        try {
            return constructor.newInstance(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }






    private static Map<Class<? extends  AST>, ASTFactory> factories = new HashMap();


    public static ASTFactory of(Class<? extends AST> clazz) {
        ASTFactory factory = factories.get(clazz);
        if (factory == null) {
            factory = createFactory(clazz);
            factories.put(clazz, factory);
        }
        return factory;
    }

    private static ASTFactory createFactory(Class<? extends  AST> clazz) {
        Constructor<? extends AST> constructor;
        try {
            constructor = clazz.getConstructor(AST[].class);
            if (constructor != null) {
                return new ASTFactory(constructor, false);
            }
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        }
        try {
            constructor = clazz.getConstructor(Token.class);
            if (constructor != null) {
                return new ASTFactory(constructor, true);
            }
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        }
        return null;
    }



}
