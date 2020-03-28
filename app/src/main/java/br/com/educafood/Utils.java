package br.com.educafood;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import br.com.educafood.api.FoodApi;
import br.com.educafood.api.FoodClient;

public class Utils {

    public static FoodApi getApi() {
        return FoodClient.getFoodClient().create(FoodApi.class);
    }

    public static FoodApi getApiV2() {
        return FoodClient.getFoodClientV2().create(FoodApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
