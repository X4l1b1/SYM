package ch.heigvd.sym.template;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

public class MyActivity extends AppCompatActivity {

    //ImageView       imageView = null;
    private String  pathToPicture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("LifeCycle", "My Activity onCreate()");
        super.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        super.onCreate(savedInstanceState);
        // Show the picture
        setContentView(R.layout.picture);
//      setContentView(R.layout.picture_rel);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String mail = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(mail);

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File[] files = dir.listFiles();

        Log.d("PATH", "PATH: " + dir.getAbsolutePath());
        for (int i = 0; i < files.length; i++) {
            Log.d("FILES", "FileName: " + files[i].getName());
        }

        Bitmap myBitmap = BitmapFactory.decodeFile(dir.getAbsolutePath() + "/" + files[0].getName());

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(myBitmap);

        Intent intent2 = new Intent();
        intent2.putExtra("RETURN_VALUE", "Here is a return value");
        setResult(RESULT_OK, intent2);

    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("LifeCycle", "My Activity onRestart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LifeCycle", "My Activity onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LifeCycle", "My Activity onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LifeCycle", "My Activity onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "My Activity onDestroy()");
    }

   /* @Override
    public void onBackPressed() {
        Intent main = new Intent(MyActivity.this, MainActivity.class);
        MyActivity.this.startActivity(main);
        finish();
    }*/


}

