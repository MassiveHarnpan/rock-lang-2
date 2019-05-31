package rocklang.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

public class FormatStream {

    private PrintStream writer;
    private String indent = "    ";
    private int currentLevel = 0;

    public FormatStream(PrintStream writer, String indent) {
        this.writer = writer;
        this.indent = indent;
    }

    public FormatStream levelUp() {
        currentLevel++;
        return this;
    }

    public FormatStream levelDown() {
        currentLevel--;
        return this;
    }

    public FormatStream print(String s) throws IOException {
        writer.print(s);
        return this;
    }

    public FormatStream newLine() throws IOException {
        writer.println();
        printIndent();
        return this;
    }

    private void printIndent() throws IOException {
        for (int i = 0; i < currentLevel; i++) {
            writer.print(indent);
        }
    }

}
