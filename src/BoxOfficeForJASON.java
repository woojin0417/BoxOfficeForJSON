
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.*;

public class BoxOfficeForJASON {
	
	public BoxOfficeForJASON() throws Exception{
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(httpRead());
		JSONObject json = (JSONObject) obj.get("boxOfficeResult");
		JSONArray arr = (JSONArray)json.get("dailyBoxOfficeList");
		
		System.out.println((String) json.get("boxofficeType"));
		System.out.println();
		String str = (String) json.get("showRange");
		System.out.println(str + "일별 박스오피스 .");
		System.out.println();
		
		for(int i=0; i<arr.size(); i++){
			
			JSONObject entity = (JSONObject) arr.get(i);
			String movieNm = (String) entity.get("movieNm");
			System.out.println((i+1) + "등. " + movieNm);
			
		}
		
	}
	

	public static String httpRead() throws Exception{
		String str = "http://ec2-52-26-144-160.us-west-2.compute.amazonaws.com:3000/jiminzzang";
		URL url = new URL(str);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		conn.setConnectTimeout(3000);
		conn.setReadTimeout(3000);
		
		try(InputStream in = conn.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream()){
			
			byte[] buf = new byte[1024 * 8];
			int length = 0;
			while((length = in.read(buf)) != -1){
				out.write(buf, 0, length);
			}
			return new String(out.toByteArray(), "UTF-8");
			
		}
		
	}

	public static void main(String[] args){
		
		try{
			new BoxOfficeForJASON();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
