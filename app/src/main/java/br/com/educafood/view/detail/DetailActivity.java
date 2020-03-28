
package br.com.educafood.view.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.educafood.R;
import br.com.educafood.Utils;
import br.com.educafood.model.Categories;
import br.com.educafood.model.Meals;
import br.com.educafood.view.home.HomePresenter;
import br.com.educafood.view.home.HomeView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.educafood.view.home.HomeActivity.EXTRA_DETAIL;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.mealThumb)
    ImageView mealThumb;
    
    @BindView(R.id.category)
    TextView category;
    
    @BindView(R.id.country)
    TextView country;
    
    @BindView(R.id.instructions)
    TextView instructions;

    @BindView(R.id.ingredient)
    TextView ingredients;
    
    @BindView(R.id.measure)
    TextView measures;
    
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    
    @BindView(R.id.youtube)
    TextView youtube;
    
    @BindView(R.id.source)
    TextView source;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActionBar();

        Intent intent = getIntent();
        String mealName= intent.getStringExtra(EXTRA_DETAIL);

        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealById(mealName);


    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setMeal(Meals.Meal meal) {

        try{
            Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
            collapsingToolbarLayout.setTitle(meal.getStrMeal());
            category.setText(meal.getStrCategory());
            country.setText(meal.getStrArea());
            instructions.setText(meal.getStrInstructions());
            setupActionBar();

            if (!meal.getStrIngredient1().isEmpty() && meal.getStrIngredient1() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient1());
            }
            if (!meal.getStrIngredient2().isEmpty() && meal.getStrIngredient2() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient2());
            }
            if (!meal.getStrIngredient3().isEmpty() && meal.getStrIngredient3() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient3());
            }
            if (!meal.getStrIngredient4().isEmpty() && meal.getStrIngredient4() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient4());
            }
            if (!meal.getStrIngredient5().isEmpty() && meal.getStrIngredient5() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient5());
            }
            if (!meal.getStrIngredient6().isEmpty() && meal.getStrIngredient6() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient6());
            }
            if (!meal.getStrIngredient7().isEmpty() && meal.getStrIngredient7() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient7());
            }
            if (!meal.getStrIngredient8().isEmpty() && meal.getStrIngredient8() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient8());
            }
            if (!meal.getStrIngredient9().isEmpty() && meal.getStrIngredient9() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient9());
            }
            if (!meal.getStrIngredient10().isEmpty() && meal.getStrIngredient10() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient10());
            }
            if (!meal.getStrIngredient11().isEmpty() && meal.getStrIngredient11() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient11());
            }
            if (!meal.getStrIngredient12().isEmpty() && meal.getStrIngredient12() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient12());
            }
            if (!meal.getStrIngredient13().isEmpty() && meal.getStrIngredient13() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient13());
            }
            if (!meal.getStrIngredient14().isEmpty() && meal.getStrIngredient14() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient14());
            }
            if (!meal.getStrIngredient15().isEmpty() && meal.getStrIngredient15() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient15());
            }
            if (!meal.getStrIngredient16().isEmpty() && meal.getStrIngredient16() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient16());
            }
            if (!meal.getStrIngredient17().isEmpty() && meal.getStrIngredient17() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient17());
            }
            if (!meal.getStrIngredient18().isEmpty() && meal.getStrIngredient18() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient18());
            }
            if (!meal.getStrIngredient19().isEmpty() && meal.getStrIngredient19() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient19());
            }
            if (!meal.getStrIngredient20().isEmpty() && meal.getStrIngredient20() != null) {
                ingredients.append("\n \u2022 " + meal.getStrIngredient20());
            }

            if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure1());
            }
            if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure2());
            }
            if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure3());
            }
            if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure4());
            }
            if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure5());
            }
            if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure6());
            }
            if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure7());
            }
            if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure8());
            }
            if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure9());
            }
            if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure10());
            }
            if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure11());
            }
            if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure12());
            }
            if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure13());
            }
            if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure14());
            }
            if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure15());
            }
            if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure16());
            }
            if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure17());
            }
            if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure18());
            }
            if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure19());
            }
            if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
                measures.append("\n : " + meal.getStrMeasure20());
            }


            youtube.setOnClickListener(v -> {
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                String uriYoutube = meal.getStrYoutube();

                if (uriYoutube.isEmpty() || uriYoutube == null) {
                    //jogar para uri aleatoria
                    uriYoutube = String.valueOf(Uri.parse("https://www.youtube.com/channel/UCB0cIRr6cEkpzxtm3KTV3uw/"));
                    intentYoutube.setData(Uri.parse(uriYoutube));
                    startActivity(intentYoutube);

                }
                else{
                    intentYoutube.setData(Uri.parse(uriYoutube));
                    startActivity(intentYoutube);
                }

            });

            source.setOnClickListener(v -> {
                Intent intentSource = new Intent(Intent.ACTION_VIEW);
                String uriSource = meal.getStrSource();

                if (uriSource.isEmpty() || uriSource == null) {

                    uriSource = String.valueOf(Uri.parse("https://www.tudogostoso.com.br/"));
                    intentSource.setData(Uri.parse(uriSource));
                    startActivity(intentSource);
                }
                else{
                    intentSource.setData(Uri.parse(uriSource));
                    startActivity(intentSource);
                }

            });
        }catch (Exception e){
            Toast.makeText(this, "Não foi possivel carregar alguns valores!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        youtube.setOnClickListener(v -> {
            Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
            String uriYoutube = meal.getStrYoutube();

            if (uriYoutube.isEmpty() || uriYoutube == null) {
                //jogar para uri aleatoria
                uriYoutube = String.valueOf(Uri.parse("https://www.youtube.com/channel/UCB0cIRr6cEkpzxtm3KTV3uw/"));
                intentYoutube.setData(Uri.parse(uriYoutube));
                startActivity(intentYoutube);

            }
            else{
                intentYoutube.setData(Uri.parse(uriYoutube));
                startActivity(intentYoutube);
            }

        });


        source.setOnClickListener(v -> {
            Intent intentSource = new Intent(Intent.ACTION_VIEW);
            String uriSource = meal.getStrSource();

            if (uriSource.isEmpty() || uriSource == null) {

                uriSource = String.valueOf(Uri.parse("https://www.tudogostoso.com.br/"));
                intentSource.setData(Uri.parse(uriSource));
                startActivity(intentSource);

            }
            else{
                intentSource.setData(Uri.parse(uriSource));
                startActivity(intentSource);
            }

        });


    }


    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Erro",message);
    }
}
