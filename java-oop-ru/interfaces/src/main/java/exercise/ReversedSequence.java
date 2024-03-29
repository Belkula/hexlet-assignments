package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String originalString;

    public ReversedSequence(String str) {
        this.originalString = str;
    }

    @Override
    public int length() {
        return originalString.length();
    }

    @Override
    public char charAt(int index) {
        return originalString.charAt(length() - index - 1);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end > length() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = end - 1; i >= start; i--) {
            sb.append(originalString.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(originalString);
        return sb.reverse().toString();
    }
}

// END
