import java.net.*;
import java.io.*;

import org.json.*;

import java.util.regex.*;


public class TocHw4

{
	public static String road(String t)
	{
		if(t.contains("路"))
		{
			t=t.substring(0,t.indexOf("路")+1);
		
		}
		else if(t.contains("街"))
		{
			t=t.substring(0,t.indexOf("街")+1);
		
		}
		else if(t.contains("大道"))
		{
			t=t.substring(0,t.indexOf("大道")+2);
		
		}
		else if(t.contains("巷"))
		{
			t=t.substring(0,t.indexOf("巷")+1);
		
		}
		else
		{
			return "";
		}
		
		return t;
	}
	

	public static void  main(String[] args) throws IOException, JSONException
	{
		
		String url=new String(args[0]);
		URL pageUrl = new URL(url);
		URLConnection myconnection = pageUrl.openConnection();
		myconnection.connect();

		BufferedReader data = new BufferedReader(new InputStreamReader(pageUrl.openStream(),"UTF-8"));
		
		
		
		JSONTokener tok = new JSONTokener(data);
		JSONArray arr=new JSONArray(tok);
		String mdroad;
		int max_distinct_month=0,i=0,count=0,temp=0;
		int [] check;
		check= new int[arr.length()];
		for(i=0;i<arr.length();i++)
		{
			check[i]=1;
		}
		
		Pattern pattern;
		int [] montemp;
		montemp=new int [2000];
		
		
		while(i<arr.length())
		{
			JSONObject obj =arr.getJSONObject(i);
			
			//if(check[i]!=0)
				{
					pattern=Pattern.compile(road(obj.optString("土地區段位置或建物區門牌")));
					Matcher match1 =pattern.matcher(obj.optString("土地區段位置或建物區門牌"));
					if(match1.find())
					{
						montemp[count]=obj.getInt("交易年月");
						count++;
						
					}
					for(int j=1;j<count;j++)
					{
						for(int k=0;k<j;k++)
						{
							if(montemp[j]==montemp[k])
							{
								montemp[j]=0;								
							}
							
						}
						
					}
					
					for(int j=0;j<count;j++)
					{
						if(montemp[j]!=0)
							temp++;
					}
					
					if(temp>max_distinct_month)
						{
							max_distinct_month=temp;
							mdroad=road(obj.optString("土地區段位置或建物區門牌");
						}
				}
			i++;
		}
		
		
		/*
		//System.out.println(max_distinct_month);
		Pattern pattern=Pattern.compile(Integer.toString(max_distinct_month));
		i=0;
		int [] price;
		price=new int [2000];
		String[] temp;
		temp=new String[2000];
		
		while(i<arr.length())
		{
			JSONObject obj =arr.getJSONObject(i);
			
			if(obj.getInt("交易年月")==max_distinct_month)
			{
				temp[count]=obj.getString("土地區段位置或建物區門牌");
				price[count]=obj.getInt("總價元");
				System.out.println(temp[count]);
				System.out.println(obj.getInt("交易年月"));
				System.out.println(price[count]);
				
				count++;
			}
			
			i++;
		}
		
		
		
				
		i=0;
		int lowPrice=price[0],highPrice=price[0];
		while(i<count)
		{
			if(lowPrice>price[i])
				lowPrice=price[i];
			if(highPrice<price[i])
				highPrice=price[i];
			
			i++;
		}*/
		/*i=0;
		while(i<count){
		System.out.print(temp[count]+":");
		System.out.println(price[count]);}*/
	}
	
	
	
	
}
