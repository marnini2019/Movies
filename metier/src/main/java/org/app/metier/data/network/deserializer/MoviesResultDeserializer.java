package org.app.metier.data.network.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import org.app.metier.data.entities.Movies;

public class MoviesResultDeserializer implements JsonDeserializer<List<Movies>> {

    public static final String RESULTS_FIELD = "results";

    @Override
    public List<Movies> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement results = json.getAsJsonObject().get(RESULTS_FIELD);

        return new Gson().fromJson(results, MoviesResultDeserializer.getRetrofitMovieEntitiesListType());
    }

    public static Type getRetrofitMovieEntitiesListType() {
        return new TypeToken<List<Movies>>() {
        }.getType();
    }
}
