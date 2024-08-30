package argonaut.midi;

import argonaut.app.App;
import com.jsyn.midi.MidiSynthesizer;
import argonaut.logging.LoggingEventType;
import argonaut.util.BytesUtil;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiReceiver implements Receiver {
    //Passes input between the controller and the synthesizer while performing any necessary logging.
    //When support for faders and knobs is added, it will route input for the MIDI controller to the appropriate input/control module.
    //Holds an instance of the synth it uses.

    protected MidiSynthesizer midiSynthesizer;

    public MidiReceiver(MidiSynthesizer synth) {
        this.midiSynthesizer = synth;
    }

    @Override
    public void close() {
        // NO-OP, for now.
        // May later call Close methods for any non-synth bits and bobs.
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        byte[] bytes = message.getMessage();
        midiSynthesizer.onReceive(bytes, 0, bytes.length);
        App.getApp().logMessage(BytesUtil.byteArrayToLogString(bytes), LoggingEventType.MIDI_IN);
    }
}
