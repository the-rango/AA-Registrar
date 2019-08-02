import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class GenDepen {
	public static void main (String[] args) throws IOException, ParseException{
		Object c = new JSONParser().parse(new FileReader("htmlformated.json"));
		JSONObject b = (JSONObject) c;
		Iterator<Map.Entry> iterate = b.entrySet().iterator();
		while (iterate.hasNext()){
			Map.Entry course = iterate.next();
			JSONObject d = (JSONObject) course.getValue();
			System.out.println(d);
		}
	}
}
