package argonaut.config;

import argonaut.exceptions.ConfigFileParseException;

public class ConfigRecord {
    protected int onKey;
    protected ControllerFunctionConfigFileOption function;
    protected String friendlyName;

    public static ConfigRecord parse(String aString) {
        ConfigRecord ret = new ConfigRecord();
        String[] keys = aString.split("\\|");
        if (keys.length != 3) {
            throw new ConfigFileParseException(aString);
        }
        try {
            ret.onKey = Integer.parseInt(keys[0]);
        } catch (NumberFormatException e) {
            throw new ConfigFileParseException(e, aString);
        }
        ret.function = ControllerFunctionConfigFileOption.valueOf(keys[1]);
        ret.friendlyName = keys[2];
        return ret;
    }

    @Override
    public String toString() {
        return onKey + "|" + function + "|" + friendlyName;
    }

    public int getOnKey() {
        return onKey;
    }

    public ControllerFunctionConfigFileOption getFunction() {
        return function;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
