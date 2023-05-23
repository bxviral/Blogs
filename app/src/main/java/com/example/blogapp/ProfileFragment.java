package com.example.blogapp;

import static android.content.Context.MODE_PRIVATE;
import static com.example.blogapp.MyApplicationClass.sharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class ProfileFragment extends Fragment {
    static ProfileFragment profileFragment;

    public static ProfileFragment getFragment() {

        if (profileFragment == null) {
            profileFragment = new ProfileFragment();
        }
        return profileFragment;
    }

    EditText profName, profEmail, profPassword;
    ImageView profImageView;
    Button takePhoto, goGallery;
    Bitmap bitmapOfCamera;
    Uri tempUri;
    File finalFile;
    ByteArrayOutputStream bytes;
    boolean x = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profName = view.findViewById(R.id.profName);
        profEmail = view.findViewById(R.id.profEmail);
        profPassword = view.findViewById(R.id.profPassword);
        profImageView = view.findViewById(R.id.profImageView);

        sharedPreferences = getContext().getSharedPreferences("K", MODE_PRIVATE);

        profName.setText(sharedPreferences.getString("name", null));
        profEmail.setText(sharedPreferences.getString("email", null));
        profPassword.setText(sharedPreferences.getString("password", null));

        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (x) {


        }


        profImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bsd = new BottomSheetDialog(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.bottom_dialog_sheet, (LinearLayout) view.findViewById(R.id.sheet));
                bsd.setContentView(view1);
                bsd.show();
                takePhoto = bsd.findViewById(R.id.takePhoto);
                goGallery = bsd.findViewById(R.id.GoGallery);
                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "takePhoto", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityIntent.launch(intent1);
                        bsd.dismiss();
                    }
                });
                goGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "goGallery", Toast.LENGTH_SHORT).show();
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // this will open the whole new gallery activity
                        startActivityIntentGallery.launch(galleryIntent);
                        bsd.dismiss();
                    }
                });
            }
        });


        return view;
    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        assert result.getData() != null;
                        Bundle bundle = result.getData().getExtras();
                        bitmapOfCamera = (Bitmap) bundle.get("data");
                        profImageView.setImageBitmap(bitmapOfCamera);
                        x = true;
                        tempUri = getImageUri(getContext(), bitmapOfCamera);
                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        finalFile = new File(getRealPathFromURI(tempUri));
                        Log.e("PPP", "finalFile: " + finalFile.getAbsolutePath());

                        sharedPreferences = getContext().getSharedPreferences("K", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("profImage", finalFile.getAbsolutePath());
                        myEdit.apply();


                        String v = sharedPreferences.getString("profImage", null);
                        Log.e("TAG", "onActivityResult: " + v);
                    }
                }
            }
    );
    ActivityResultLauncher<Intent> startActivityIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Uri selectedImageUri = data.getData();
                        InputStream inputStream = null;
                        try {
                            inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(selectedImageUri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        profImageView.setImageBitmap(bitmap);
                        x = true;
                        tempUri = getImageUri(getContext(), bitmap);
                        finalFile = new File(getRealPathFromURI(tempUri));
                        Log.e("PPP", "onActivityResult:  " + finalFile.getAbsolutePath());

                    }
                }
            }
    );

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "profilePic", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getApplicationContext().getContentResolver() != null) {
            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
}