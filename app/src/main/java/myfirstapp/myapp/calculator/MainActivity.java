package myfirstapp.myapp.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2603627329934270~9553172548");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.


        savedInstanceState.putDouble("c", cal);
        savedInstanceState.putString("s", string);
        savedInstanceState.putString("s1", string1);

        // etc.

        super.onSaveInstanceState(savedInstanceState);
    }

//onRestoreInstanceState

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        string=savedInstanceState.getString("s");
        string1=savedInstanceState.getString("s1");
        cal=savedInstanceState.getDouble("c");
        TextView info=(TextView)findViewById(R.id.input);
        TextView result=(TextView)findViewById(R.id.output);
        info.setText(string1);
        result.setText(Double.toString(cal));
    }





    String string = "";
    String string1 = "";
    int flag=0;
   public int pos=-1;
  public   char c;
    public int flag1=0;
    double cal=0;// to check if the calculations are in degree or in radian


    public void angle(View v){
        Button button=(Button)v;
        Button button1=(Button)findViewById(R.id.degree);
        Button button2=(Button)findViewById(R.id.radian);
        if(button.getText().equals("Degree")){
            if(flag1==0){
                flag1=1;
                button1.setBackgroundColor(WHITE);
                button2.setBackgroundColor(GRAY);
            }
        }
        else{
            if(flag1==1){
                flag1=0;
                button1.setBackgroundColor(GRAY);
                button2.setBackgroundColor(WHITE);
            }
        }
    }


    public double result(String str) {
        try {// calculation
            nextchar(str);
            ArrayList<String> list = new ArrayList<>();
            while (pos <= (str.length() - 1)) {
                int a = pos;                      // TELLS STARTING POINT OF STR
                if (Character.isDigit(c) || c == '.') {
                    while (Character.isDigit(c) || c == '.')
                        nextchar(str);            // POS -1 ENDING POINT OF ARRAY
                    list.add(sstring(str, a, pos - 1));
                } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                    while (c >= 'a' && c <= 'z') nextchar(str);
                    list.add(sstring(str, a, pos - 1));
                } else if (c == '(') {
                    list.add(Double.toString(result(str)));
                } else if (c == ')') {
                    nextchar(str);
                    break;
                }
                else if (c == '-'&&pos-1==-1) {
                    nextchar(str);
                    if (Character.isDigit(c) || c == '.') {
                        while (Character.isDigit(c) || c == '.') nextchar(str);
                        list.add(sstring(str, a, pos - 1));
                    }
                        else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                            while (c >= 'a' && c <= 'z') nextchar(str);
                            list.add(sstring(str, a, pos - 1));
                        }

                }else if (c == '-' && (pos - 1 >= 0)&&(str.charAt(pos - 1) == '('||str.charAt(pos - 1) == '!'||str.charAt(pos - 1) == '*' || str.charAt(pos - 1) == '+' || str.charAt(pos - 1) == '/')) {

                    nextchar(str);
                    if (Character.isDigit(c) || c == '.') {
                        while (Character.isDigit(c) || c == '.') nextchar(str);
                        list.add(sstring(str, a, pos - 1));
                    }
                        else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                            while (c >= 'a' && c <= 'z') nextchar(str);
                            list.add(sstring(str, a, pos - 1));
                    }
                    else if (c == '(') {
                        list.add("-" + result(str));
                    }
                }
                        else {
                        list.add(Character.toString(c));
                        nextchar(str);
                    }

                }
                 cal = 0;
                for (int i = 0; i < list.size(); ++i) {
                    switch (list.get(i)) {
                        case "e":
                            list.remove(i);
                            list.add(i, "2.71828182846");
                            break;
                        case "π":
                            list.remove(i);
                            list.add(i, "3.14159265359");
                            break;
                    }
                }
                for (int i = 0; i < list.size(); ++i) {
                    if(flag1==0) {
                        switch (list.get(i)) {
                            case "sin":
                                cal = Math.sin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "cos":
                                cal = Math.cos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "tan":
                                cal = Math.tan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "asin":
                                cal = Math.asin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "acos":
                                cal = Math.acos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "ln":
                                cal = Math.log(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "atan":
                                cal = Math.atan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "log":
                                cal = Math.log10(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "sqrt":
                                cal = Math.sqrt(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-sin":
                                cal = -Math.sin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-cos":
                                cal = -Math.cos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-tan":
                                cal = -Math.tan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-asin":
                                cal = -Math.asin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-acos":
                                cal = -Math.acos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-ln":
                                cal =- Math.log(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-atan":
                                cal = -Math.atan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-log":
                                cal =- Math.log10(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-sqrt":
                                cal = -Math.sqrt(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                        }
                    }
                    else {
                        switch (list.get(i)) {
                            case "sin":
                                cal = Math.sin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "cos":
                                cal = Math.cos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "tan":
                                cal = Math.tan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "asin":
                                cal = Math.asin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "acos":
                                cal = Math.acos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "ln":
                                cal = Math.log(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "atan":
                                cal = Math.atan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "log":
                                cal = Math.log10(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "sqrt":
                                cal = Math.sqrt(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-sin":
                                cal = -Math.sin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-cos":
                                cal = -Math.cos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-tan":
                                cal = -Math.tan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-asin":
                                cal = -Math.asin(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-acos":
                                cal = -Math.acos(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-ln":
                                cal =- Math.log(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-atan":
                                cal = -Math.atan(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-log":
                                cal =- Math.log10(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                            case "-sqrt":
                                cal = -Math.sqrt(Double.parseDouble(list.get(i + 1)));
                                list.remove(i);
                                list.remove(i);
                                list.add(i, Double.toString(cal));
                                break;
                        }
                    }

                }

                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).equals("^")) {
                        cal = Math.pow(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));

                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        --i;

                    }
                }
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).equals("!")) {
                        cal = factorial(Double.parseDouble(list.get(i - 1)));
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        --i;
                    }
                }

                for (int i = 1; i < list.size(); i += 2) {
                    if (list.get(i).equals("/")) {
                        cal = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        i -= 2;
                    } else if (list.get(i).equals("*")) {
                        cal = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        i -= 2;
                    }
                }

                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).equals("+")) {
                        cal = Double.parseDouble(list.get(i - 1)) + Double.parseDouble(list.get(i + 1));
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        --i;
                    } else if (list.get(i).equals("-")) {
                        cal = Double.parseDouble(list.get(i - 1)) - Double.parseDouble(list.get(i + 1));
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.remove(i - 1);
                        list.add(i - 1, Double.toString(cal));
                        --i;
                    }
                }

                if(list.size()!=0){
                    double sum=0;
                    for(int i=0;i<list.size();++i)
                        sum+=Double.parseDouble(list.get(i));
                    for(int i=0;i<list.size();++i)
                        list.remove(0);
                    list.add(0,Double.toString(sum));
                }
                return Double.parseDouble(list.get(0));
            }catch(Exception e){
                pos = -9;
                return 0;
            }


    }

    public  double factorial(double x){
        double fact=1;
        for(int i=1;i<=x;++i){
            fact*=i;
        }
        return fact;
    }


    public void nextchar(String str){   // get next string
        c = (++pos < str.length()) ? str.charAt(pos):' ';
    }

    public String sstring(String str,int start,int end){
        String mix="";
        for(;start<=end;++start) {
            mix+=str.charAt(start);
        }
        return mix;
    }


    public void Onclick1(View v) {
        TextView info = (TextView) findViewById(R.id.input);
        Button button = (Button) v;
        string =  button.getText().toString();
        if(string1.isEmpty()&&(string.equals("!")||string.equals("/")||string.equals("*")||string.equals(")")||string.equals("!")||string.equals("+"))){
           string="";
        }
       else if (flag==1){
            flag=0;
            if(Character.isDigit(string.charAt(0))) string1="";
            else if(string.equals("(")) string = "*" + string;
        }
        else if (!string1.isEmpty()) {
           if ((string.equals("(") && Character.isDigit(string1.charAt(string1.length() - 1))))
                string = "*" + string;
            else if ((string1.charAt(string1.length() - 1) == ')' && (Character.isDigit(string.charAt(0)))))
                string = "*" + string;
           else if ((string.equals("e") && Character.isDigit(string1.charAt(string1.length() - 1))))
                string = "*" + string;
            else if ((string1.charAt(string1.length() - 1) == 'e' && (Character.isDigit(string.charAt(0)))))
                string = "*" + string;
           else if ((string.equals("π") && Character.isDigit(string1.charAt(string1.length() - 1))))
               string = "*" + string;
           else if ((string1.charAt(string1.length() - 1) == 'π' && (Character.isDigit(string.charAt(0)))))
               string = "*" + string;
           else if (string.equals("-")) {
                if (string1.charAt(string1.length() - 1) == '-')
                    string = "";
            } else if ( (string1.charAt(string1.length() - 1) == '+' || string1.charAt(string1.length() - 1) == '*' || string1.charAt(string1.length() - 1) == '/' || string1.charAt(string1.length() - 1) == '-') && (string.equals("+") || string.equals("!") || string.equals("-") || string.equals("/")||string.equals("*")))
                string = "";
        }
        string1=string1+string;
        info.setText(string1);
      //  info.setText(list.toString());


    }

    public void del(View v){
        TextView info = (TextView) findViewById(R.id.input);
        if (string1 != ""){
            string1 = string1.substring(0, string1.length() - 1);
        }
        info.setText(string1);
    }


    public void onClick(View v) {
        TextView result = (TextView) findViewById(R.id.output);
            try {
                if (!string1.isEmpty()) {
                    if (Character.isDigit(string1.charAt(string1.length() - 1)) || string1.charAt(string1.length() - 1) == ')' || string1.charAt(string1.length() - 1) == '!'||string1.charAt(string1.length() - 1) == 'e'||string1.charAt(string1.length() - 1) == 'π') {
                        double cal = result(string1);
                        if (pos != -9) {
                            result.setText(Double.toString(cal));
                            string1 = "";
                            string = "";
                            string1 = Double.toString(cal);
                        } else result.setText("Error");
                        flag = 1;
                        pos = -1;
                    }
                } else {
                    result.setText("ERROR");
                    string1 = "";
                    string = "";

                }
            }catch(Exception e) {
                result.setText("Error");
            }
    }

    public void clear(View v){
        TextView info=(TextView)findViewById(R.id.input);
        TextView result=(TextView)findViewById(R.id.output);
        string="";
        string1="";
        info.setText("");
        result.setText("");
        flag=0;
        pos=-1;

    }
}

