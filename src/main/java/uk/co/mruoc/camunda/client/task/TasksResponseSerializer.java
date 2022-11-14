package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class TasksResponseSerializer extends StdSerializer<TasksResponse> {

    protected TasksResponseSerializer() {
        super(TasksResponse.class);
    }

    @Override
    public void serialize(TasksResponse response, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartArray();
        for (Task task : response.getTasks()) {
            json.writeObject(task);
        }
        json.writeEndArray();
    }
}
