package argonaut.controls;

import com.jsyn.util.MultiChannelSynthesizer;
import argonaut.midi.ControllableMidiSynthesizer;
import argonaut.midi.ParsedMidiDetails;

import java.util.function.Consumer;

//Wraps a Consumer of int[] that's connected to a synthesizer.
//the int[] are the four integers parsed out in the controlChange method.
public abstract class ControllerFunction implements Consumer<ParsedMidiDetails> {
    protected ControllableMidiSynthesizer controllableMidiSynthesizer;
    protected MultiChannelSynthesizer synthControlled;

    public void registerSynth(ControllableMidiSynthesizer controllableMidiSynthesizer, MultiChannelSynthesizer synthControlled) {
        this.controllableMidiSynthesizer = controllableMidiSynthesizer;
        this.synthControlled = synthControlled;
    }

    @Override
    public abstract void accept(ParsedMidiDetails midiMessage);
}
