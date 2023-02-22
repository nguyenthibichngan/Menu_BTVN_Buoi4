package com.example.menu_btvn_buoi4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, NotificationActivity.class);

        ImageButton mButton = findViewById(R.id.button_popup);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, mButton);
                popup.getMenuInflater().inflate(
                        R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.popup1:
                                        Toast.makeText(MainActivity.this, "Popup1", Toast.LENGTH_SHORT).show();
                                        showActivity();
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        }
                );
                popup.show();
            }
        });

        TextView view = findViewById(R.id.article);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) return false;
                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }

            public ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.contextual_menu, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.context_edit:
                            Toast.makeText(MainActivity.this, "Text has been edited", Toast.LENGTH_SHORT).show();
                            showActivity();
                            mode.finish();
                            return true;
                        case R.id.context_share:
                            Toast.makeText(MainActivity.this, "Text has been shared", Toast.LENGTH_SHORT).show();
                            showActivity();
                            mode.finish();
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    mActionMode = null;
                }
            };
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Item1", Toast.LENGTH_SHORT).show();
                showActivity();
                return true;
            case R.id.item2_1:
                Toast.makeText(MainActivity.this, "Item2.1", Toast.LENGTH_SHORT).show();
                showActivity();
                return true;
            case R.id.item2_2:
                Toast.makeText(MainActivity.this, "Item2.2", Toast.LENGTH_SHORT).show();
                showActivity();
                return true;
            case R.id.item3:
                Toast.makeText(MainActivity.this, "Item3", Toast.LENGTH_SHORT).show();
                showActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showActivity(){
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
}