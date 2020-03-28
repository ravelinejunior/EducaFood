
package br.com.educafood.view.category;

import java.util.List;

import br.com.educafood.model.Meals;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
