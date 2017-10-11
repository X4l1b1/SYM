/**
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 20 septembre 2016
 *            IICT / HEIG-VD
 *
 * mailto:fabien.dutoit@heig-vd.ch
 *
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package ch.heigvd.sym.template;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "ch.heigvd.sym.template.myapp.MAIL";

    // Just for test purposes : please destroy !
    private static final String validEmail      = "a@b.c";
    private static final String validPassword   = "tata";
    public static String mail;
    public static String pwd;


    // GUI elements
    private EditText email      = null;
    private EditText passwd     = null;
    private Button   signIn     = null;
    private TextView textView   = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Lifecycle", "MainActivity onCreate()");
        // Show the welcome screen / login authentication dialog
        setContentView(R.layout.authent);

        textView = findViewById(R.id.textView);

        // Link to GUI elements -- defined in res/layout/authent.xml
        this.email      = (EditText) findViewById(R.id.email);
        this.passwd     = (EditText) findViewById(R.id.passwd);
        this.signIn     = (Button)   findViewById(R.id.buttOk);

        // Then program action associated to "Ok" button
        signIn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
				/*
				 * There you have to check out if the email/password
				 * combination given is valid or not
				 */
                mail = email.getText().toString();
                pwd = passwd.getText().toString();

                /*
                 * Format verification for both fields, first email then password
                 * if format is not valid, it doesn't even check the file for corresponding entries
                 */
                if (!isEmailValid(mail)) {
                    Toast.makeText(MainActivity.this, R.string.badMail, Toast.LENGTH_LONG).show();

                } else {
                    if (!isPasswdValid(pwd)) {
                        Toast.makeText(MainActivity.this, R.string.emptyPwd, Toast.LENGTH_LONG).show();
                    } else {

                        if (isValid(mail, pwd)) {
					/* Ok, valid combination, do something or launch another activity...
					 * The current activity could be finished, but it is not mandatory.
					 * To launch activity MyActivity.class, try something like :
					 */
					  			Intent intent = new Intent(MainActivity.this, ch.heigvd.sym.template.MyActivity.class);
					  			intent.putExtra(EXTRA_MESSAGE, mail);
					 		//	intent.putExtra("passwordGiven", pwd);
					 			MainActivity.this.startActivityForResult(intent, 1);

					/* Alternately, you could also startActivityForResult if you are awaiting a result.
					 * In the latter case, you have to indicate an int parameter to identify MyActivity
					 *
					 * If you haven't anything more to do, you may finish()...
					 * But just display a small message before quitting...
					 */
                            Toast.makeText(MainActivity.this, getString(R.string.good), Toast.LENGTH_LONG).show();
                            //finish();
                        } else {
                            // Wrong combination, display pop-up dialog and stay on login screen
                            showErrorDialog(mail, pwd);
                            email.setText("");
                            passwd.setText("");
                        }
                    }
                }
            }

        });
    }

    //Checks email is in valid format : [...]@[...].[...]
    private boolean isEmailValid(String email){
        return !(email == null) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Checks password is in valid format : length > 0
    private boolean isPasswdValid(String password){
        return password.length() > 0;
    }

    private boolean isValid(String mail, String passwd) {
        if(mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        }
        // Return true if combination valid, false otherwise
        return (mail.equals(validEmail) && passwd.equals(validPassword));
    }

    protected void showErrorDialog(String mail, String passwd) {
		/*
		 * Pop-up dialog to show error
		 */
        AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(android.R.drawable.ic_dialog_alert);
        alertbd.setTitle(getString(R.string.wronglogin));
        alertbd.setMessage(getString(R.string.wrong));
        alertbd.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // we do nothing...
                // dialog close automatically

            }
        });
        alertbd.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("RETURN_VALUE");
                textView.setText(strEditText);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Lifecycle", "MainActivity onStart()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("Lifecycle", "MainActivity onRestart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Lifecycle", "MainActivity onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Lifecycle", "MainActivity onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Lifecycle", "MainActivity onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "MainActivity onDestroy()");
    }

}
