package br.com.educafood.view.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import br.com.educafood.R;
import br.com.educafood.adapter.ViewPagerCategoryAdapter;
import br.com.educafood.model.Categories;
import br.com.educafood.view.home.HomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_Category)
    Toolbar toolbarCategory;

    @BindView(R.id.tabLayout_id_category)
    TabLayout tabLayoutCategory;

    @BindView(R.id.viewPager_id_category)
    ViewPager viewPagerCategory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION,0);

        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPagerCategory.setAdapter(viewPagerCategoryAdapter);
        tabLayoutCategory.setupWithViewPager(viewPagerCategory);
        viewPagerCategory.setCurrentItem(position,true);
        viewPagerCategoryAdapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(toolbarCategory);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private Context getContext(){
        return CategoryActivity.this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
