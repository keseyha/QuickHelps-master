package com.group4.gici4.itc.quickhelps;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class EmergencyEdit extends AppCompatActivity {

    private Button pickcontact;
    private EditText desc, tel;
    private Button cancel, save;
    private ImageButton select_image;
    private String imagename = "image";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_edit);

        desc = (EditText) findViewById(R.id.desc);
        tel = (EditText) findViewById(R.id.tel);
        cancel = (Button) findViewById(R.id.cancel);
        save = (Button) findViewById(R.id.save);
        select_image = (ImageButton) findViewById(R.id.select_image);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("resourceimage", imagename);
                data.putExtra("name", desc.getText().toString());
                data.putExtra("tel", tel.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectPic = new Intent(EmergencyEdit.this, ChoosePic.class);
                startActivityForResult(selectPic, 3);
            }
        });
        pickcontact = (Button) findViewById(R.id.pickcontact);
        pickcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                pick.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pick, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                Uri uridata = data.getData();
                Cursor cursor = getContentResolver().query(uridata, null, null, null, null);
                if (cursor.moveToFirst()){
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    desc.setText(name);
                    tel.setText(number);
                }
            }
        } else if (requestCode == 3){
            if (resultCode == RESULT_OK){
                imagename = data.getStringExtra("imagename");
                select_image.setImageResource(getResources().getIdentifier(data.getStringExtra("imagename"), "mipmap", getPackageName()));
            }
        }
    }
}
