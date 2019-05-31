package rocklang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class DocumentReaderUtil {


    public static Document read(String name, Reader reader) throws IOException {
        Document document = new Document(name);

        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            document.appendLine(line);
        }
        return document;
    }

}
