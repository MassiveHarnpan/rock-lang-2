package rocklang.util;

public class DocumentIndex {

    private Document document;
    private int lineNumber;
    private int offset;

    public DocumentIndex(Document document, int lineNumber, int offset) {
        this.document = document;
        this.lineNumber = lineNumber;
        this.offset = offset;
    }

    public Document getDocument() {
        return document;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "(" + document.getName()+ ": " + lineNumber + ", " + offset + ")";
    }
}
