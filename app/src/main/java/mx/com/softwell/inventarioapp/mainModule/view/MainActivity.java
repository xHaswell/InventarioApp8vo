package mx.com.softwell.inventarioapp.mainModule.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.softwell.inventarioapp.R;
import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.mainModule.MainPresenter;
import mx.com.softwell.inventarioapp.mainModule.MainPresenterClass;
import mx.com.softwell.inventarioapp.mainModule.view.adapters.OnItemClickListener;
import mx.com.softwell.inventarioapp.mainModule.view.adapters.ProductoAdapter;


public class MainActivity extends AppCompatActivity implements OnItemClickListener, MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcvlProductos)
    RecyclerView rcvlProductos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.contentMain)
    ConstraintLayout contentMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MainPresenter mPresenter;
    private ProductoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configToolbar();
        configAdapter();
        configRecycler();

        mPresenter = new MainPresenterClass(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
    }

    private void configAdapter(){
        mAdapter = new ProductoAdapter(new ArrayList<Producto>(), this);
    }

    private void configRecycler(){
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_columns));
        rcvlProductos.setLayoutManager(linearLayoutManager);
        rcvlProductos.setAdapter(mAdapter);
    }

    /*
     * MainView
     * */

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void add(Producto producto) {
        mAdapter.add(producto);
    }

    @Override
    public void update(Producto producto) {
        mAdapter.update(producto);
    }

    @Override
    public void remove(Producto producto) {
        mAdapter.remove(producto);
    }

    @Override
    public void removeFail() {
        Snackbar.make(contentMain, R.string.main_error_remove, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onShowError(int resMsg) {
        Snackbar.make(contentMain, resMsg,Snackbar.LENGTH_LONG).show();
    }

    /*
     * OnItemClickListener
     * */

    @Override
    public void onItemClick(Producto producto) {

    }

    @Override
    public void onLongItemClick(Producto producto) {
        mPresenter.remove(producto);
    }

    @OnClick(R.id.fab)
    public void onAddClicked() {

    }
}
