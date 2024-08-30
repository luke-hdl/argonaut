package argonaut.config;

import argonaut.app.App;
import argonaut.exceptions.ConfigFileParseException;
import argonaut.logging.LoggingEventType;
import argonaut.midi.ControllableMidiSynthesizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SynthConfig {
    //Parses and applies .synconfig files.
    //I'd like to add a functionality to help set these up. Shouldn't be too difficult, but I haven't done it yet.
    protected List<ConfigRecord> parsedRecords = new ArrayList<>();

    public static SynthConfig parse(File aFile) {
        SynthConfig aConfig = new SynthConfig();
        try (BufferedReader reader = new BufferedReader(new FileReader(aFile))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                aConfig.parsedRecords.add(ConfigRecord.parse(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ConfigFileParseException(e, "Could not open or read file.");
        }
        return aConfig;
    }

    public void apply(ControllableMidiSynthesizer synth) {
        //This should possibly be adjusted to take a higher-up object,
        //so that the synth setup performed in MidiKeyboardInputManager can be affected here.
        //For now it just adds logic to faders and knobs, though.
        for (ConfigRecord record : parsedRecords) {
            synth.addControl(record.getFunction().function.get(), record.getOnKey());
            App.getApp().logMessage("Added function " + record.getFunction() + " ON: " + record.getOnKey(), LoggingEventType.DEBUG);
        }
    }
}
