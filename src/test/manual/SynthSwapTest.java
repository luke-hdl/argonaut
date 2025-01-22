package manual;

import argonaut.config.SynthConfig;
import argonaut.voices.KnownVoice;
import utils.TestSynthesizer;

import java.io.File;

public class SynthSwapTest  {
    protected void theTest() {
        System.out.println("Playing startup noise 1...");
        TestSynthesizer synth = new TestSynthesizer(SynthConfig.parse(new File("example\\Minilab3.synconfig")), KnownVoice.DUAL_OSCILLATOR);
        synth.start();
        synth.playStartUpSound();
        System.out.println("Swapping and playing startup noise 2...");
        synth.restart(KnownVoice.WAVE_SHAPING_VOICE);
        synth.playStartUpSound();
    }

    public static void main(String[] args){
        new SynthSwapTest().theTest();
    }
}
