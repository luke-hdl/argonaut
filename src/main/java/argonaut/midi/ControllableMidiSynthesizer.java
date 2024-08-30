package argonaut.midi;

import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;
import argonaut.controls.ControllerFunction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ControllableMidiSynthesizer extends MidiSynthesizer {

    protected Map<Integer, List<ControllerFunction>> controllers;
    protected MultiChannelSynthesizer parentsSynth;
    //This is a janky way to store access to the private MultiChannelSynthesizer in the parent object,
    //since the pointer to it is only passed once, in the constructor, so it'll be up to date.
    //This isn't really a "safe and sane" way to do this, but it seemed like a reasonable solution to avoid
    //way messier options.

    public ControllableMidiSynthesizer(MultiChannelSynthesizer multiChannelSynthesizer) {
        super(multiChannelSynthesizer);
        parentsSynth = multiChannelSynthesizer;
        controllers = new HashMap<>();
    }

    public void addControl(ControllerFunction function, int onKey) {
        controllers.putIfAbsent(onKey, new LinkedList<>());
        controllers.get(onKey).add(function);
        function.registerSynth(this, parentsSynth);
    }

    @Override
    public void controlChange(int var1, int var2, int var3) {
        if (controllers.containsKey(var2)) {
            ParsedMidiDetails details = new ParsedMidiDetails(var1, var2, var3);
            controllers.get(var2).forEach(e -> e.accept(details));
        }
    }
}
