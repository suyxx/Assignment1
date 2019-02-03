package www.webapp.suyash.assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    private TextView title;
    private FloatingActionButton floatingActionButton;
    private ImageView image;
    private TextView radiotitle;
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    private RadioButton rbtn3;
    private Switch aSwitch;
    private EditText editText;
    private TextView title2;
    private FloatingActionButton floatingActionButton2;
    private ImageView image2;
    private TextView radiotitle2;
    private RadioButton rbtn12;
    private RadioButton rbtn22;
    private RadioButton rbtn32;
    private Switch aSwitch2;
    private Button btncross;
    private ImageView fullImage;
    private int hasimage1 =0;
    private int hasimage2 =0;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private int check = 0;
    private EditText editText2;
    private int  CAMERA = 2;
    private Intent in;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE",
            "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CAMERA"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit:
                Toast.makeText(getApplicationContext(),"Details Saved",Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        floatingActionButton = findViewById(R.id.fab1);
        image = findViewById(R.id.image1);
        radiotitle = findViewById(R.id.radiotitle);
        rbtn1 = findViewById(R.id.option1);
        btncross =findViewById(R.id.closebutton);
        fullImage = findViewById(R.id.imageview);
        rbtn2 = findViewById(R.id.option2);
        rbtn3 = findViewById(R.id.option3);
        aSwitch = findViewById(R.id.simpleSwitch);
        editText = findViewById(R.id.comment);
        title2 = findViewById(R.id.title2);
        floatingActionButton2 = findViewById(R.id.fab2);
        image2 = findViewById(R.id.image2);
        radiotitle2 = findViewById(R.id.radiotitle2);
        rbtn12 = findViewById(R.id.option12);
        rbtn22 = findViewById(R.id.option22);
        linearLayout1 =findViewById(R.id.linearlayout);
        linearLayout2 =findViewById(R.id.linearlayout2);
        rbtn32 = findViewById(R.id.option32);
        aSwitch2 = findViewById(R.id.simpleSwitch2);
        editText2 = findViewById(R.id.comment2);
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
         btncross.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 linearLayout1.setVisibility(View.VISIBLE);
                 linearLayout2.setVisibility(View.GONE);
             }
         });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                editText.setVisibility(View.VISIBLE);}
                else{
                    editText.setVisibility(View.GONE);
                }
            }
        });



        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editText2.setVisibility(View.VISIBLE);}
                else{
                    editText2.setVisibility(View.GONE);
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(hasimage1 ==1){

                linearLayout1.setVisibility(View.GONE);
                Bitmap bitmap = getBitmapFromView(image);
                fullImage.setImageBitmap(bitmap);
                linearLayout2.setVisibility(View.VISIBLE);

              }
              else{
              check =1;
              showPictureDialog();
              }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasimage2 == 2){

                    linearLayout1.setVisibility(View.GONE);
                    Bitmap bitmap = getBitmapFromView(image2);
                    fullImage.setImageBitmap(bitmap);
                    linearLayout2.setVisibility(View.VISIBLE);
                }
                else{
                check =2;
                showPictureDialog();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasimage1 ==1){
                    image.setImageResource(0);
                    hasimage1 =0;
                }
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasimage2 ==2){
                    image2.setImageResource(0);
                   hasimage2 =0;
                }
            }
        });


    }

    private void showPictureDialog() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void takePhotoFromCamera() {
        in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (in.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(in, CAMERA);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmap1 = getBitmapFromView(image);
        bmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray1 = stream.toByteArray();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        Bitmap bmap2 = getBitmapFromView(image2);
        bmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] byteArray2 = stream2.toByteArray();

        outState.putByteArray("image1",byteArray1);
        outState.putByteArray("image2",byteArray2);
        outState.putInt("hasimage1",hasimage1);
        outState.putInt("hasimage2",hasimage2);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
          byte[] byteArray1 = savedInstanceState.getByteArray("image1");
          byte[] byteArray2 = savedInstanceState.getByteArray("image2");
          hasimage1 =savedInstanceState.getInt("hasimage1");
          hasimage2 =savedInstanceState.getInt("hasimage2");
          ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray1);
          Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
          ByteArrayInputStream arrayInputStream2 = new ByteArrayInputStream(byteArray2);
          Bitmap bitmap2 = BitmapFactory.decodeStream(arrayInputStream2);
          super.onRestoreInstanceState(savedInstanceState);

          image.setImageBitmap(bitmap);
          image2.setImageBitmap(bitmap2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        else if (requestCode == CAMERA) {

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            if(check == 1){
                hasimage1 =1;
                image.setImageBitmap(thumbnail);
            }
            else if(check ==2){
                hasimage2 =2;
                image2.setImageBitmap(thumbnail);
            }
        }


    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }


}
