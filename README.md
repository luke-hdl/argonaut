# Argonaut
Casual, hobby-level, all-in-one digital groovebox software designed for use with any USB MIDI controller.

Argonaut is in a very early alpha state, but is fully usable with a MIDI controller.

## Usage
```commandline
mvn exec:java -D"exec.args"="example\Minilab3.synconfig"
```

to run using Maven (example config is for Minilab3). 

```commandline
 mvn package
```
to package for use on a Raspberry Pi. 

Generating a config is a bit annoying. Notes should play automatically, but to adjust faders, knobs, etc.; run the program and check the logs. This will let you see the channel that changing that fader, knob, etc. sends events on. At that point, you can alter the config to be on the correct channel by changing the first field on each line (the number) to the new channel number. 

## Roadmap:
1. Improvements to configuration:
    1. More functionalities configurable
    2. Live configuration file generation (for instance, setting pan by moving the same knob to the minimum and maximum.)
    3. Better configuration for logging files.
2. Improvements to saving/recording
    1. Saving a list of inputs to a log-file that can be used to replay a jam or make small adjustments ("I wish I hadn't amped the volume up quite that much here...")
    2. Better error logging
3. Better playback options
    1. Looping part of a playback - tied to 2i.
    2. Basic support for live sampling when a microphone is attached?