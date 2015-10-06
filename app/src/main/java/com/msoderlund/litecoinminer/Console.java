package com.msoderlund.litecoinminer;

import static com.msoderlund.litecoinminer.Constants.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class Console {
	final int MSG_UIUPDATE = 1;
	final int MSG_STATUPDATE = 2;
	final int MSG_CONSOLE_UPDATE = 7;
	StringBuilder sb=new StringBuilder();
	boolean c_new=false;
	String[] console_a = new String[20];
	Handler sHandler = new Handler();
	
	public Console(Handler h)
	 {
		Log.i("LC", "Console: Console()");
		console_a=new String[20];
		for (int i=0; i<20; i++) {console_a[i]="";}
		sHandler = h;
	 }

	public void write (String s)
	{
		Message msg = new Message();
		Bundle bundle = new Bundle();
		
		Log.i("LC", "Console: write():"+s);
		if(s!=null)
		{
			for (int i=19; i>0; i--) {console_a[i]=console_a[i-1]; }
			console_a[0]=s;
		}
		msg.arg1=MSG_CONSOLE_UPDATE;
		bundle.putString("console", getConsole());
		msg.setData(bundle);
		sHandler.sendMessage(msg);
	}

	public String getConsole()
	{
		sb=new StringBuilder();
		for (int i=0; i<20; i++)
		{
			sb.append(console_a[i]+'\n');
		}
		return sb.toString();
	}
		
}

		
	

