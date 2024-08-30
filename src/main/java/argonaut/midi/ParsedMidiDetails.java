package argonaut.midi;

public class ParsedMidiDetails {
    protected int channel;
    protected int input;
    protected int value;

    public ParsedMidiDetails(int channel, int input, int value) {
        this.channel = channel;
        this.input = input;
        this.value = value;
    }

    public int getChannel() {
        return channel;
    }

    public int getInput() {
        return input;
    }

    public int getValue() {
        return value;
    }
}
