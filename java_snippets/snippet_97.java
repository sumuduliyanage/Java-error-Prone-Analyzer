
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandle implements HttpResponse.BodyHandler<JsonObject> {
    @Override
    public HttpResponse.BodySubscriber<JsonObject> apply(HttpResponse.ResponseInfo ri) {
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);
        return HttpResponse.BodySubscribers.mapping(
            upstream, body -> Json.createReader(new StringReader(body)).readObject());
        }
    }

