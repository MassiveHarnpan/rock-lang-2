package rocklang.util;

import java.util.List;

public class Utils {

    public static void cutList(List list, int size) {
        while (list.size() > size) {
            list.remove(list.size() - 1);
        }
    }

}
