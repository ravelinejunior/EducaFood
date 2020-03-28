package br.com.educafood.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

import br.com.educafood.R;
import br.com.educafood.Utils;
import br.com.educafood.adapter.RecyclerViewHomeAdapter;
import br.com.educafood.adapter.ViewPagerHeaderAdapter;
import br.com.educafood.model.Categories;
import br.com.educafood.model.Meals;
import br.com.educafood.view.category.CategoryActivity;
import br.com.educafood.view.detail.DetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

   @Nullable
    @BindView(R.id.viewPager_Header_home_id)
    ViewPager viewPagerMeal;

    @Nullable
    @BindView(R.id.recyclerView_category_id_home)
    RecyclerView recyclerViewCategoria;
    HomePresenter homePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //instanciar ButterKnife Bind
        ButterKnife.bind(this);

        homePresenter = new HomePresenter(this); // passando this ele vai pegar o implements homeView
        //homePresenter.getMeals(); com versão v1
        homePresenter.getMealsV2();
        homePresenter.getCategories();
    }

    public Context getContext(){
        return HomeActivity.this;
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meal) {
        //verificar se check meal result tem valores
        /*for (Meals.Meal mealResultado: meal){
            Log.i("ResultadoMeal",mealResultado.getStrMeal());
        }*/

        ViewPagerHeaderAdapter viewPagerHeaderAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(viewPagerHeaderAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        viewPagerHeaderAdapter.notifyDataSetChanged();

        viewPagerHeaderAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent i = new Intent(getContext(), DetailActivity.class);
            i.putExtra(EXTRA_DETAIL, (Serializable) mealName.getText().toString());
            startActivity(i);

        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter recyclerViewHomeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategoria.setAdapter(recyclerViewHomeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategoria.setLayoutManager(layoutManager);
        recyclerViewCategoria.setClipToPadding(false);
        recyclerViewCategoria.setNestedScrollingEnabled(true);
        //enviar
        recyclerViewHomeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION,position);
            startActivity(intent);
        });
        recyclerViewHomeAdapter.notifyDataSetChanged();


    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Titulo", message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
