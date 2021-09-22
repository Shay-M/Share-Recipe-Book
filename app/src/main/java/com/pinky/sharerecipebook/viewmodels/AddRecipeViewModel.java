package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 19/09/2021 */

import android.app.Application;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class AddRecipeViewModel extends AndroidViewModel {



    public AddRecipeViewModel(@NonNull Application application) {
        super(application);
    }



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
    /*MutableLiveData<List<Cart>> mutableLiveData = new MutableLiveData<>();
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

}
