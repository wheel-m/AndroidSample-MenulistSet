package android.wings.websarva.menulistset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /**
     * リストビューを表すフィールド。
     */
    private ListView _lvMenu;
    /**
     * リストビューに表示するリストデータ。
     */
    private List<Map<String, Object>> _menuList;
    /**
     * SimpleAdapterの第4引数fromに使用する定数フィールド。
     */
    private static final String[] FROM = {"name", "price"};
    /**
     * SimpleAdapterの第5引数toに使用する定数フィールド。
     */
    private static final int[] TO = {R.id.tvMainMenuNameRow, R.id.tvMainMenuPriceRow};

    private Button btnMenuset;
    private Button btnOrder;

    public Intent menulist_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面部品ListViewを取得し、フィールドに格納。
        _lvMenu = findViewById(R.id.lvMenu);
        //SimpleAdapterで使用する定食メニューListオブジェクトをprivateメソッドを利用して用意し、フィールドに格納。
        List<Map<String, Object>> menuList = new ArrayList<>();
        _menuList = menuList;
        //SimpleAdapterを生成。
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.activity_main_menu_row, FROM, TO);
        //アダプタの登録。
        _lvMenu.setAdapter(adapter);
        //リストタップのリスナクラス登録。
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        //コンテキストメニューをリストビューに登録。
        registerForContextMenu(_lvMenu);

        // ボタンを設定
        findViewById(R.id.btMenuset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //インテントオブジェクトを生成。
                menulist_intent = new Intent(MainActivity.this, MenuSet.class)
                        .setAction(Intent.ACTION_VIEW);
                //第2画面に送るデータを格納。
                menulist_intent.putExtra("menuName", "");
                //MenuThanksActivityでのデータ受け取りと合わせるために、数字で渡す
                menulist_intent.putExtra("menuPrice", 0);
                //メニューセットの画面起動。
                startActivityForResult(menulist_intent, 1000);
            }
        });

        // ボタンを設定
        findViewById(R.id.btOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //インテントオブジェクトを生成。
                menulist_intent = new Intent(MainActivity.this, MenuOrderActivity.class)
                        .setAction(Intent.ACTION_VIEW);

                int goukei = 0;
                for (int l=0;l<_lvMenu.getCount();l++){
                    //タップされた行のデータを取得。
                    Map<String, Object> item = (Map<String, Object>) _lvMenu.getItemAtPosition(l);
                    goukei = goukei + (Integer)item.get("price");
                }

                //インテントオブジェクトを生成。
                Intent intent = new Intent(MainActivity.this, MenuOrderActivity.class);
                //第2画面に送るデータを格納。
                //MenuThanksActivityでのデータ受け取りと合わせるために、数字で渡す
                intent.putExtra("menuPrice", goukei);
                //第2画面の起動。
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // 注意：superメソッドは呼ぶようにする
        // Activity側のonActivityResultで呼ばないとFragmentのonActivityResultが呼ばれない
        super.onActivityResult(requestCode,resultCode,data);

        switch(requestCode){
            case(1000):
                // 呼び出し先のActivityから結果を受け取る
                if(resultCode == RESULT_OK){
                    String menuName = data.getStringExtra("menuName");
                    Integer menuPrice = data.getIntExtra("menuPrice", 0);
                    Log.d("menuName", menuName);
                    Log.d("menuPrice", Integer.toString(menuPrice));

                    //List<Map<String, Object>> menuList = new ArrayList<>();
                    Map<String, Object> menu = new HashMap<>();
                    menu.put("name", menuName);
                    menu.put("price", menuPrice);
                    menu.put("desc", "");
                    _menuList.add(menu);

                    SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.activity_main_menu_row, FROM, TO);
                    //アダプタの登録。
                    _lvMenu.setAdapter(adapter);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 注文処理を行うメソッド。
     *
     * @param menu 注文するメニューを表すMapオブジェクト。
     */
    private void order(Map<String, Object> menu) {
        //定食名と金額を取得。Mapの値部分がObject型なのでキャストが必要。
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");

        //インテントオブジェクトを生成。
        Intent intent = new Intent(MainActivity.this, MenuOrderActivity.class);
        //第2画面に送るデータを格納。
        intent.putExtra("menuName", menuName);
        //MenuThanksActivityでのデータ受け取りと合わせるために、数字で渡す
        intent.putExtra("menuPrice", menuPrice);
        //第2画面の起動。
        startActivity(intent);
    }

    /**
     * リストがタップされたときの処理が記述されたメンバクラス。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //データの削除
            _menuList.remove(position);
            //リスト再セット
            SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.activity_main_menu_row, FROM, TO);
            //アダプタの登録。
            _lvMenu.setAdapter(adapter);
        }
    }
}