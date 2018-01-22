package com.example.tapos.myapplication.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.tapos.myapplication.Database.MagicOperations;
import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.adpaters.HomeAdapter;
import com.example.tapos.myapplication.models.Magic;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<Magic> mMagicList;
    private MagicOperations mMagicOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMagicOperations = MagicOperations.getInstance(this);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mMagicList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
            Glide.with(this).load(R.drawable.magic).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private List<Magic> getDummyData() {

        List<Magic> magicList = new ArrayList<>();
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10};

        Magic a = new Magic("Turning coffee into coins",getResources().getString(R.string.magic_des_1), covers[0]);
        magicList.add(a);
        a = new Magic("The magic sword",
                getResources().getString(R.string.magic_des_2), covers[1]);
        magicList.add(a);

        a = new Magic("Pulling cards out of thin air", getResources().getString(R.string.mg_des_3), covers[2]);
        magicList.add(a);

        a = new Magic("Bullet catch", getResources().getString(R.string.mg_des_4), covers[3]);
        magicList.add(a);

        a = new Magic("Balloon swallowing", "The key to success here is tiny holes at the top of the balloon. The magician puts a balloon in his mouth and presses it against his tongue so the air releases. This creates the illusion that the magician is eating the balloon. But he needs to hurry after he inflates the balloon if he doesn’t want to get caught", covers[4]);
        magicList.add(a);

        a = new Magic("Run over by a truck", "It’s entirely based on the laws of physics. A real truck runs over the unprotected magician but doesn’t harm him. The key to the successful trick is a set of counterweights on the far side of the truck, not visible to the audience. The weights shift the balance of the truck, allowing the wheels on the lighter side to roll over the top of the magician.", covers[5]);
        magicList.add(a);

        a = new Magic("Cut and restored rope","A cut rope is magically back in one piece...but the magician actually never cuts the rope. He has a short piece of rope concealed in his hand and cuts that instead. Using sleight of hand and pulling the short rope up, he creates the illusion of restoring the rope only by magic.", covers[6]);
        magicList.add(a);

        a = new Magic("Mind reading", "All mind reading is based on human psychology, and a magician usually just guesses the most probable answer. When a magician performs the \"Gray Elephant From Denmark\" trick, he asks the audience certain math questions, the answer to which will always be the same. Try to perform it at home, and tell us in the comments section if you managed to trick your friends!", covers[7]);
        magicList.add(a);

        a = new Magic("Levitating objects", "To perform this trick, you need an extremely thin rubber band and a pencil to hide in your sleeve. Depending on the object you are going to levitate, the technique may differ, but the main idea will stay the same: fix the support so no one can see it, and turn on your charm", covers[8]);
        magicList.add(a);

        a = new Magic("Water suspended in midair", "The magician takes a glass, fills it with water, places a piece of cardboard on the glass, and then turns the glass upside down. The cardboard holds the water because of the vacuum inside the glass. But why doesn’t the water pour out when the magician takes away the cardboard? On the bottom of the glass, there’s a small hole that allows him to create the vacuum and hold or release the water. A transparent lid also helps the water to stay put.", covers[9]);
        magicList.add(a);

       return magicList;

    }



    // create main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int menuId = item.getItemId();
        if (menuId == item.getItemId()) {
            addNewMagic();
        }
        return true;

    }

    private void addNewMagic() {
        startActivity(new Intent(this, NewMagicActivity.class));
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareAlbums() {
        List<Magic> magics = mMagicOperations.getAllMagics();

        for (Magic magic : getDummyData()){
            magics.add(magic);
        }

        adapter.addALL(magics);

        adapter.notifyDataSetChanged();
    }

    public void showDetails(Magic magic) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("magic", magic);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            adapter = new HomeAdapter(this, mMagicList);
        }
        prepareAlbums();
        recyclerView.setAdapter(adapter);
    }
}

