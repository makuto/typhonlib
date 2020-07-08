package org.zorgblub.rikai.download.settings.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.zorgblub.rikai.R;
/**
 * Created by Benjamin on 26/03/2016.
 */
public class DictionaryConfigActivity extends AppCompatActivity {

    private Fragment dictionaryConfigFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dictionary_list_activity);
        dictionaryConfigFragment = getSupportFragmentManager().findFragmentById(R.id.dictionary_list_fragment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.dictionary_settings);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dictionaryConfigFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
