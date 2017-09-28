package ch.heigvd.sym.template;

import android.content.Intent;
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

public class MyActivity extends AppCompatActivity {

    //ImageView       imageView = null;
    private String  pathToPicture = null;
    //private Button  exit     = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the picture
        setContentView(R.layout.picture);

        // Link to GUI elements -- defined in res/layout/picture.xml
        //this.imageView = (ImageView) findViewById(R.id.imageView);

        // Fetches and displays picture
        //imageView.setImageBitmap(BitmapFactory.decodeFile(pathToPicture));

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String mail = intent.getStringExtra(MainActivity.mail);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText("Arthur te souhaite la bienvenue Lapin");

      /*  exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // exit application
            }
        });*/
    }

  /*  protected void showErrorDialog() {
		/*
		 * Pop-up dialog to show error
		 *
        AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(android.R.drawable.ic_dialog_alert);
        alertbd.setTitle("File not found");
        alertbd.setMessage("Unable to find corresponding picture!");
        alertbd.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // we do nothing...
                // dialog close automatically
            }
        });
        alertbd.create().show();
    } */
}

