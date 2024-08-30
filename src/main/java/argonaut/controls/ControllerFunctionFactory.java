package argonaut.controls;

import argonaut.app.App;
import argonaut.logging.LoggingEventType;
import argonaut.midi.ParsedMidiDetails;

import java.io.IOException;

public class ControllerFunctionFactory {
    public static ControllerFunction volumeFunction() {
        return new ControllerFunction() {
            @Override
            public void accept(ParsedMidiDetails midiMessage) {
                //jsyn assumes that your volume raw input is between 34 and ~254
                //I assume there's a reason for that I'm too dumb to know, like "there's a volume standard"
                //At some point, I'll add to SynthConfig an option for a min amd max value for the input, to better adjust
                //for now it's hardcoded to my keyboard's range.
                //34-127

                synthControlled.setVolume(midiMessage.getChannel(), Math.max(34, midiMessage.getValue() * 2 - 2) * 0.007874015748031496d);
            }
        };
    }

    public static ControllerFunction timbreFunction() {
        return new ControllerFunction() {
            @Override
            public void accept(ParsedMidiDetails midiMessage) {
                synthControlled.setTimbre(midiMessage.getChannel(), midiMessage.getValue() * 0.007874015748031496d);
            }
        };
    }

    public static ControllerFunction panFunction() {
        return new ControllerFunction() {
            @Override
            public void accept(ParsedMidiDetails midiMessage) {
                synthControlled.setPan(midiMessage.getChannel(), midiMessage.getValue() * 0.007874015748031496d * 2d - 1d);
            }
        };
    }

    public static ControllerFunction vibratoFunction() {
        return new ControllerFunction() {
            @Override
            public void accept(ParsedMidiDetails midiMessage) {
                synthControlled.setVibratoDepth(midiMessage.getChannel(), midiMessage.getValue() * 0.0007874015748031496d);
            }
        };
    }

    public static ControllerFunction shutdownSystemFunction() {
        return new ControllerFunction() {
            @Override
            public void accept(ParsedMidiDetails midiMessage) {
                try {
                    Runtime.getRuntime().exec(new String[]{"sudo", "shutdown", "-h", "now"});
                } catch (IOException e) {
                    App.getApp().logMessage(e.getMessage(), LoggingEventType.ERROR);
                }
            }
        };
    }
}
