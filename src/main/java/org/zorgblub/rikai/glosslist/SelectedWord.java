package org.zorgblub.rikai.glosslist;

/**
 * Created by alex on 10/14/14.
 */
public class SelectedWord {

    private CharSequence text;
    private CharSequence contextSentence;
    private TextPosition position;

    public SelectedWord(TextPosition position, CharSequence text, CharSequence contextSentence) {
        this.text = text;
        this.contextSentence = contextSentence;
        this.position = position;
    }

    public SelectedWord(TextPosition position, CharSequence text){
        this(null, text, "No context sentence");
    }

    public SelectedWord(String word) {
        this(null, word, "No context sentence");
    }

    public TextPosition getPosition() {
        return position;
    }

    public CharSequence getText() {

        if ( text == null ) {
            return "";
        }

        return text;
    }

    public CharSequence getContextSentence() {
        return contextSentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectedWord that = (SelectedWord) o;

        return text.equals(that.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}