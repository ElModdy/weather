package meteo;

import java.util.Map;
import javafx.concurrent.Task;

public class MeteoGetter extends Task<Map<String,String>>{
    private final static String GET_COORD = "tion\"[\\S\\s]+?\"(lat)\" : ([^,]+)[\\S\\s]+?(lng)\" : (\\S+)";
    private final static String GET_WEAT = "(description)\":\"([^\"]+)[\\s\\S]+(temp)\":([^,]+)";
    private final static String GOOGLE_KEY = "AIzaSyC-8BYDiFY6FFxygTUID6iVi8214jnCpps";
    private final static String OPENWEAT_KEY = "32ef75f0d4fc04941fe84caa7dc581d4";

    private final Meteo father;
    private final String placeId;

    public MeteoGetter(String placeId, Meteo father) {
            this.father = father;
            this.placeId = placeId;
    }

    @Override
    protected Map<String, String> call() throws Exception {
        Map<String, String> coord = Utils.getFromUrl(GET_COORD, "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeId +
					"&key=" + GOOGLE_KEY);
        return Utils.getFromUrl(GET_WEAT, "http://api.openweathermap.org/data/2.5/weather?units=metric&lat=" + coord.get("lat") + 
					"&lon=" + coord.get("lng") + 
					"&appid=" + OPENWEAT_KEY);
    }
}
