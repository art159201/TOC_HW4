import java.net.*;
import java.io.*;

import org.json.*;

import java.util.regex.*;


public class TocHw4

{
	
	
	public static String road(String t)  //return road only
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
			return null;
		}
		
		return t;
	}
	

	public static void  main(String[] args, int o) throws IOException, JSONException
	{
		
		String url=new String(args[0]);
		String sect;
		String road;
		int year,price;
		
		URL pageUrl = new URL(url);
		URLConnection myconnection = pageUrl.openConnection();
		myconnection.connect();
		int max_distinct_month=0,i=0,count=0,temp=0;

		BufferedReader data = new BufferedReader(new InputStreamReader(pageUrl.openStream(),"UTF-8"));
		JSONTokener tok = new JSONTokener(data);
		JSONArray arr=new JSONArray(tok);
		String[] temps;
		temps=new String[20000];
		int[] tempm;
		tempm=new int[20000];
		String[] temps2;
		temps2=new String[20000];
		int[][] temp1;
		temp1=new int[20000][20000];
		int[] ans;
		ans=new int [20000];
		String[] anss;
		anss=new String[20000];
		
		while(i<arr.length())
		{
			JSONObject obj =arr.getJSONObject(i);
			sect=obj.getString("鄉鎮市區");
			road=obj.getString("土地區段位置或建物區門牌");
			//year=
			price=obj.getInt("總價元");
			
			if(road.indexOf("路")>0)
			{
				temps[count]=road(sect);
				tempm[count]=obj.getInt("交易年月");
				count++;
			}
			else if(road.indexOf("街")>0)
			{
				temps[count]=road(sect);
				tempm[count]=obj.getInt("交易年月");
				count++;
			}
			else if(road.indexOf("大道")>0)
			{
				temps[count]=road(sect);
				tempm[count]=obj.getInt("交易年月");
				count++;
			}
			else if(road.indexOf("巷")>0)
			{
				temps[count]=road(sect);
				tempm[count]=obj.getInt("交易年月");
				count++;
			}
			int k,l = 0,m = 0,n=0;
			for(int j=0;j<count;j++)
			{
				for(k=0;k<j;k++)
				{
					if(temps[j].equals(temps[k]))
					{
						if(temps2.length!=0)
							for(l=0;l<temps2.length;l++)
							{
								if(temps2[l].equals(temps[j]))
									if(temp1[l].length!=0)
										{
											for(m=0;m<temps2.length;l++)
											{
												if(temp1[l][m]==tempm[j])
													break;
											}	
											break;
										}
							}
						else if(temp1[l][m]==0)
						{
							temp1[l][m]=tempm[j];
							ans[l]++;
							
						}
						
						
						
					}
					
					
				}
		
				if(j==k)
				{
					anss[n]=temps[j];
					temp1[n][0]=tempm[j];
					ans[n]=1;
					n++;
			
				}
		
			}	
			
			int[] number=new int[3];
			
			for(k=0;anss[k]!=null;k++)
			{
				if(l<ans[k])
				{
					l=ans[k];
					number[0]=k;
				}

			}
			int q=0;m=0;
			for(q=number[0]+1;anss[q]!=null;q++)
			{
				if(ans[q]==l)
				{
					m++;
					number[m]=q;
				}
			}

			
		int highPrice=0,lowPrice=0;
		String  ansroad;
		for(m=0;number[m]!=0;m++)
		{
			ansroad=anss[number[m]];
			lowPrice=obj.getInt("總價元");
			for(o=0;o<arr.length();o++)
			{
				obj=arr.getJSONObject(o);
				sect=obj.getString("土地區段位置或建物區門牌");
				price=obj.getInt("總價元");
				if(sect.contains(ansroad))
				{	
					for(q=0;q<50;q++)
					{
						if(price<lowPrice)
							lowPrice=price;
						if(price>highPrice)
							highPrice=price;
					}
		
				}
		
			}
			
			System.out.println(ansroad+", 最高成交價: "+highPrice+", 最低成交價: "+lowPrice);
		}
	}
}
}
