package br.com.educafood.api;

import br.com.educafood.model.Categories;
import br.com.educafood.model.Meals;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();
}
