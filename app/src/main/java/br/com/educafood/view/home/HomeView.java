package br.com.educafood.view.home;

import java.util.List;

import br.com.educafood.model.Categories;
import br.com.educafood.model.Meals;

public interface HomeView {

    void showLoading();

    void hideLoading();

    void setMeals(List<Meals.Meal> meal);

    void setCategory(List<Categories.Category> category);

    void onErrorLoading(String message);


}
