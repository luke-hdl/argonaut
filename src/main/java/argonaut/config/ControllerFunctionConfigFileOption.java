package argonaut.config;

import argonaut.controls.ControllerFunction;
import argonaut.controls.ControllerFunctionFactory;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public enum ControllerFunctionConfigFileOption {
    //These are used in ConfigRecord.parse to convert them by name into the function they represent.
    // Because reflection can't be easily avoided in the situation,
    // they are retrieved reflectively, thus the need to @SuppressWarnings.

    PAN(ControllerFunctionFactory::panFunction),
    TIMBRE(ControllerFunctionFactory::timbreFunction),
    VIBRATO(ControllerFunctionFactory::vibratoFunction),
    VOLUME(ControllerFunctionFactory::volumeFunction),
    SHUTDOWN_LINUX_SYSTEM(ControllerFunctionFactory::shutdownSystemFunction);

    final Supplier<ControllerFunction> function;

    ControllerFunctionConfigFileOption(Supplier<ControllerFunction> function) {
        this.function = function;
    }
}