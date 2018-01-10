package meteo;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Meteo {
    ExecutorService executor;
    UIMeteoController father;

    public Meteo(UIMeteoController father){
        this.father = father;
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void getWeat(String placeId) {
        MeteoGetter meteoGetter = new MeteoGetter(placeId, this);
        meteoGetter.setOnSucceeded((e) -> setWeat(meteoGetter.getValue()));
        executor.execute(meteoGetter);
    }

    public void setWeat(Map<String, String> weat) {
        father.setDesc(weat.get("description"));
        father.setTemp(weat.get("temp"));
    }

    public void getPlaces(String place) throws UnsupportedEncodingException {
        PlacesGetter placeGetter = new PlacesGetter(place, this);
        placeGetter.setOnSucceeded((e) -> setPlaces(placeGetter.getValue()));
        executor.execute(placeGetter);
    }

    public void setPlaces(Map<String, String> places) {
        father.setPlaces(places);
    }
}
