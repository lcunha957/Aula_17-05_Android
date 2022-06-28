package com.example.app2100522;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //define quem fez a solicitação da permissão
    private static final int PERMISSION_ALL =1;
    String[] PERMISSIONS = {
            Manifest.permission.CAMERA}


  // valores para o intent da foto
  private static final int REQUEST_CAPTURE_IMAGE = 100;
  private static  final int CAMERA_REQUEST = 1888;
  static URI capturedImagUri = null;
  private Button btnFoto;
  private ImageView imFoto;

    public static boolean hasPermission(Context context, String...permissions){
        // passagem de parâmetro: ver se contexto é valido e se tem string
        if((context !=null) && (permissions !=null)){
            for (String permission: permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }


            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int RequestCode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(RequestCode, permissions, grantResult);
        switch (RequestCode){
            case PERMISSION_ALL:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!hasPermission(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
       btnFoto = (Button) findViewById(R.id.btFoto);
        imFoto = (ImageView) findViewById(R.id.imgFoto);

        btnFoto.setOnClickListener (new View.OnClickListener() {
            @Override
                    public void onClick (View view) {
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    imFoto.setImageBitmap(null); //estou limpando a imagem ao clicar no botão
                    TakePhoto();
                }
            }
        });
    };

    private File createTempImageFile () throws IOException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd-hhmmss");
        Calendar cal = Calendar.getInstance();
        String imgFIleName = "DS303" + formatDate.format(cal.getTime());
        File storageDir =  getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imgFileName, // nome do arquivo
                ".jpg", // tipo de arquivo
                storageDir); // diretório onde ficará o arquivo
        return image;
    }
}