package argonaut.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Log implements AutoCloseable {
    protected OutputStream stream;
    protected Set<LoggingEventType> loggableTypes;

    public Log(OutputStream stream, LoggingEventType... allowedTypes) {
        this.stream = stream;
        loggableTypes = new HashSet<>();
        loggableTypes.addAll(Arrays.stream(allowedTypes).toList());
    }

    public void log(String message, LoggingEventType type) throws IOException {
        if (loggableTypes.contains(type)) {
            stream.write(message.getBytes());
        }
    }

    @Override
    public void close() throws Exception {
        stream.close();
    }
}
