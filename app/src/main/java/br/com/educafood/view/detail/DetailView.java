package br.com.educafood.view.detail;

import br.com.educafood.model.Meals;

public interface DetailView {

    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
