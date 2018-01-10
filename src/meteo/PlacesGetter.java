package meteo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javafx.concurrent.Task;

public class PlacesGetter extends Task<Map<String,String>>{
    private final static String GET_PLACES = "ption\" : \"([^\"]+)[\\S\\s]+?_id\" : \"([^\"]+)";
    private final static String GOOGLE_KEY = "AIzaSyC-8BYDiFY6FFxygTUID6iVi8214jnCpps";

    private final Meteo father;
    private final String place;

    public PlacesGetter(String place, Meteo father) throws UnsupportedEncodingException {
            this.father = father;
            this.place = URLEncoder.encode(place, "UTF-8");
    }
	
    @Override
    protected Map<String,String> call() throws Exception {
        return Utils.getFromUrl(GET_PLACES, "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + place + "&types=geocode&key=" + GOOGLE_KEY);
    }
}
