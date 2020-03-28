
package br.com.educafood.view.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.educafood.R;
import br.com.educafood.Utils;
import br.com.educafood.adapter.RecyclerViewMealByCategory;
import br.com.educafood.model.Meals;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment implements CategoryView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageCategory)
    ImageView imageCategory;
    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
    @BindView(R.id.textCategory)
    TextView textCategory;
    
    AlertDialog.Builder descDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));

            Picasso.get().
                    load(getArguments().getString("EXTRA_DATA_IMAGE")).
                    into(imageCategory);

            Picasso.get().
                    load(getArguments().getString("EXTRA_DATA_IMAGE")).
                    into(imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity()).
                    setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            //declarar presenter
            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));

        }

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewMealByCategory recyclerViewMealByCategoryAdapter = new RecyclerViewMealByCategory(getActivity(),meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(recyclerViewMealByCategoryAdapter);
        recyclerViewMealByCategoryAdapter.notifyDataSetChanged();

        recyclerViewMealByCategoryAdapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(getActivity(), "Refeição: "+meals.get(position).getStrInstructions(), Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    @OnClick(R.id.cardCategory)
    public void onClick(){
        descDialog.setPositiveButton("Fechar", (dialog, which) -> {
            dialog.dismiss();
        });
        descDialog.show();
    }
    
}
