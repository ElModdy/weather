package meteo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public class Utils {
	
    public static Map<String, String> getFromUrl(String pattern, String url) throws MalformedURLException, IOException{
            Matcher matcher = Pattern.compile(pattern).matcher(IOUtils.toString(new URL(url).openStream()));
            Map<String,String> map = new HashMap<>();
            int i = 1;
            while(matcher.find()) {
                try {
                    while(true)
                            map.put(matcher.group(i++),matcher.group(i++));
                }catch (Exception e) {i = 1;}
            }
            return map;
    }
}
