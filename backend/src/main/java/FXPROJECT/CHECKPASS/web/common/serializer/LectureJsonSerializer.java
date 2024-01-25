package FXPROJECT.CHECKPASS.web.common.serializer;

import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LectureJsonSerializer extends JsonSerializer<LectureInformation> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void serialize(LectureInformation lectureInformation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String json = objectMapper.writeValueAsString(lectureInformation);
        jsonGenerator.writeRawValue(json);
    }
}