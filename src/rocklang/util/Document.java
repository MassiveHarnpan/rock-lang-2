package rocklang.util;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Document {

    public static List<String> explode(String text) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            builder.append(ch);
            if (ch == '\n') {
                list.add(builder.toString());
                builder.delete(0, builder.length());
            }
        }
        if (builder.length() > 0) {
            builder.append('\n');
            list.add(builder.toString());
        }
        return list;
    }

    private String name;
    private List<String> lines = new ArrayList<>();

    public Document(String name, List<String> lines) {
        this.name = name;
        this.lines.addAll(lines);
    }

    public Document(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }

    public Document append(String text) {
        this.lines.addAll(explode(text));
        return this;
    }

    public Document appendLine(String line) {
        if (!line.endsWith("\n")) {
            line = line + '\n';
        }
        this.lines.add(line);
        return this;
    }

    public Document append(Collection<String> lines) {
        this.lines.addAll(lines);
        return this;
    }

    public char charAt(int lineNumber, int offset) {
        String line = this.lines.get(lineNumber);
        return line.charAt(offset);
    }

    public char charAt(DocumentIndex index) {
        String line = this.lines.get(index.getLineNumber());
        return line.charAt(index.getOffset());
    }

    public String line(int lineNumber) {
        return this.lines.get(lineNumber);
    }

    public String range(int startLineNumber, int startOffset, int endLineNumber, int endOffset) {
        if (startLineNumber == endLineNumber) {
            return lines.get(startLineNumber).substring(startOffset, endOffset);
        }
        StringBuilder builder = new StringBuilder();
        String line = lines.get(startLineNumber);
        builder.append(line.substring(startOffset));
        for (int i = startLineNumber + 1; i < endLineNumber; i++) {
            builder.append(lines.get(i));
        }
        line = lines.get(endLineNumber);
        builder.append(line, 0, endOffset);
        return builder.toString();
    }

    public String range(DocumentRange range) {
        return range(range.getStartLineNumber(), range.getStartOffset(), range.getEndLineNumber(), range.getEndOffset());
    }


    public int lineCount() {
        return lines.size();
    }
}
