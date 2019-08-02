import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseInfo {
	public static void main (String[] args) throws IOException{
		
		
		final String url = "http://catalogue.uci.edu/allcourses/";
		try {
			
			final Document document = Jsoup.connect(url).get();
			//Element everything = document.getElementById("atozindex");
			Elements links = document.getElementById("atozindex").getElementsByAttribute("href");
			List<String> linked = new ArrayList<String>();
			List<String> named = new ArrayList<String>();
			for (Element a: links) {
				linked.add(a.attr("abs:href"));
				String temp = a.text();
				//String[] splitter = temp.split("\\(");
				String[] splitter3 = temp.split("\\(")[1].split("\\)");
				named.add(splitter3[0]);
			}
			/*System.out.println(linked);
			 *System.out.println(named);
			 *System.out.println("-----");
			 *
			 * [http://catalogue.uci.edu/allcourses/ac_eng/, http://catalogue.uci.edu/allcourses/afam/, http://catalogue.uci.edu/allcourses/anatomy/, http://catalogue.uci.edu/allcourses/anthro/, http://catalogue.uci.edu/allcourses/arabic/, http://catalogue.uci.edu/allcourses/armn/, http://catalogue.uci.edu/allcourses/art/, http://catalogue.uci.edu/allcourses/art_his/, http://catalogue.uci.edu/allcourses/arts/, http://catalogue.uci.edu/allcourses/asianam/, http://catalogue.uci.edu/allcourses/biochem/, http://catalogue.uci.edu/allcourses/bio_sci/, http://catalogue.uci.edu/allcourses/bats/, http://catalogue.uci.edu/allcourses/bme/, http://catalogue.uci.edu/allcourses/bsemd/, http://catalogue.uci.edu/allcourses/bana/, http://catalogue.uci.edu/allcourses/cbe/, http://catalogue.uci.edu/allcourses/cbems/, http://catalogue.uci.edu/allcourses/chem/, http://catalogue.uci.edu/allcourses/chc_lat/, http://catalogue.uci.edu/allcourses/chinese/, http://catalogue.uci.edu/allcourses/engrcee/, http://catalogue.uci.edu/allcourses/classic/, http://catalogue.uci.edu/allcourses/cogs/, http://catalogue.uci.edu/allcourses/com_lit/, http://catalogue.uci.edu/allcourses/cse/, http://catalogue.uci.edu/allcourses/compsci/, http://catalogue.uci.edu/allcourses/crm_law/, http://catalogue.uci.edu/allcourses/critism/, http://catalogue.uci.edu/allcourses/clt_thy/, http://catalogue.uci.edu/allcourses/dance/, http://catalogue.uci.edu/allcourses/dev_bio/, http://catalogue.uci.edu/allcourses/drama/, http://catalogue.uci.edu/allcourses/earthss/, http://catalogue.uci.edu/allcourses/eas/, http://catalogue.uci.edu/allcourses/eco_evo/, http://catalogue.uci.edu/allcourses/econ/, http://catalogue.uci.edu/allcourses/educ/, http://catalogue.uci.edu/allcourses/eecs/, http://catalogue.uci.edu/allcourses/ecps/, http://catalogue.uci.edu/allcourses/engr/, http://catalogue.uci.edu/allcourses/english/, http://catalogue.uci.edu/allcourses/epidem/, http://catalogue.uci.edu/allcourses/euro_st/, http://catalogue.uci.edu/allcourses/mgmt_ep/, http://catalogue.uci.edu/allcourses/flm_mda/, http://catalogue.uci.edu/allcourses/fin/, http://catalogue.uci.edu/allcourses/french/, http://catalogue.uci.edu/allcourses/mgmt_fe/, http://catalogue.uci.edu/allcourses/gen_sex/, http://catalogue.uci.edu/allcourses/german/, http://catalogue.uci.edu/allcourses/glblclt/, http://catalogue.uci.edu/allcourses/glblme/, http://catalogue.uci.edu/allcourses/greek/, http://catalogue.uci.edu/allcourses/mgmt_hc/, http://catalogue.uci.edu/allcourses/hebrew/, http://catalogue.uci.edu/allcourses/history/, http://catalogue.uci.edu/allcourses/human/, http://catalogue.uci.edu/allcourses/in4matx/, http://catalogue.uci.edu/allcourses/i_c_sci/, http://catalogue.uci.edu/allcourses/intl_st/, http://catalogue.uci.edu/allcourses/italian/, http://catalogue.uci.edu/allcourses/japanse/, http://catalogue.uci.edu/allcourses/korean/, http://catalogue.uci.edu/allcourses/lsci/, http://catalogue.uci.edu/allcourses/latin/, http://catalogue.uci.edu/allcourses/linguis/, http://catalogue.uci.edu/allcourses/lit_jrn/, http://catalogue.uci.edu/allcourses/lps/, http://catalogue.uci.edu/allcourses/mgmtmba/, http://catalogue.uci.edu/allcourses/mgmt/, http://catalogue.uci.edu/allcourses/mgmtphd/, http://catalogue.uci.edu/allcourses/mpac/, http://catalogue.uci.edu/allcourses/engrmse/, http://catalogue.uci.edu/allcourses/math/, http://catalogue.uci.edu/allcourses/engrmae/, http://catalogue.uci.edu/allcourses/pharm/, http://catalogue.uci.edu/allcourses/m_mg/, http://catalogue.uci.edu/allcourses/mol_bio/, http://catalogue.uci.edu/allcourses/music/, http://catalogue.uci.edu/allcourses/net_sys/, http://catalogue.uci.edu/allcourses/neurbio/, http://catalogue.uci.edu/allcourses/nur_sci/, http://catalogue.uci.edu/allcourses/path/, http://catalogue.uci.edu/allcourses/ped_gen/, http://catalogue.uci.edu/allcourses/persian/, http://catalogue.uci.edu/allcourses/phrmsci/, http://catalogue.uci.edu/allcourses/philos/, http://catalogue.uci.edu/allcourses/phy_sci/, http://catalogue.uci.edu/allcourses/physics/, http://catalogue.uci.edu/allcourses/physio/, http://catalogue.uci.edu/allcourses/pp_d/, http://catalogue.uci.edu/allcourses/pol_sci/, http://catalogue.uci.edu/allcourses/portug/, http://catalogue.uci.edu/allcourses/psci/, http://catalogue.uci.edu/allcourses/psych/, http://catalogue.uci.edu/allcourses/pubhlth/, http://catalogue.uci.edu/allcourses/pub_pol/, http://catalogue.uci.edu/allcourses/rel_std/, http://catalogue.uci.edu/allcourses/rotc/, http://catalogue.uci.edu/allcourses/russian/, http://catalogue.uci.edu/allcourses/socecol/, http://catalogue.uci.edu/allcourses/spps/, http://catalogue.uci.edu/allcourses/soc_sci/, http://catalogue.uci.edu/allcourses/sociol/, http://catalogue.uci.edu/allcourses/swe/, http://catalogue.uci.edu/allcourses/spanish/, http://catalogue.uci.edu/allcourses/stats/, http://catalogue.uci.edu/allcourses/tox/, http://catalogue.uci.edu/allcourses/ucdc/, http://catalogue.uci.edu/allcourses/uni_aff/, http://catalogue.uci.edu/allcourses/uni_stu/, http://catalogue.uci.edu/allcourses/uppp/, http://catalogue.uci.edu/allcourses/vietmse/, http://catalogue.uci.edu/allcourses/vis_std/, http://catalogue.uci.edu/allcourses/writing/]
		     * [AC ENG, AFAM, ANATOMY, ANTHRO, ARABIC, ARMN, ART, ART HIS, ARTS, ASIANAM, BIOCHEM, BIO SCI, BATS, BME, BSEMD, BANA, CBE, CBEMS, CHEM, CHC/LAT, CHINESE, ENGRCEE, CLASSIC, COGS, COM LIT, CSE, COMPSCI, CRM/LAW, CRITISM, CLT&THY, DANCE, DEV BIO, DRAMA, EARTHSS, EAS, ECO EVO, ECON, EDUC, EECS, ECPS, ENGR, ENGLISH, EPIDEM, EURO ST, MGMT EP, FLM&MDA, FIN, FRENCH, MGMT FE, GEN&SEX, GERMAN, GLBLCLT, GLBL ME, GREEK, MGMT HC, HEBREW, HISTORY, HUMAN, IN4MATX, I&C SCI, INTL ST, ITALIAN, JAPANSE, KOREAN, LSCI, LATIN, LINGUIS, LIT JRN, LPS, MGMTMBA, MGMT, MGMTPHD, MPAC, ENGRMSE, MATH, ENGRMAE, PHARM, M&MG, MOL BIO, MUSIC, NET SYS, NEURBIO, NUR SCI, PATH, PED GEN, PERSIAN, PHRMSCI, PHILOS, PHY SCI, PHYSICS, PHYSIO, PP&D, POL SCI, PORTUG, PSCI, PSYCH, PUBHLTH, PUB POL, REL STD, ROTC, RUSSIAN, SOCECOL, SPPS, SOC SCI, SOCIOL, SWE, SPANISH, STATS, TOX, UCDC, UNI AFF, UNI STU, UPPP, VIETMSE, VIS STD, WRITING]
			 * -----
			 */
			int index = 0;
			JSONObject jo = new JSONObject();
			for(String ugh : linked) {
				final Document newDoc = Jsoup.connect(ugh).get();
				Elements courses = newDoc.getElementsByClass("courses");
				LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
				for (Element courseSelection : courses) {
					Elements courseblock = courseSelection.getElementsByClass("courseblock");
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					for (Element classed : courseblock) {
						String title = classed.select("p").text();
						String courseTitle = title.replace(named.get(index),"").trim().split("\\.")[0];
						//String operation = title.split("\\.")[0];
						//String map1 = operation.split(" ")[operation.length()-1];
						//if wanting original, do .html() after classed.select("p.courseblocktitle").first()
						//
						//AC&nbsp;ENG&nbsp;20A. Academic Writing. 5 Units. 
						map.put(courseTitle, title);
						//System.out.println(title);
						//System.out.println(courseTitle);
						//System.out.println(classed.select("p").text());
					}
					//map.forEach((key, value) -> System.out.println(key + ":" + value));
					jo.put(named.get(index), map);
					//System.out.println(courseSelection);
					//System.out.println("-----");
					//TODO: filter course title and desc and make list. Then make json.
				}
				//System.out.println(courses.size());
				//System.out.println(courses);
				//System.out.println("-----");
				//System.out.println(jo.toJSONString());
				index++;
			}
			PrintWriter pw = new PrintWriter("/home/rishi/Desktop/JSONExample.json");
			pw.write(jo.toJSONString());
			pw.flush(); 
	        pw.close(); 
			//System.out.println(jo.toJSONString());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}