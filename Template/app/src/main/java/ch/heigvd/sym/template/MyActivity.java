package ch.heigvd.sym.template;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

public class MyActivity extends AppCompatActivity {

    //ImageView       imageView = null;
    private String  pathToPicture = null;
    private static final String TAG2 = MyActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the picture
        setContentView(R.layout.picture);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String mail = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(mail);

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File[] files = dir.listFiles();

            Log.d("PATH", "PATH: " + dir.getAbsolutePath());
            for (int i = 0; i < files.length; i++)
            {
                Log.d("FILES", "FileName: " + files[i].getName());
            }

            Bitmap myBitmap = BitmapFactory.decodeFile(dir.getAbsolutePath() + "/" + files[0].getName());

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(myBitmap);

    }
}

