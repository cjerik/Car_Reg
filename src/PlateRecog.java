import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * PlateRecog is used for finding the platenumber of a car
 * and keeping track of speeding cars. When creating a
 * PlateRecog object specifiy the distance and speedlimit.
 *
 * It uses openalpr for the plate-recognintion, okhttp for
 * the http request and gson for reciving results.
 */
public class PlateRecog {

    Map<String, Date> plates;

    private final double MINTIMESECONDS;
    private final double DISTANCE;

    PlateRecog(double distance, int speedlimit) {
        DISTANCE = distance;
        MINTIMESECONDS = distance / (speedlimit / 3.6);
        plates = new HashMap<>();
    }

    /**
     * Stores plate and date for later check. Returns
     * boolean representing succes of string.
     * @param plate to image
     * @param date of checkpoint crossing
     * @return true/false if able to store
     */
    public boolean storePlate(String plate, Date date) {
        if (!containsPlate(plate)) {
            plates.put(plate, date);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recognises the platenumber if a car. When calling just send the
     * imagefile and findPlate will return the platenumber
     * @param file the image
     * @return a string containing the platenumber
     * @throws Exception if request fails 
     */
    public String findPlate(File file) {
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

        try {
            Response res = client.newCall(req).execute();

            JsonElement jelement = new JsonParser().parse(res.body().string());
            JsonObject  jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("results");
            jobject = jarray.get(0).getAsJsonObject();

            return jobject.get("plate").getAsString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if a car has been speednig between the checkpoints.
     * If it has method will return average speed in km/h, if
     * not it will return -1
     * @param plate of the car to check
     * @param date of second crossing
     * @return average speed if speeding, -1 if not
     */
    public Double isSpeeding(String plate, Date date) {
        double timeCar = (date.getTime() - plates.get(plate).getTime()) / 1000;

        if (timeCar < MINTIMESECONDS) {
            return (DISTANCE / timeCar) * 3.6;
        } else {
            return -1.0;
        }
    }

    private boolean containsPlate(String plate) {
        return plates.containsKey(plate);
    }
}
