import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.File;

/**
 * PlateRecog is used for finding the platenumber of a car.
 * It uses openalpr for the plate-recognintion, okhttp for
 * the http request and gson for reciving results.
 */
public class PlateRecog {
    /**
     * Recognises the platenumber if a car. When calling just send the
     * imagefile and findPlate will return the platenumber
     * @param file the image
     * @return a string containing the platenumber
     * @throws Exception if request fails 
     */
    public String findPlate(File file) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody r = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.toString(), RequestBody.create(MediaType.parse("text/csv"), file))
                .build();

        Request req = new Request.Builder()
                .url("https://api.openalpr.com/v2/recognize?secret_key=sk_73db2212a4160f33b0213ef4&" +
                        "recognize_vehicle=1&country=eu&return_image=0&topn=1")
                .post(r)
                .build();

        Response res = client.newCall(req).execute();

        JsonElement jelement = new JsonParser().parse(res.body().string());
        JsonObject  jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("results");
        jobject = jarray.get(0).getAsJsonObject();

        return jobject.get("plate").getAsString();
    }
}
