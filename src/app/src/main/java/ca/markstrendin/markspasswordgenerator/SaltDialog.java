package ca.markstrendin.markspasswordgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SaltDialog extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.saltdialog);

        Button saveButton = (Button) findViewById(R.id.btnSaveButton);
        Button cancelButton = (Button) findViewById(R.id.btnCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveButtonPress();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelButtonPress();
            }
        });

        try {
            String salt = storage.getKey(this.getApplicationContext());
            TextView txtSaltInput = (TextView) findViewById(R.id.txtSaltInput);
            txtSaltInput.setText(salt);
        } catch (Exception ex) {
            MessageBox("Exception: " + ex.toString());
        }

    }

    private void clearInputFields() {
        TextView txtSaltInput = (TextView) findViewById(R.id.txtSaltInput);

        if (txtSaltInput.getText() != null){
            txtSaltInput.setText(null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearInputFields();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearInputFields();
    }

    /*
     * This displays a toast messagebox with the specified message
     */
    private void MessageBox(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void cancelButtonPress() {
        this.finish();
    }

    private void saveButtonPress() {
        try {
            TextView txtSaltInput = (TextView) findViewById(R.id.txtSaltInput);
            String theKey = txtSaltInput.getText().toString();
            storage.saveKey(this.getApplicationContext(),theKey);
            MessageBox("Master key saved!");
        } catch (Exception ex) {
            MessageBox("Error saving Master Key!");
            MessageBox(ex.toString());
        }
        this.finish();

    }

}
