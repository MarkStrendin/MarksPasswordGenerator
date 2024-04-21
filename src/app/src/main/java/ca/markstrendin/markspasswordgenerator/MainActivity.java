package ca.markstrendin.markspasswordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity
{
    String Salt;
    String Input;
    Button generateButton, btnAlphaSel8, btnAlphaSel12, btnAlphaSel15,
            btnAlphaSel20, btnSpecialSel8, btnSpecialSel12, btnSpecialSel15,
            btnSpecialSel20, saltDialogButton;
    TextView txtInput, txtOutput_Alpha, txtOutput_Special;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        /* Initialize some variables */

        Salt = "";
        Input = "";

        /* Set up references to the UI elements */

        generateButton = (Button) findViewById(R.id.btnGenerate);
        saltDialogButton = (Button) findViewById(R.id.btnOpenSaltDialog);
        btnAlphaSel8 = (Button) findViewById(R.id.btnAlphaSel8);
        btnAlphaSel12 = (Button) findViewById(R.id.btnAlphaSel12);
        btnAlphaSel15 = (Button) findViewById(R.id.btnAlphaSel15);
        btnAlphaSel20 = (Button) findViewById(R.id.btnAlphaSel20);
        btnSpecialSel8 = (Button) findViewById(R.id.btnSpecialSel8);
        btnSpecialSel12 = (Button) findViewById(R.id.btnSpecialSel12);
        btnSpecialSel15 = (Button) findViewById(R.id.btnSpecialSel15);
        btnSpecialSel20 = (Button) findViewById(R.id.btnSpecialSel20);

        txtInput = (TextView) findViewById(R.id.txtInput);
        txtOutput_Alpha = (TextView) findViewById(R.id.txtOutput_Alpha);
        txtOutput_Special = (TextView) findViewById(R.id.txtOutput_Special);

        /* Check for the existence of the master key file, and if
         * it doesn't exist, create a new one */

        if (!storage.checkKey(this.getApplicationContext())) {
            MessageBox("Unrecoverable error reading the saved Master Key");
            this.finish();
        }

        /* Set up onClick listeners */

        generateButton.setOnClickListener(v -> GenerateButtonPress());

        saltDialogButton.setOnClickListener(v -> {
            Intent saltDialogIntent = new Intent("ca.markstrendin.markspasswordgenerator.SALTDIALOG");
            startActivity(saltDialogIntent);
        });

        btnAlphaSel8.setOnClickListener(v -> {
            MessageBox("First 8 characters copied to clipboard!");
            copyCharactersToClipboard(8,txtOutput_Alpha.getText().toString());
        });

        btnAlphaSel12.setOnClickListener(v -> {
            MessageBox("First 12 characters copied to clipboard!");
            copyCharactersToClipboard(12,txtOutput_Alpha.getText().toString());
        });

        btnAlphaSel15.setOnClickListener(v -> {
            MessageBox("First 15 characters copied to clipboard!");
            copyCharactersToClipboard(15,txtOutput_Alpha.getText().toString());
        });

        btnAlphaSel20.setOnClickListener(v -> {
            MessageBox("First 20 characters copied to clipboard!");
            copyCharactersToClipboard(20,txtOutput_Alpha.getText().toString());
        });

        btnSpecialSel8.setOnClickListener(v -> {
            MessageBox("First 8 characters copied to clipboard!");
            copyCharactersToClipboard(8,txtOutput_Special.getText().toString());
        });

        btnSpecialSel12.setOnClickListener(v -> {
            MessageBox("First 12 characters copied to clipboard!");
            copyCharactersToClipboard(12,txtOutput_Special.getText().toString());
        });

        btnSpecialSel15.setOnClickListener(v -> {
            MessageBox("First 15 characters copied to clipboard!");
            copyCharactersToClipboard(15,txtOutput_Special.getText().toString());
        });

        btnSpecialSel20.setOnClickListener(v -> {
            MessageBox("First 20 characters copied to clipboard!");
            copyCharactersToClipboard(20,txtOutput_Special.getText().toString());
        });

    }


    /*
     * This copies the specified number of characters (numCharacters) from
     *  the given string (stringToCopy) to the clipboard
     */
    private void copyCharactersToClipboard(int numCharacters, String stringToCopy) {
        String returnMe = "";
        if (stringToCopy.length() > numCharacters) {
            returnMe = stringToCopy.substring(0, numCharacters);
        } else {
            returnMe = stringToCopy;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", returnMe);
        clipboard.setPrimaryClip(clip);
    }

    /*
     * This displays a toast messagebox with the specified message
     */
    private void MessageBox(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /*
     * This forces the software keyboard to be hidden so that
     *  it doesn't block the output text box
     */
    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtInput.getWindowToken(), 0);
    }

    /*
     * Enable or Disable the "copy to clipboard" buttons.
     * The buttons start disabled, because copying NULL to
     * the clipboard is counterproductive.
     */
    private void toggleClipBoardButtons(boolean setToThis) {
        btnAlphaSel8.setEnabled(setToThis);
        btnAlphaSel12.setEnabled(setToThis);
        btnAlphaSel15.setEnabled(setToThis);
        btnAlphaSel20.setEnabled(setToThis);
        btnSpecialSel8.setEnabled(setToThis);
        btnSpecialSel12.setEnabled(setToThis);
        btnSpecialSel15.setEnabled(setToThis);
        btnSpecialSel20.setEnabled(setToThis);
    }

    /*
     * This is called when the "Generate" button is pressed
     */
    private void GenerateButtonPress() {
        try {
            String saltFromFile = storage.getKey(this.getApplicationContext());
            String input = txtInput.getText().toString();
            txtOutput_Alpha.setText(crypto.createPassword_Alpha(input, saltFromFile));
            txtOutput_Special.setText(crypto.createPassword_Special(input, saltFromFile));

        } catch (Exception ex) {
            MessageBox("Error accessing Master Key");
            MessageBox(ex.toString());
        }
        /*
         */
        hideSoftKeyboard();
        toggleClipBoardButtons(true);
    }

    private void clearInputFields() {

        if (txtInput.getText() != null){
            txtInput.setText(null);
        }

        if (txtOutput_Alpha.getText() != null){
            txtOutput_Alpha.setText(null);
        }

        if (txtOutput_Special.getText() != null){
            txtOutput_Special.setText(null);
        }

        toggleClipBoardButtons(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearInputFields();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearInputFields();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearInputFields();
    }
}