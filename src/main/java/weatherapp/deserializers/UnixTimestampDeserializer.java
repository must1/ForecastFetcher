package weatherapp.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnixTimestampDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser parser, DeserializationContext context) {
        String unixTimestamp;
        try {
            unixTimestamp = parser.getText().trim();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot access textual representation of the current token in " +
                    "UnixTimestampDeserializer due to " + e.getMessage());
        }

        return Instant.ofEpochMilli(
                new Date(TimeUnit.SECONDS.toMillis(Long.valueOf(unixTimestamp)))
                        .getTime());
    }
}