package utils;

// This file takes heavy influence from Phil Burk's example in the JSyn git repo.
// I would like to thank him for the influence he's had on my ability to have a good time and make funny sounds.

import argonaut.midi.ControllableMidiSynthesizer;
import argonaut.midi.MidiReceiver;
import argonaut.voices.KnownVoice;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.devices.javasound.MidiDeviceTools;
import com.jsyn.unitgen.LineOut;
import com.jsyn.util.MultiChannelSynthesizer;
import com.jsyn.util.VoiceDescription;
import argonaut.config.SynthConfig;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.Receiver;

import static java.lang.Thread.sleep;

public class TestSynthesizer {

    private static final int NUM_CHANNELS = 16;
    private static final int VOICES_PER_CHANNEL = 3;

    protected Synthesizer synth;
    protected SynthConfig config;
    protected LineOut lineOut;
    protected ControllableMidiSynthesizer midiSynthesizer;
    protected MultiChannelSynthesizer multiSynth;
    protected MidiDevice keyboard;
    protected Receiver receiver;

    public TestSynthesizer(SynthConfig aConfig, KnownVoice voice) {
        this.config = aConfig;
        setupSynth(voice.getVoiceDescription());
    }

    public void restart(KnownVoice withVoice) {
        setupSynth(withVoice.getVoiceDescription());
        start();
    }

    public void start() {
        synth.start();
        lineOut.start();
        keyboard = MidiDeviceTools.findKeyboard();
        receiver = new MidiReceiver(midiSynthesizer);
    }

    public void playStartUpSound() {
        try {
            byte[] bytes1 = {(byte) -112, (byte) 59, (byte) 115};
            byte[] bytes2 = {(byte) -112, (byte) 62, (byte) 115};
            byte[] bytes3 = {(byte) -112, (byte) 65, (byte) 115};
            midiSynthesizer.onReceive(bytes1, 0, bytes1.length);
            midiSynthesizer.onReceive(bytes2, 0, bytes2.length);
            midiSynthesizer.onReceive(bytes3, 0, bytes3.length);
            sleep(1000); // This should be a better sound but it gets the job done.
            bytes1[0] = -128;
            bytes2[0] = -128;
            bytes3[0] = -128;
            bytes1[2] = 0;
            bytes2[2] = 0;
            bytes3[2] = 0;
            midiSynthesizer.onReceive(bytes1, 0, bytes1.length);
            midiSynthesizer.onReceive(bytes2, 0, bytes2.length);
            midiSynthesizer.onReceive(bytes3, 0, bytes3.length);
        } catch (Exception e) {
            //suppress.
        }
    }

    private void setupSynth(VoiceDescription description) {
        synth = JSyn.createSynthesizer();
        multiSynth = new MultiChannelSynthesizer();
        final int startChannel = 0;
        multiSynth.setup(synth, startChannel, NUM_CHANNELS, VOICES_PER_CHANNEL, description);
        midiSynthesizer = new ControllableMidiSynthesizer(multiSynth);

        config.apply(midiSynthesizer);

        synth.add(lineOut = new LineOut());
        multiSynth.getOutput().connect(0, lineOut.input, 0);
        multiSynth.getOutput().connect(1, lineOut.input, 1);
    }

}