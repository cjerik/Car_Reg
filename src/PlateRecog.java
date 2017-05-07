import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * PlateRecog is used for finding the platenumber of a car
 * and keeping track of speeding cars.
 *
 * It uses openalpr for the plate-recognintion, okhttp for
 * the http request and gson for reciving results.
 */
public class PlateRecog {

    Map<String, Date> plates;

    PlateRecog() {
        plates = new HashMap<>();
    }

    /**
     * Stores plate and date for later check. Returns
     * boolean representing succes of soring. Can return
     * false if APII faild also
     * @param path to image
     * @param date of checkpoint crossing
     * @return true/false if able to store
     */
    public boolean storePlate(String path, Date date) {
            File file = new File(path);
            try {
                String plate = findPlate(file);
                if (!containsPlate(plate)) {
                    plates.put(plate, date);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

    /**
     * Recognises the platenumber if a car. When calling just send the
     * imagefile and findPlate will return the platenumber
     * @param file the image
     * @return a string containing the platenumber
     * @throws Exception if request fails 
     */
    private String findPlate(File file) throws Exception {
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

    private boolean containsPlate(String plate) {
        return plates.containsKey(plate);
    }
}
