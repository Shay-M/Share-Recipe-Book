package com.pinky.sharerecipebook.repositories;/* Created by Shay Mualem 22/09/2021 */

import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pinky.sharerecipebook.models.AuthAppRepository;

import java.io.File;
import java.util.Date;

// https://www.youtube.com/watch?v=MfCiiTEwt3g
//https://firebase.google.com/docs/storage/android/create-reference?authuser=0#java


public class FirebaseStorgeRepository {

    static FirebaseStorgeRepository INSTANCE;
    // Create a Cloud Storage reference from the app
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;

    public FirebaseStorgeRepository() {
        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReference();
        Log.d("FirebaseStorgeRepository", "");

    }

    public static FirebaseStorgeRepository getInstance() {
        Log.d("getInstance", "");
        if (INSTANCE == null)
            INSTANCE = new FirebaseStorgeRepository();

        return INSTANCE;
    }

    public void UploadFile(Uri imageUri) {
        Log.d("UploadFile", "imageUri:" + imageUri);

        String userID = AuthAppRepository.getInstance().getCurrentUser().getUid();
        //        String fileName = (new File(String.valueOf(imageUri))).getName();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
        //String uniqueString = UUID.randomUUID().toString();

//        String fileName = "" + timeStamp + "_" + uniqueString + ".jpg ";
        String fileName = "" + timeStamp + "" + ".jpg ";

        // Create a reference
        StorageReference storageReference = mStorageRef.child("images/recipe/" + userID + "/" + fileName);

        storageReference.putFile(imageUri)

                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //
                    }
                })


                /* .addOnSuccessListener(taskSnapshot -> {
                 *//*Log.d("UploadFile", "on Success " + storageReference.getPath());
                    Log.d("UploadFile", "getDownloadUrl: " + storageReference.getDownloadUrl());
                    Log.d("UploadFile", "getStorage " + storageReference.getStorage());
                    Log.d("UploadFile", "getMetadata " + storageReference.getMetadata());
*//*
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                })*/

                .addOnFailureListener(e -> Log.d("UploadFile", "onFailure"))
                .addOnProgressListener(snapshot -> {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    Log.d("UploadFile", "progress: " + progress + "% done");
                    //mProgressBar.setProgress((int) progress);
                });
    }

    //            Snackbar.make(findViewById(android.R.id.content),"Image Upload!")

    public void LoadFile(StorageReference storage, String userID, String fileName) {

       /* StorageReference pathReference = storageRef.child("images/stars.jpg");

        // Create a reference to a file from a Cloud Storage URI
        StorageReference gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg");

        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
        StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");
    */
        firebaseStorage.getReference("images/recipe/");


    }

    private void loadImageFromStorage(Uri imageUri) {
        String path = imageUri.toString();
        File f = new File(path, "");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            image.setImageBitmap(b);

    }

}
