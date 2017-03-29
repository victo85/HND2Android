package com.daniel.hnd2.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.hnd2.BuildConfig;
import com.daniel.hnd2.activities.EditActivity;
import com.daniel.hnd2.Preferencias;
import com.daniel.hnd2.R;
import com.daniel.hnd2.beans.UsuarioBean;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    private TextView txtNombre, txtApellidos, txtUsuario;
    private ImageButton btn_edit, btn_editImg, btn_editCamera;
    private ImageView imgPerfil;


    private UsuarioBean usuarioBean;
    private Uri mMediaUri;

    private static final int TAKE_PICTURE = 0;
    private final int PICTURE_KEY = 1;
    static final int MEDIA_TYPE_IMAGE = 2;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtNombre = (TextView) view.findViewById(R.id.txtNombre);
        txtApellidos = (TextView) view.findViewById(R.id.txtApellidos);
        txtUsuario = (TextView) view.findViewById(R.id.txtUsuario);
        btn_edit = (ImageButton) view.findViewById(R.id.btn_edit);
        btn_editImg = (ImageButton) view.findViewById(R.id.btn_editImg);
        btn_editCamera = (ImageButton) view.findViewById(R.id.btn_editCamera);
        imgPerfil = (ImageView) view.findViewById(R.id.imgPerfil);

        Preferencias preferencias = new Preferencias(getActivity());
        usuarioBean = preferencias.getUsuario();

        txtNombre.setText("Nombre: " + usuarioBean.getNombre());
        txtApellidos.setText("Apellidos: " + usuarioBean.getApellidos());
        txtUsuario.setText("Usuario: " + usuarioBean.getUsuario());

        if(usuarioBean.getImgPerfil() == null){
            imgPerfil.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.imagen_usuario));
        }else{
            imgPerfil.setImageURI(Uri.parse(usuarioBean.getImgPerfil()));
        }

        btn_edit.setOnClickListener(this);
        btn_editImg.setOnClickListener(this);
        btn_editCamera.setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_edit:
                Intent intent = new Intent(getActivity(), EditActivity.class);

                startActivity(intent);

                break;

            case R.id.btn_editImg:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Verifica permisos para Android 6.0+

                    int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                    if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                        abrirGaleria();
                    }else{
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                }else{
                    abrirGaleria();
                }

                break;

            case R.id.btn_editCamera:
                abrirCamara();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PICTURE_KEY:
                if(resultCode == getActivity().RESULT_OK){
                    Uri path = data.getData();
                    imgPerfil.setImageURI(path);
                    usuarioBean.setImgPerfil(path.toString());
                    Preferencias preferencias = new Preferencias(getActivity());
                    preferencias.setUsuario(usuarioBean);

                }
                break;

            case TAKE_PICTURE:

                mMediaUri = data.getData();

                imgPerfil.setImageURI(mMediaUri);

                usuarioBean.setImgPerfil(mMediaUri.toString());
                Preferencias preferencias = new Preferencias(getActivity());
                preferencias.setUsuario(usuarioBean);


                break;
        }
    }

    private Uri getOutputMediaFileUri(int mediaTypeImage) {

        File mediaStorageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        String fileName = "";
        String fileType = "";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());

        fileName = "IMG_" + timeStamp;
        fileType = ".jpg";

        File mediaFile;
        try {
            mediaFile = File.createTempFile(fileName, fileType, mediaStorageDir);
            Log.i("st", "File: " + Uri.fromFile(mediaFile));
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("St", "Error creating file: " + mediaStorageDir.getAbsolutePath() + fileName + fileType);
            return null;
        }
        return FileProvider.getUriForFile(getActivity(),
                BuildConfig.APPLICATION_ID + ".provider",
                mediaFile);
    }

    private void abrirCamara() {
        mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
        startActivityForResult(photoIntent, TAKE_PICTURE);
    }

    private void abrirGaleria(){
        Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGaleria.setType("image/*");
        startActivityForResult(intentGaleria.createChooser(intentGaleria, "Selecciona una app de imagen"), PICTURE_KEY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirGaleria();

                } else {
                    Toast.makeText(getActivity(), "Debes aceptar los permisos para acceder a la galería", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
