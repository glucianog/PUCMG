package lddm.computacao.pucminas.organapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import lddm.computacao.pucminas.organapp.arquivos.Arquivo;
import lddm.computacao.pucminas.organapp.data.Constant;
import lddm.computacao.pucminas.organapp.fragment.ArquivoFragment;
import lddm.computacao.pucminas.organapp.fragment.InserirFragment;
import lddm.computacao.pucminas.organapp.fragment.LinkFragment;
import lddm.computacao.pucminas.organapp.fragment.MainFragment;
import lddm.computacao.pucminas.organapp.fragment.VideoFragment;
import lddm.computacao.pucminas.organapp.navigationdrawer.NavMenuAdapter;
import lddm.computacao.pucminas.organapp.navigationdrawer.NavMenuModel;
import lddm.computacao.pucminas.organapp.navigationdrawer.SubTitle;
import lddm.computacao.pucminas.organapp.navigationdrawer.TitleMenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity
        implements NavMenuAdapter.MenuItemClickListener,
                   InserirFragment.OnFragmentInteractionListener {



    final public String CONVIDADO_TAG = "InserirFragment";

        // Lista de Arquivos

    String fileArquivosName = "arquivos.txt", fileLinksName = "links.txt",
            fileVideosName = "videos.txt";

    ArrayList<Arquivo> arquivos,links,videos = new ArrayList<>();

    Toolbar toolbar;
    DrawerLayout drawer;
    ArrayList<NavMenuModel> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        // Iniciliza as listas utilizadas
        iniciaListas();

        // Le dados do arquivo, caso existam
        readFile();




        drawer = (DrawerLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        setNavigationDrawerMenu();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setNavigationDrawerMenu() {
        NavMenuAdapter adapter = new NavMenuAdapter(this, getMenuList(), this);
        RecyclerView navMenuDrawer = (RecyclerView) findViewById(R.id.main_nav_menu_recyclerview);
        navMenuDrawer.setAdapter(adapter);
        navMenuDrawer.setLayoutManager(new LinearLayoutManager(this));
        navMenuDrawer.setAdapter(adapter);

//        INITIATE SELECT MENU
        adapter.selectedItemParent = menu.get(0).menuTitle;
        onMenuItemClick(adapter.selectedItemParent);
        adapter.notifyDataSetChanged();
    }

    public void saveFile() {

        try{
            FileOutputStream fos = openFileOutput(fileArquivosName, MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(fos);

            for(int i = 0 ; i< arquivos.size() ; i++ ) {
                outputFile.write(arquivos.get(i).getLink() + "\n");
            }
            outputFile.flush();
            outputFile.close();

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }

        try{
            FileOutputStream fos = openFileOutput(fileLinksName, MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(fos);

            for(int i = 0 ; i< links.size() ; i++ ) {
                outputFile.write(links.get(i).getLink() + "\n");
            }
            outputFile.flush();
            outputFile.close();

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }

        try{
            FileOutputStream fos = openFileOutput(fileVideosName, MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(fos);

            for(int i = 0 ; i< videos.size() ; i++ ) {
                outputFile.write(videos.get(i).getLink()+ "\n");
            }
            outputFile.flush();
            outputFile.close();

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }

    }

    public void readFile() {
        arquivos.clear();
        links.clear();
        videos.clear();

        File file = getApplicationContext().getFileStreamPath(fileArquivosName);
        String lineFromFile;

        if(file.exists()) {
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        openFileInput(fileArquivosName)));
                while((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile);
                    Arquivo arquivo = new Arquivo(tokens.nextToken());
                    arquivos.add(arquivo);
                }
                reader.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        file = getApplicationContext().getFileStreamPath(fileLinksName);
        lineFromFile = "";
        if(file.exists()) {
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        openFileInput(fileLinksName)));
                while((lineFromFile = reader.readLine()) != null ) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile);
                    Arquivo arquivo = new Arquivo(tokens.nextToken());
                    links.add(arquivo);
                }
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        file = getApplicationContext().getFileStreamPath(fileVideosName);
        lineFromFile = "";
        if(file.exists()) {
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        openFileInput(fileVideosName)));
                while((lineFromFile = reader.readLine()) != null ) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile);
                    Arquivo arquivo = new Arquivo((tokens.nextToken()));
                    videos.add(arquivo);
                }
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



    private List<TitleMenu> getMenuList() {
        List<TitleMenu> list = new ArrayList<>();

        menu = Constant.getMenuNavigasi();
        for (int i = 0; i < menu.size(); i++) {
            ArrayList<SubTitle> subMenu = new ArrayList<>();
            if (menu.get(i).subMenu.size() > 0){
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    subMenu.add(new SubTitle(menu.get(i).subMenu.get(j).subMenuTitle));
                }
            }

            list.add(new TitleMenu(menu.get(i).menuTitle, subMenu, menu.get(i).menuIconDrawable));
        }

        return list;
    }



    @Override
    public void onMenuItemClick(String itemString) {

        for (int i = 0; i < menu.size(); i++) {
            if (itemString.equals(menu.get(i).menuTitle)){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, menu.get(i).fragment)
                        .commit();
                break;
            }else{
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    if (itemString.equals("Links")){
                        LinkFragment fragment = LinkFragment.newInstance();
                        fragment.setLinkList(links);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_content,fragment)
                                .commit();
                        break;
                    }
                    if (itemString.equals("Arquivos")){
                        ArquivoFragment fragment = ArquivoFragment.newInstance();
                        fragment.setArquivosList(arquivos);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_content,fragment)
                                .commit();
                        break;
                    }
                    if (itemString.equals("Vídeos")){
                        VideoFragment fragment = VideoFragment.newInstance();
                        fragment.setArquivos(videos);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_content,fragment)
                                .commit();
                        break;
                    }
                }
            }

        }

        if (drawer != null){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void iniciaListas() {
        arquivos = new ArrayList<>();
        links    = new ArrayList<>();
        videos   = new ArrayList<>();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                arquivos.add(new Arquivo((uri.toString())));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void setArchiveData(Arquivo arquivo, String Whocalls) throws CloneNotSupportedException {
        if(Whocalls.equals("arquivo")) arquivos.add(arquivo);
        if(Whocalls.equals("link")) links.add(arquivo);
        if(Whocalls.equals("video")) videos.add(arquivo);

    }

    public void onStop(){
        super.onStop();
        deleteFile();
        saveFile();
    }

    public void deleteFile() {
        File file = new File(getFilesDir(),fileArquivosName);
        if( file.exists()) {
            deleteFile(fileArquivosName);
        }
         file = new File(getFilesDir(),fileLinksName);
        if( file.exists()) {
            deleteFile(fileArquivosName);
        }

        file = new File(getFilesDir(),fileVideosName);
        if( file.exists()) {
            deleteFile(fileArquivosName);
        }
    }




}
