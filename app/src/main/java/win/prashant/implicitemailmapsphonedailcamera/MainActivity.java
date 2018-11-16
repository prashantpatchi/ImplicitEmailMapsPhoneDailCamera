package win.prashant.implicitemailmapsphonedailcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btn1;
    Button btncall;    // varibale to call btn
    EditText numText;  // varibale to edit text number
    String sNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.idbtn);
        btn1 = findViewById(R.id.buttonMap);
        btncall = findViewById(R.id.buttonCall);
        numText = findViewById(R.id.editTextNumber);


    }
        // method use to use email app like gmail and others
    public void btnClick(View v){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"abc@gmail.com","xyz@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_SUBJECT,"this is a subject");
        i.putExtra(Intent.EXTRA_TEXT,"Hi this is the email Body");
        i.setType("message/rfc822");
        Intent chooser = Intent.createChooser(i,"launch Email");
        startActivity(chooser);
    }

    // method to launch map app
    public void btnClickMap(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo: 47.4925,19.0513"));
        Intent chooser = Intent.createChooser(i,"Launch Maps");
        startActivity(chooser);

    }
    // method to on click to call
    public void btnCall(View v){
        Intent i = new Intent(Intent.ACTION_CALL);
        sNum = numText.getText().toString();
        if (sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:778973578"));
        }else {
            i.setData(Uri.parse("tel:" +sNum));
        }
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permission to call ",Toast.LENGTH_SHORT).show();
            requestPermission();
        }
        else{
            startActivity(i);
        }
    }
    // to take permission from user to make call using this app
    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.CALL_PHONE},1);

    }
}
