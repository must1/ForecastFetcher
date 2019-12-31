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
    public Instant deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        String unixTimestamp = parser.getText().trim();

        return Instant.ofEpochMilli(
                new Date(TimeUnit.SECONDS.toMillis(Long.valueOf(unixTimestamp)))
                        .getTime());
    }
}