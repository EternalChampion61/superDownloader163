package superDownloader163.hugesea.download;

import java.net.*;
import java.io.*;

import org.apache.commons.lang3.StringEscapeUtils;
import com.alibaba.fastjson.*; 

@SuppressWarnings("deprecation")
public class Download {
	public static void Download(String SongName,String SongAuthor,String SongCode) {
		String SongUrl = null;
		
		String HttpJSON_SongInformation = getSongInfomation(SongCode).substring(1,getSongInfomation(SongCode).length()-1);
		StringBuilder JSON_SongInformation_Encode =  new StringBuilder(HttpJSON_SongInformation);
		JSON_SongInformation_Encode.append("}");
		JSON_SongInformation_Encode.insert(0, "{");
		
		String JSON_Formated_SongInformation = StringEscapeUtils.unescapeJava(JSON.toJSONString(JSON_SongInformation_Encode).substring(1,JSON.toJSONString(JSON_SongInformation_Encode).length()-1 ));

		JSONObject JSON_Object_SongUrl = JSON.parseObject(JSON_Formated_SongInformation);
		JSONObject JSON_Result = JSON.parseObject(JSON_Object_SongUrl.getJSONArray("data").get(0).toString());
		SongUrl = JSON_Result.getString("url");
		
		try {
			saveFile(SongName,SongAuthor,SongUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void saveFile(String SongName,String SongAuthor,String SongUrl) throws IOException {
		final String Directory = "Download";
		
		URL url = new URL(SongUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(10 * 1000);
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		InputStream inputstream = conn.getInputStream();
		byte[] getData = readInputStream(inputstream);

		File downloadDirectory = new File(Directory);
		if (!downloadDirectory.exists()) {
			downloadDirectory.mkdirs();
		}

		File file = new File(downloadDirectory + File.separator + SongName + "-" + SongAuthor + ".mp3");
		FileOutputStream FOS = new FileOutputStream(file);
		FOS.write(getData);
		if (FOS != null) {
			FOS.close();
		}
		if (inputstream != null) {
			inputstream.close();
		}
	}
	
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream BOS = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			BOS.write(buffer, 0, len);
		}
		BOS.close();
		return BOS.toByteArray();
	}
	
	private static String getSongInfomation(String SongCode) {
		String urlString = "";
		try {
			URL HttpUrl = new URL("https://api.imjad.cn/cloudmusic/?type=song&id=" + SongCode + "&br=320000");
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
