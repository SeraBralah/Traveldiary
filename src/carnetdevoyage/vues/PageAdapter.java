package carnetdevoyage.vues;

import carnetdevoyage.carnet.Pages;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PageAdapter implements JsonDeserializer<Pages> {
    @Override
    public Pages deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Vérifier le type de page à désérialiser
        if (jsonObject.has("type") && jsonObject.get("type").getAsString().equals("PagePresentation")) {
            return context.deserialize(jsonObject, PagePresentation.class);
        } else {
            return context.deserialize(jsonObject, PageDestination.class);
        }
    }
}
