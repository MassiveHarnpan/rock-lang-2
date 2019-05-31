package rocklang.util;

public class DocumentRange {

    private Document document;
    private int startLineNumber;
    private int startOffset;
    private int endLineNumber;
    private int endOffset;


    public DocumentRange(Document document, int startLineNumber, int startOffset, int endLineNumber, int endOffset) {
        this.document = document;
        this.startLineNumber = startLineNumber;
        this.startOffset = startOffset;
        this.endLineNumber = endLineNumber;
        this.endOffset = endOffset;
    }

    public Document getDocument() {
        return document;
    }

    public int getStartLineNumber() {
        return startLineNumber;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndLineNumber() {
        return endLineNumber;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public String cut() {
        return document.range(startLineNumber, startOffset, endLineNumber, endOffset);
    }

    @Override
    public String toString() {
        return "(" + document.getName()+ ": " + startLineNumber + ", " + startOffset + " - " + endLineNumber + ", " + endOffset + ")";
    }
}
