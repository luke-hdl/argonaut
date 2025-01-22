package argonaut.voices;

import com.jsyn.instruments.DrumWoodFM;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.instruments.NoiseHit;
import com.jsyn.instruments.SubtractiveSynthVoice;
import com.jsyn.instruments.WaveShapingVoice;
import com.jsyn.util.VoiceDescription;

public enum KnownVoice {
    DUAL_OSCILLATOR(DualOscillatorSynthVoice.getVoiceDescription()),
    DRUM(DrumWoodFM.getVoiceDescription()),
    NOISE_HIT(NoiseHit.getVoiceDescription()),
    SUBTRACTIVE_SYNTH_VOICE(SubtractiveSynthVoice.getVoiceDescription()),
    WAVE_SHAPING_VOICE(WaveShapingVoice.getVoiceDescription());

    private final VoiceDescription voiceDescription;

    public VoiceDescription getVoiceDescription() {
        return voiceDescription;
    }

    KnownVoice(VoiceDescription voiceDescription) {
        this.voiceDescription = voiceDescription;
    }
}
