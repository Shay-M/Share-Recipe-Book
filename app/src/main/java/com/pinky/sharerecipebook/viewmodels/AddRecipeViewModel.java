package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 19/09/2021 */

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;

import java.util.ArrayList;

public class AddRecipeViewModel extends ViewModel {
   /* final int GALLERY_REQUEST_CODE = 3;
    public MutableLiveData<ArrayList<Recipe>> liveData;
    ActivityResultLauncher<Uri> cameraFullSizeResultLauncher;
    //ImageView resultIv;
    //    private MutableLiveData<Uri> imgUri;
    ActivityResultLauncher<String> pickContentResultLauncher;*/
   private CameraManagerUrl cameraManagerUrl;
    private Uri imgUri = null;
    public MutableLiveData<ArrayList<Recipe>> liveData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

  /*  public AddRecipeViewModel(@NonNull Application application) {
        super(application);
        cameraManagerUrl = CameraManagerUrl.getInstance();
        //initLaunchers();

    }*/

    final DatabaseReference myRef = database.getReference("recipe");


    public void init() {
        if (liveData != null) {
            return;
        }
        liveData = FirebaseDatabaseRepository.getInstance().getRecipe();
    }

    public LiveData<ArrayList<Recipe>> getRecipeLiveData() {
        return liveData;
    }

    public void AttachNewRecipe(Recipe newRecipe) {
        String key = myRef.push().getKey();
        myRef.child(key).setValue(newRecipe);
    }

}

    /*private void initLaunchers() {

        cameraFullSizeResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        //true if the image saved to the uri given in the launch function

                    }
                });
        pickContentResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                resultIv.setImageURI(result);
            }
        });
    }

    public void cameraFun() {

        cameraFullSizeResultLauncher.launch(Uri.parse(""));
    }

    public void fileFun() {

        pickContentResultLauncher.launch("image/*");
    }

    private void picFromGalleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

*/


    /*public void sendDataFirebase(UserDate currentUserObj, String shiftGet_shiftWants) {

        User_sign_In = currentUserObj;
        // Write a message to the database
        try {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Users")
//                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    .child(currentUserObj.getId());

            myRef.child(shiftGet_shiftWants).setValue(currentUserObj.getShiftWants());
            myRef = database.getReference("Users"); //resat myRef val
        } catch (Exception e) {
            Log.d("send Data Exception", "sendDataFirebase : " + e);
        }

    }*/
//////


    /*
    MutableLiveData<List<Cart>> mutableLiveData = new MutableLiveData<>();
    CartRepo rep = new CartRepo(this);

    public CartViewModel() {
        rep.getCartShit();
    }

    public LiveData<List<Cart>> cartLiveDataShit() {
        return mutableLiveData;
    }


    @Override
    public void CartlIST(List<Cart> carts) {
        mutableLiveData.setValue(carts);
    }*/


