package android.wings.websarva.menulistset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MenuSet extends AppCompatActivity {

    /**
     * リストビューを表すフィールド。
     */
    private ListView _lvMenuList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_set);

        //アクションバーを取得。
        ActionBar actionBar = getSupportActionBar();
        //アクションバーの［戻る］メニューを有効に設定。
        actionBar.setDisplayHomeAsUpEnabled(true);

        //画面部品ListViewを取得し、フィールドに格納。
        _lvMenuList = findViewById(R.id.lvMenuList);
        //SimpleAdapterで使用する定食メニューListオブジェクトをprivateメソッドを利用して用意し、フィールドに格納。
        _menuList = createTeishokuList();
        //SimpleAdapterを生成。
        SimpleAdapter adapter = new SimpleAdapter(MenuSet.this, _menuList, R.layout.activity_main_menu_row, FROM, TO);
        //アダプタの登録。
        _lvMenuList.setAdapter(adapter);
        //リストタップのリスナクラス登録。
        _lvMenuList.setOnItemClickListener(new ListItemSetClickListener());

        //コンテキストメニューをリストビューに登録。
        registerForContextMenu(_lvMenuList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューインフレーターの取得。
        MenuInflater inflater = getMenuInflater();
        //オプションメニュー用xmlファイルをインフレイト。
        inflater.inflate(R.menu.main_menu_options_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //戻り値用の変数を初期値trueで用意。
        boolean returnVal = true;
        //選択されたメニューのIDを取得。
        int itemId = item.getItemId();
        //選択されたメニューが［戻る］の場合、アクティビティを終了。
        if(itemId == android.R.id.home) {
            finish();
        }
        //IDのR値による処理の分岐。
        switch(itemId) {
            //定食メニューが選択された場合の処理。
            case R.id.menuListOptionTeishoku:
                //定食メニューリストデータの生成。
                _menuList = createTeishokuList();
                break;
            //カレーメニューが選択された場合の処理。
            case R.id.menuListOptionCurry:
                //カレーメニューリストデータの生成。
                _menuList = createCurryList();
                break;
            //それ以外…
            default:
                //親クラスの同名メソッドを呼び出し、その戻り値をreturnValとする。
                returnVal = super.onOptionsItemSelected(item);
                break;
        }
        //SimpleAdapterを選択されたメニューデータで生成。
        SimpleAdapter adapter = new SimpleAdapter(MenuSet.this, _menuList, R.layout.activity_main_menu_row, FROM, TO);
        //アダプタの登録。
        _lvMenuList.setAdapter(adapter);
        return returnVal;
    }

    /**
     * リストビューに表示させる定食リストデータを生成するメソッド。
     *
     * @return 生成された定食リストデータ。
     */
    private List<Map<String, Object>> createTeishokuList() {
        //定食メニューリスト用のListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        //以下データ登録の繰り返し。
        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", 850);
        menu.put("desc", "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", 750);
        menu.put("desc", "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", 900);
        menu.put("desc", "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", 900);
        menu.put("desc", "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", 850);
        menu.put("desc", "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "回鍋肉定食");
        menu.put("price", 750);
        menu.put("desc", "ピリ辛回鍋肉にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "麻婆豆腐定食");
        menu.put("price", 800);
        menu.put("desc", "本格四川風麻婆豆腐にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "青椒肉絲定食");
        menu.put("price", 900);
        menu.put("desc", "ピーマンの香り豊かな青椒肉絲にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", 850);
        menu.put("desc", "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼肉定食");
        menu.put("price", 950);
        menu.put("desc", "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }

    /**
     * リストビューに表示させるカレーリストデータを生成するメソッド。
     *
     * @return 生成されたカレーリストデータ。
     */
    private List<Map<String, Object>> createCurryList() {
        //カレーメニューリスト用のListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        //「ビーフカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスをきかせた国産ビーフ100%のカレーです。");
        menuList.add(menu);

        //「ポークカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスをきかせた国産ポーク100%のカレーです。");
        menuList.add(menu);

        //以下データ登録の繰り返し。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグカレー");
        menu.put("price", 620);
        menu.put("desc", "特選スパイスをきかせたカレーに手ごねハンバーグをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チーズカレー");
        menu.put("price", 560);
        menu.put("desc", "特選スパイスをきかせたカレーにとろけるチーズをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "カツカレー");
        menu.put("price", 760);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ロースカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ビーフカツカレー");
        menu.put("price", 880);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ビーフカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "からあげカレー");
        menu.put("price", 540);
        menu.put("desc", "特選スパイスをきかせたカレーに若鳥のから揚げをトッピングです。");
        menuList.add(menu);

        return menuList;
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

        // 遷移先のactivityを指定してintentを作成
        Intent intent = new Intent();
        //第2画面に送るデータを格納。
        intent.putExtra("menuName", menuName);
        //MenuThanksActivityでのデータ受け取りと合わせるために、数字で渡す
        intent.putExtra("menuPrice", menuPrice);
        //第2画面の起動
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * リストがタップされたときの処理が記述されたメンバクラス。
     */
    private class ListItemSetClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //タップされた行のデータを取得。
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            //注文処理。
            order(item);
        }
    }
}
