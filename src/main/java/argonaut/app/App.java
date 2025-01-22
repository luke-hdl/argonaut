package argonaut.app;

import argonaut.config.SynthConfig;
import argonaut.logging.Log;
import argonaut.logging.LoggingEventType;
import argonaut.midi.MidiKeyboardInputManager;
import argonaut.voices.KnownVoice;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class App {
    protected static final App theApp = new App();

    protected MidiKeyboardInputManager inputManager;

    protected HashSet<Log> logs = new HashSet<>();

    protected void addLog(Log log) {
        logs.add(log);
    }

    public void logMessage(String message, LoggingEventType type) {
        try {
            for (Log log : logs) {
                log.log(message + "\r\n", type);
            }
        } catch (IOException exception) {
            exception.printStackTrace(); //don't cancel
        }
    }

    public static App getApp() {
        return theApp;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please specify config file.");
            return;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Log log : theApp.logs) {
                try {
                    log.close();
                } catch (Exception e) {
                    //doesn't matter, we're just closing logs before shut-off.
                }
            }
        }));

        try {
            theApp.addLog(new Log(System.out, LoggingEventType.MIDI_IN, LoggingEventType.ERROR, LoggingEventType.DEBUG));
            theApp.inputManager = new MidiKeyboardInputManager(SynthConfig.parse(new File(args[0])), KnownVoice.DUAL_OSCILLATOR);
            theApp.inputManager.start();
        } catch (Exception e) {
            theApp.logMessage(e.toString(), LoggingEventType.ERROR);
            e.printStackTrace();
        }
    }
}
