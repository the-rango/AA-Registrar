import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class RewriteOfCourseInfo {
	public static void main (String[] args) throws IOException{
		try {
			String ROOT = "http://catalogue.uci.edu/allcourses/";
			
			JSONObject c = new JSONObject();
			
			final Document document = Jsoup.connect(ROOT).get();
			
			Elements links = document.getElementById("atozindex").getElementsByAttribute("href");
			for (Element dept: links) {
				String d_name = dept.text();
				String d_short = d_name.substring(d_name.indexOf('(')+1,d_name.length()-1);
				System.out.println(d_short);
				
				LinkedHashMap<String, String> c_d_short = new LinkedHashMap<String, String>();
				
				String url = dept.attr("abs:href");
				
				final Document inp = Jsoup.connect(url).get();
				for (Element course: inp.getElementsByClass("courseblock")){
					String l_name = course.getElementsByClass("courseblocktitle").get(0).text();
					String name = l_name.split("\\.")[0];
					String[] numed = name.split(" ");
					String num = numed[numed.length-1];
										
					String info = "";
					for (Element p: course.getElementsByTag("p")) {
						for (Element a: p.getElementsByTag("a")) {
							a.attr("href",a.attr("abs:href"));
							a.attr("onclick", "");
							a.attr("target", "_blank");
						}
						info += p.toString();
					}
					c_d_short.put(num, info);
				}
				c.put(d_short, c_d_short);
			}

			PrintWriter pw = new PrintWriter("htmlformated.json");
	        pw.write(c.toJSONString());
	        pw.flush(); 
	        pw.close();
	        /*
	         * Gen Dependency Graph is onwards. Idk how to import files to proper JSON
	         * in Java, and I'm too lazy to question it. Hence the catastrophic mismash :P
	         */
	        
	        
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

