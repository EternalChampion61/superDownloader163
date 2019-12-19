package superDownloader163.hugesea.handle;

import java.net.*;
import java.io.*;

import org.apache.commons.lang3.StringEscapeUtils;
import com.alibaba.fastjson.*; 

@SuppressWarnings("deprecation")
public class Handle {
	public static String getSongName(String SongCode) {
		String SongName = null;
		
		String HttpJSON_SongInformation = getSongInfomation(SongCode).substring(1,getSongInfomation(SongCode).length()-1);
		StringBuilder JSON_SongInformation_Encode =  new StringBuilder(HttpJSON_SongInformation);
		JSON_SongInformation_Encode.append("}");
		JSON_SongInformation_Encode.insert(0, "{");
		
		String JSON_Formated_SongInformation = StringEscapeUtils.unescapeJava(JSON.toJSONString(JSON_SongInformation_Encode).substring(1,JSON.toJSONString(JSON_SongInformation_Encode).length()-1 ));

		JSONObject JSON_Object_SongName = JSON.parseObject(JSON_Formated_SongInformation);
		String Result = JSON_Object_SongName.getJSONArray("songs").get(0).toString();
		JSONObject JSON_Result = JSON.parseObject(Result);
		SongName = JSON_Result.getString("name");
		
		return SongName;
	}
	
	public static String getSongAuthor(String SongCode) {
		String SongAuthor = null;
		
		String HttpJSON_SongInformation = getSongInfomation(SongCode).substring(1,getSongInfomation(SongCode).length()-1);
		StringBuilder JSON_SongInformation_Encode =  new StringBuilder(HttpJSON_SongInformation);
		JSON_SongInformation_Encode.append("}");
		JSON_SongInformation_Encode.insert(0, "{");
		
		String JSON_Formated_SongInformation = StringEscapeUtils.unescapeJava(JSON.toJSONString(JSON_SongInformation_Encode).substring(1,JSON.toJSONString(JSON_SongInformation_Encode).length()-1 ));

		JSONObject JSON_Object_Author = JSON.parseObject(JSON_Formated_SongInformation);
		String Result = JSON_Object_Author.getJSONArray("songs").get(0).toString();
		JSONObject JSON_Result = JSON.parseObject(Result);
		String JSON_SongAuthor = JSON_Result.getJSONArray("ar").get(0).toString();
		JSONObject Result_SongAuthor = JSON.parseObject(JSON_SongAuthor);
		SongAuthor = Result_SongAuthor.getString("name");
		
		return SongAuthor;
	}
	
	private static String getSongInfomation(String SongCode) {
		String urlString = "";
		try {
			URL HttpUrl = new URL("https://api.imjad.cn/cloudmusic/?id=" + SongCode + "&type=detail");
			URLConnection HttpConnection = HttpUrl.openConnection();
			HttpURLConnection Connection = null;
			if (HttpConnection instanceof HttpURLConnection) {
				Connection = (HttpURLConnection) HttpConnection;
			}else {
				
			}
			BufferedReader in = new BufferedReader(
			new InputStreamReader(Connection.getInputStream()));
			String current;
			while((current = in.readLine()) != null)
	         {
	            urlString += current;
	         }
		}catch (IOException e) {
			e.printStackTrace();
		}
		return urlString;
	}
}