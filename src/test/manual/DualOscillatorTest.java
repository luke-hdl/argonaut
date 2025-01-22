package manual;

import argonaut.config.SynthConfig;
import argonaut.voices.KnownVoice;
import utils.TestSynthesizer;

import java.io.File;

public class DualOscillatorTest  {
    protected void theTest() {
        System.out.println("Playing startup noise...");
        TestSynthesizer synth = new TestSynthesizer(SynthConfig.parse(new File("example\\Minilab3.synconfig")), KnownVoice.DUAL_OSCILLATOR);
        synth.start();
        synth.playStartUpSound();
    }

    public static void main(String[] args){
        new DualOscillatorTest().theTest();
    }
}
