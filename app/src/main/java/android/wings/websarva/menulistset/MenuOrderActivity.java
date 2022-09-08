package android.wings.websarva.menulistset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 『Androidアプリ開発の教科書』
 * 第8章
 * メニューサンプル
 *
 * 注文完了画面のアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MenuOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_setlist);

        //インテントオブジェクトを取得。
        Intent intent = getIntent();
        //リスト画面から渡されたデータを取得。
        String menuName = "合計";
        Integer menuGoukei = intent.getIntExtra("menuPrice",0);

        TextView tvCommit = findViewById(R.id.txCommit);
        TextView txComment = findViewById(R.id.txComment);
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);
        if(menuGoukei==0){
            menuName = "";
            tvCommit.setText("注文未確認");
            txComment.setText("注文メニューが設定されていません。\n戻ってメニューを再設定して下さい。");
        }else{
            //TextViewに定食名と金額を表示。
            tvMenuName.setText(menuName);
            tvMenuPrice.setText(Integer.toString(menuGoukei)  + "円");
        }

        //アクションバーを取得。
        ActionBar actionBar = getSupportActionBar();
        //アクションバーの［戻る］メニューを有効に設定。
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        //それ以外…
        else {
            //親クラスの同名メソッドを呼び出し、その戻り値をreturnValとする。
            returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }
}
