package de.manoj.wordpuzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.Toast;





public class Home extends Activity {
    /** Called when the activity is first created. */
	class MyCount extends CountDownTimer{
		public MyCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		
		}
		ProgressBar time_bar;

		public void initialize(ProgressBar time_bar){
						this.time_bar=time_bar;
						
						
			
		}
		@Override
		public void onFinish() {
			//Home.count=0;
			//Toast.makeText(Home.this,"word is"+a.toString(),Toast.LENGTH_LONG).show();
			//Toast.makeText(Home.this,"starting from level 1",Toast.LENGTH_LONG).show();
			//wcounter=0;
			//EditText words_list = (EditText)findViewById(R.id.editText1);
			//words_list.setText("");
			//get_word();
			//load_word(shuffle_word(a));		
			status="Level Reached: " + current_level.toString();
			counter.cancel();
			finish();
			Intent prevScreen = new Intent(getApplicationContext(),wordpuzzleActivity.class);
            startActivity(prevScreen);
			
		}
		
		@Override
		public void onTick(long millisUntilFinished) {
			
	    	//time++;
			if(reset_word>0)
				reset_word--;
			if(reset_word==6)
			{
				TextView output = (TextView)findViewById(R.id.textView6);
				output.setText("NEXT LEVEL...");
			}
			if(reset_word==0)
			{
				TextView output = (TextView)findViewById(R.id.textView6);
				output.setText(R.string.make_words);
			}
	    	time_bar.setProgress((int)millisUntilFinished/1000);
	    	
		}
		
		}
	
	MyCount counter = new MyCount(200000,1000);
	static String status ="Level Reached: 0";
	char[] new_word = {'$','$','$','$','$','$','$','$'};
	static int reset_word=0;
	static int count=0;
	Integer target_score=new Integer(0); 
	//int target_time=0;
	Integer score=new Integer(0);
	Integer wcounter = new Integer(0);
	static Integer current_level=new Integer(0);
	static char []a={'a','b','n','o','r','m','a','l'};
	static String line="-";
	String added_words[] = new String[100];
	//Dictionary dict = new Dictionary();
	Integer time= new Integer(0);
	public boolean valid_word(String word)
	{
		//Toast.makeText(getApplicationContext(),word,Toast.LENGTH_LONG).show();
		//check whether the word is present in the text box already
		Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(R.raw.allwords);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line=null;
        int i=58112;
        while(i>0){
        	i--;
        	try {
				line = reader.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        	if(line.compareTo(word)==0)
        	{
        		return true;
        	}
        }
		return false;
	}
	
	
	public boolean exist_word(String input) {
		int i=0;
		for(i=0;i<wcounter;i++)
		{
			if(input.compareToIgnoreCase(added_words[i])==0)
				return true;
		}
		return false;
	}
	

	
	public void get_word()   //returns a new 8 letter word from the list
	{
		 TextView level = (TextView) findViewById(R.id.textView2);
		 TextView tar_score = (TextView) findViewById(R.id.textView4);
		 TextView t_score = (TextView) findViewById(R.id.TextView01);
		 int number = (new Random().nextInt(9394));
		 Resources resources = getResources();
	        InputStream inputStream = resources.openRawResource(R.raw.listofwords);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	        //Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();
	        try {
	        	while(number>0)
	        	{
	            line = reader.readLine();
	            number--;
	        	}
	            	//Toast.makeText(getApplicationContext(),line,Toast.LENGTH_LONG).show();
	        	wordpuzzleActivity.wordset="Last occured word: " + line;
	            	a[0]=line.charAt(0);
	            	a[1]=line.charAt(1);
	            	a[2]=line.charAt(2);
	            	a[3]=line.charAt(3);
	            	a[4]=line.charAt(4);
	            	a[5]=line.charAt(5);
	            	a[6]=line.charAt(6);
	            	a[7]=line.charAt(7);
	            	time=0;
	            	final ProgressBar time_bar = (ProgressBar)findViewById(R.id.progressBar2);
	            	
	            	if(count==0)
	            	{
	            		counter.initialize(time_bar);
	            		counter.cancel();
		            	counter.start();
	            	}
	            	else
	            	{
	            		counter.cancel();
	            		counter.start();
	            	}
	               	t_score.setText("0");
	            	score=0;
	            	current_level=current_level+1;
	            	target_score=13+(current_level-1)*5;
	            	level.setText(current_level.toString());
	            	tar_score.setText(target_score.toString());
	            	ProgressBar scr_bar = (ProgressBar)findViewById(R.id.progressBar1);
	            	
	                scr_bar.setMax(target_score);
	                scr_bar.setProgress(0);	
	                
	            	time_bar.setMax(200);
	            	time_bar.setProgress(300);
	             
	        } catch (IOException e) {
			
				e.printStackTrace();
			} finally {
	            try {
					reader.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	        }
		// a[0]=dict.words[number].charAt(0);
		// a[1]=dict.words[number].charAt(1);
		// a[2]=dict.words[number].charAt(2);
		// a[3]=dict.words[number].charAt(3);
		// a[4]=dict.words[number].charAt(4);
		// a[5]=dict.words[number].charAt(5);
		// a[6]=dict.words[number].charAt(6);
		// a[7]=dict.words[number].charAt(7);
	        shuffle_word(a);
	}


	
	public char[] shuffle_word(char[] word)
	{
    	int i=0;
    	new_word[0]='$';
    	new_word[1]='$';
    	new_word[2]='$';
    	new_word[3]='$';
    	new_word[4]='$';
    	new_word[5]='$';
    	new_word[6]='$';
    	new_word[7]='$';
    	for(;;)
    	{
    		if(i<8){
    		int number = (new Random().nextInt(8));
    		if (new_word[number]=='$')
    		{
    			new_word[number]=word[i];
    			i++;
    		}
    		}
    		else
    			break;
    	}
		return new_word;
	}

   	
	public void load_word(char[] input)  // loads the word into the buttons
	{
	   	final Button button14 = (Button) findViewById(R.id.Button14);
	    final Button button12 = (Button) findViewById(R.id.Button12);
	    final Button button10 = (Button) findViewById(R.id.Button10);
	   	final Button button11 = (Button) findViewById(R.id.Button11);
	   	final Button button9 = (Button) findViewById(R.id.Button09);
	   	final Button button15 = (Button) findViewById(R.id.Button15);
	   	final Button button13 = (Button) findViewById(R.id.Button13);
	   	final Button button8 = (Button) findViewById(R.id.Button08);
	   	final Button button07 = (Button) findViewById(R.id.Button07);
	   	final Button button06 = (Button) findViewById(R.id.Button06);
	    final Button button05 = (Button) findViewById(R.id.Button05);
	   	final Button button04 = (Button) findViewById(R.id.Button04);
	   	final Button button03 = (Button) findViewById(R.id.Button03);
	   	final Button button02 = (Button) findViewById(R.id.Button02);
	   	final Button button01 = (Button) findViewById(R.id.Button01);
	   	final Button button2 = (Button) findViewById(R.id.button2);
		int i=0;
		button14.setVisibility(0);
		button12.setVisibility(0);
		button10.setVisibility(0);
		button11.setVisibility(0);
		button9.setVisibility(0);
		button15.setVisibility(0);
		button13.setVisibility(0);
		button8.setVisibility(0);
		button07.setVisibility(4);
		button06.setVisibility(4);
		button05.setVisibility(4);
		button04.setVisibility(4);
		button03.setVisibility(4);
		button02.setVisibility(4);
		button01.setVisibility(4);
		button2.setVisibility(4);
    	button14.setText(input,i++,1);
	    button12.setText(input,i++,1);
	    button10.setText(input,i++,1);
	    button11.setText(input,i++,1);
	    button9.setText(input,i++,1);
	    button15.setText(input,i++,1);
	    button13.setText(input,i++,1);
	    button8.setText(input,i++,1);
	}
	
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        get_word();
        load_word(shuffle_word(a));
        final ProgressBar scr_bar = (ProgressBar)findViewById(R.id.progressBar1);
        scr_bar.setMax(target_score);
        final TextView t_score = (TextView) findViewById(R.id.TextView01);
    	final Button button07 = (Button) findViewById(R.id.Button07);
       	final Button button06 = (Button) findViewById(R.id.Button06);
        final Button button05 = (Button) findViewById(R.id.Button05);
       	final Button button04 = (Button) findViewById(R.id.Button04);
       	final Button button03 = (Button) findViewById(R.id.Button03);
       	final Button button02 = (Button) findViewById(R.id.Button02);
       	final Button button01 = (Button) findViewById(R.id.Button01);
       	final Button button2 = (Button) findViewById(R.id.button2);
       	final Button button3 = (Button) findViewById(R.id.button3);
       	final Button button4 = (Button) findViewById(R.id.button4);
       	final Button button14 = (Button) findViewById(R.id.Button14);
        final Button button12 = (Button) findViewById(R.id.Button12);
        final Button button10 = (Button) findViewById(R.id.Button10);
       	final Button button11 = (Button) findViewById(R.id.Button11);
       	final Button button9 = (Button) findViewById(R.id.Button09);
       	final Button button15 = (Button) findViewById(R.id.Button15);
       	final Button button13 = (Button) findViewById(R.id.Button13);
       	final Button button8 = (Button) findViewById(R.id.Button08);
		button14.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (button07.getVisibility()==4) 
				{
					button07.setText(button14.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button14.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button14.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button14.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button14.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button14.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button14.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button14.getText());
					button2.setVisibility(0);
				}
				//button14.setText(one,0,1);
				button14.setVisibility(4);
			}});
		button12.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button12.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button12.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button12.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button12.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button12.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button12.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button12.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button12.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button12.getText());
					button2.setVisibility(0);
				}
				//button12.setText(button6.getText());
			}});
		button10.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button10.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button10.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button10.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button10.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button10.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button10.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button10.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button10.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button10.getText());
					button2.setVisibility(0);
				}
				//button10.setText(button6.getText());
			}});
		button11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button11.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button11.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button11.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button11.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button11.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button11.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button11.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button11.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button11.getText());
					button2.setVisibility(0);
				}
				//button11.setText(button6.getText());
			}});
		button9.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button9.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button9.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button9.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button9.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button9.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button9.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button9.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button9.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button9.getText());
					button2.setVisibility(0);
				}
				//button9.setText(button6.getText());
			}});
		button15.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button15.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button15.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button15.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button15.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button15.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button15.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button15.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button15.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button15.getText());
					button2.setVisibility(0);
				}
				//button15.setText(button6.getText());
			}});
		button13.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button13.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button13.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button13.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button13.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button13.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button13.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button13.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button13.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button13.getText());
					button2.setVisibility(0);
				}
				//button13.setText(button6.getText());
			}});
		button8.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button8.setVisibility(4);
				if (button07.getVisibility()==4) 
				{
					button07.setText(button8.getText());
					button07.setVisibility(0);
				}
				else if (button06.getVisibility()==4) 
				{
					button06.setText(button8.getText());
					button06.setVisibility(0);
				}
				else if (button05.getVisibility()==4) 
				{
					button05.setText(button8.getText());
					button05.setVisibility(0);
				}
				else if (button04.getVisibility()==4) 
				{
					button04.setText(button8.getText());
					button04.setVisibility(0);
				}
				else if (button03.getVisibility()==4) 
				{
					button03.setText(button8.getText());
					button03.setVisibility(0);
				}
				else if (button02.getVisibility()==4) 
				{
					button02.setText(button8.getText());
					button02.setVisibility(0);
				}
				else if (button01.getVisibility()==4) 
				{
					button01.setText(button8.getText());
					button01.setVisibility(0);
				}
				else if (button2.getVisibility()==4) 
				{
					button2.setText(button8.getText());
					button2.setVisibility(0);
				}
				//button8.setText(button6.getText());
			}});
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//String input=null;
				int i=0;
				char []word = new char[8];
				if(button07.getVisibility()==0) word[i++]=button07.getText().charAt(0);else word[i++]='\0';
				if(button06.getVisibility()==0) word[i++]=button06.getText().charAt(0);else word[i++]='\0';
				if(button05.getVisibility()==0) word[i++]=button05.getText().charAt(0);else word[i++]='\0';
				if(button04.getVisibility()==0) word[i++]=button04.getText().charAt(0);else word[i++]='\0';
				if(button03.getVisibility()==0) word[i++]=button03.getText().charAt(0);else word[i++]='\0';
				if(button02.getVisibility()==0) word[i++]=button02.getText().charAt(0);else word[i++]='\0';
				if(button01.getVisibility()==0) word[i++]=button01.getText().charAt(0);else word[i++]='\0';
				if(button2.getVisibility()==0) word[i++]=button2.getText().charAt(0);else word[i++]='\0';
				String input=new String(word);
				input=input.trim();
				//Toast.makeText(getApplicationContext(),input,Toast.LENGTH_LONG).show();
				if(!exist_word(input))
				{
				if(valid_word(input))
				{
					EditText words_list = (EditText)findViewById(R.id.editText1);
					words_list.setText(words_list.getText()+input+"\n");
					added_words[wcounter]=input;
					wcounter++;
					score=score+input.length();
					if(input.length()==8)
						score=score+5;
					scr_bar.setProgress(scr_bar.getProgress()+input.length());
					if(score.intValue()>=target_score)
					{
						//Toast.makeText(Home.this,"word was"+a.toString(),Toast.LENGTH_LONG).show();
						TextView output = (TextView)findViewById(R.id.textView6);
						output.setText("WORD WAS: "+line);
						status="Level Reached: " + current_level.toString();
						reset_word=25;
						wcounter=0;
						words_list.setText("");
						counter.cancel();
						get_word();
					}
					t_score.setText(score.toString());
					
					
				}
				else
				{
					TextView output = (TextView)findViewById(R.id.textView6);
					output.setText("No word Found");
					reset_word=3;
					//Toast.makeText(getApplicationContext(),"No word found",Toast.LENGTH_LONG).show();
				}
				}
				else
				{
					TextView output = (TextView)findViewById(R.id.textView6);
					output.setText("Word already added");
					reset_word=3;
					//Toast.makeText(getApplicationContext(),"Word already added",Toast.LENGTH_LONG).show();
				}
				
				load_word(new_word);
			}
			
		});
		
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				load_word(shuffle_word(a));
		}});
		
		
		
		
		
    }

    public void onBackPressed() {
    	 counter.cancel();
 finish();
    	Intent prevScreen = new Intent(getApplicationContext(),wordpuzzleActivity.class);
        startActivity(prevScreen);
    }

    
    
}