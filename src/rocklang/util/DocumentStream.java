package rocklang.util;

public class DocumentStream {

    private Document document;
    private int currentLineNumber = 0;
    private int currentOffset = 0;


    public DocumentStream(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public boolean hasMore() {
        return currentLineNumber < document.lineCount();
    }

    public char next() {
        return document.charAt(currentLineNumber, currentOffset);
    }

    public char read() {
        char ch = document.charAt(currentLineNumber, currentOffset);
        currentOffset++;
        String line = document.line(currentLineNumber);
        if (currentOffset >= line.length()) {
            currentLineNumber++;
            currentOffset = 0;
        }
        return ch;
    }

    public DocumentIndex checkPoint() {
        return new DocumentIndex(document, currentLineNumber, currentOffset);
    }

    public void rollBack(DocumentIndex checkPoint) {
        this.currentLineNumber = checkPoint.getLineNumber();
        this.currentOffset = checkPoint.getOffset();
    }

    public DocumentRange rangeFrom(DocumentIndex index) {
        return new DocumentRange(document, index.getLineNumber(), index.getOffset(), currentLineNumber, currentOffset);
    }


    public void skipSpace() {
        while (hasMore() && Character.isWhitespace(next())) {
            read();
        }
    }

    public void skipToNextLine() {
        currentLineNumber++;
        currentOffset = 0;
    }
}
