package com.english.howf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationDrawer extends Fragment {

    public static final String FILE_NAME="testpref";
    public static final String LEARNED_DRAWER="user_learned_drawer";
    private ActionBarDrawerToggle mdrawertoggle;
    private DrawerLayout mdrawerlayout;
    private boolean userlearnedDrawer;
    private boolean fromsavedInstance;
    private ListView listView;
    private View view;
    private String name;
    private String email;
    MenuAdapter adapter;
    TextView txtname;
    TextView txtemail;
    public NavigationDrawer()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userlearnedDrawer=Boolean.getBoolean(readfromPreference(getActivity(),LEARNED_DRAWER,"false"));

        if(savedInstanceState!=null)
        {
            fromsavedInstance=true;
        }
        Items items1=new Items("UpdateInfo",R.drawable.update);
        Items items2=new Items("Find Friend",R.drawable.find);
        Items items3=new Items("My Friend",R.drawable.user);
        Items items4=new Items("Inbox",R.drawable.username);
        Items items5=new Items("Inbox",R.drawable.username);
        Items items6=new Items("Inbox",R.drawable.username);
        ArrayList arrayList=new ArrayList<Items>();
        arrayList.add(items1);
        arrayList.add(items2);
        arrayList.add(items3);
        arrayList.add(items4);
        arrayList.add(items5);
        arrayList.add(items6);
         adapter=new MenuAdapter(getActivity(),R.layout.menu_row,arrayList);
    }
    public void init(String name,String email)
    {
        this.name=name;
        this.email=email;
        txtname.setText(this.name+"");
        txtemail.setText(this.email+"");
        //Log.i("info",name);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayFragment(position);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
         txtname=(TextView)view.findViewById(R.id.txtname);
         txtemail=(TextView)view.findViewById(R.id.txtemail);
        listView.setAdapter(adapter);

        return view;
    }
    public  void displayFragment(int position)
    {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment=new UpdateFragment();
                Bundle bundle=new Bundle();
                bundle.putString("uid",name);
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment=new FindfriendFragment();
                break;
            case 2:
                fragment=new MyFriendFragment();
                break;
        }
        if(fragment!=null)
        {
            FragmentManager fragmentManager=getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit();
        }
    }

    public void setup(int fragmentid, final DrawerLayout drawerLayout, Toolbar toolbar)
    {
      view=getActivity().findViewById(fragmentid);
       mdrawerlayout=drawerLayout;
        mdrawertoggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.draw_close,R.string.drawer_open){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
             if(!userlearnedDrawer)
             {
                 userlearnedDrawer=true;
                 saveToPreference(getActivity(),LEARNED_DRAWER,userlearnedDrawer+"");
             }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!userlearnedDrawer&&!fromsavedInstance)
        {
            mdrawerlayout.openDrawer(view);
        }
        mdrawerlayout.setDrawerListener(mdrawertoggle);
        mdrawerlayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawertoggle.syncState();
            }
        });
    }
     public static void saveToPreference(Context context,String preferenceName,String preferenceValue){
         SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
         SharedPreferences.Editor editor=sharedPreferences.edit();
         editor.putString(preferenceName,preferenceValue);
         editor.apply();
     }
    public static String readfromPreference(Context context,String preferenceName,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }
}
