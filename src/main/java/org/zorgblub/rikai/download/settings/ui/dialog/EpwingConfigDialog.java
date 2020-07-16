package org.zorgblub.rikai.download.settings.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rikai.dictionary.Dictionary;
import org.zorgblub.rikai.R;
import org.zorgblub.rikai.download.settings.DictionarySettings;
import org.zorgblub.rikai.download.settings.EpwingSettings;
import org.zorgblub.rikai.download.settings.ui.OnFileChosenListener;
import org.zorgblub.rikai.download.settings.ui.TyphonFileChooser;

import java.io.File;

/**
 * Created by Benjamin on 03/04/2016.
 */
public class EpwingConfigDialog extends DictionaryConfigDialog<EpwingSettings> implements OnFileChosenListener {

    protected EditText pathEditText;

    private IntentCallBack intentCallBack;

    public EpwingConfigDialog(Context context, EpwingSettings settings) {
        super(context, settings);
    }

    @Override
    protected int getViewId() {
        return R.layout.dictionary_epwing_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pathEditText = (EditText) findViewById(R.id.epwing_folder);
        pathEditText.setText(settings.getBasePath());
        Button browseButton = (Button) findViewById(R.id.browse_epwing_button);

        browseButton.setOnClickListener(v -> {
            TyphonFileChooser fileChooser = DictionarySettings.getFileChooser();
            if(fileChooser == null)
                return;

            fileChooser.startFileChooser(this, this.getOwnerActivity());
        });
    }

    @Override
    public void onFileChosen(File file) {
        String path = "";
        if(file != null){
            path = file.getPath();
        }
        pathEditText.setText(path);
    }

    @Override
    protected boolean saveSettings() {
        super.saveSettings();
        settings.setBasePath(pathEditText.getText().toString());
        Context context = getContext();
        try {
            Dictionary dictionary = settings.newInstance();
            dictionary.load();
        } catch (Exception e){
            Toast.makeText(context, context.getString(R.string.dictionary_epwing_loading_error, e.getMessage()), Toast.LENGTH_LONG).show();
            return false;
        }

        // TODO integrate the real name of the dictionary in the settings and message
        Toast.makeText(context, R.string.dictionary_epwing_load_success, Toast.LENGTH_LONG).show();

        return true;
    }

    public interface IntentCallBack {
        void onResult(int resultCode, Intent data);
    }
}
