package com.ruirua.sampleguideapp.ui.pins;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.RelPin;
import com.ruirua.sampleguideapp.ui.initial.MainActivity;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class PinFragment extends Fragment {

    private final int pinId;
    private Activity activity;

    private Pin pin;


    public PinFragment( int pinId) {
        this.pinId = pinId;
    }

    private void setImage(Media m, View v) {
        Button buttonImage = v.findViewById(R.id.playImagem);
        ImageView iv = v.findViewById(R.id.imagePin);
        VideoView videoView = v.findViewById(R.id.videoPin);
        videoView.setVisibility(View.GONE);
        iv.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(m.getMedia_file().replace("http:", "https:"))
                .into(iv);
        buttonImage.setOnClickListener(view-> {
            videoView.setVisibility(View.GONE);
            iv.setVisibility(View.VISIBLE);
        });
    }
    private void setAudio(Media m, View v) {
        Button buttonAudio = v.findViewById(R.id.playAudio);
        buttonAudio.setOnClickListener(view -> {
            try {
                String url = m.getMedia_file();
                Log.d("DebugApp","A configurar audio");
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build());
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(mp -> {
                    Log.d("DebugApp","A começar play do audio");
                    mediaPlayer.start();
                });
                Log.d("DebugApp","Audio configurado");
            } catch (IOException e) {
                Log.d("DebugApp","Erro a começar play do audio");
                Toast.makeText(getActivity(), "Erro ao começar audio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVideo(Media m, View v) {
        Button buttonVideo = v.findViewById(R.id.playVideo);
        VideoView videoView = v.findViewById(R.id.videoPin);
        ImageView iv = v.findViewById(R.id.imagePin);
        buttonVideo.setOnClickListener(view -> {
            videoView.setVisibility(View.VISIBLE);
            iv.setVisibility(View.GONE);
            String url = m.getMedia_file();

            // Set the video URI and start playback
            videoView.setVideoPath(url);
            videoView.start();

            // Optional: Add media controller for playback controls
            MediaController mediaController = new MediaController(getActivity());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // Optional: Handle errors during playback
            videoView.setOnErrorListener((mp, what, extra) -> {
                Toast.makeText(getActivity(), "Error playing video", Toast.LENGTH_SHORT).show();
                return false;
            });
        });
    }
    private void setMedia(View v) {
        List<Media> mediaList = pin.getMediaList();
        boolean hasVideo = false, hasAudio = false, hasImage = false;
        for(Media m : mediaList) {
            hasAudio = hasAudio || m.isAudio();
            hasVideo = hasVideo || m.isVideo();
            hasImage = hasImage || m.isImage();
            if(m.isImage())
                setImage(m,v);
            else if(m.isAudio())
                setAudio(m,v);
            else if(m.isVideo())
                setVideo(m,v);
        }
        VideoView videoView = v.findViewById(R.id.videoPin);
        ImageView iv = v.findViewById(R.id.imagePin);
        Button buttonImage = v.findViewById(R.id.playImagem);
        Button buttonAudio = v.findViewById(R.id.playAudio);
        Button buttonVideo = v.findViewById(R.id.playVideo);
        if(!hasImage) {
            iv.setVisibility(View.GONE);
            buttonImage.setOnClickListener(view -> Toast.makeText(getActivity(),"Não há imagem disponível para este ponto de referência", Toast.LENGTH_SHORT).show());
        }
        else
            videoView.setVisibility(View.GONE);
        if(!hasVideo) {
            buttonVideo.setOnClickListener(view -> Toast.makeText(getActivity(),"Não há vídeo disponível para este ponto de referência", Toast.LENGTH_SHORT).show());
            videoView.setVisibility(View.GONE);
        }
        if(!hasAudio)
            buttonAudio.setOnClickListener(view -> Toast.makeText(getActivity(),"Não há áudio disponível para este ponto de referência", Toast.LENGTH_SHORT).show());

    }
    private void setGeneralContent(View v) {
        TextView nametv = v.findViewById(R.id.pinName);
        TextView coordstv = v.findViewById(R.id.pinLocation);
        TextView descttv = v.findViewById(R.id.pinDesc);
        TextView nameTvTop = v.findViewById(R.id.namePin);

        nametv.setText(pin.getPin_name());
        coordstv.setText("(" +pin.getPin_lat() + "," + pin.getPin_lng()+ "," + pin.getPin_alt() + ")");
        descttv.setText(pin.getPin_desc());
        nameTvTop.setText(pin.getPin_name());
    }

    private TextView createTextView(String text) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.Texto1, typedValue, true);
        TextView res = new TextView(this.getActivity());
        res.setText(text);
        res.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        res.setGravity(Gravity.CENTER_HORIZONTAL);
        res.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        res.setTextColor(typedValue.data);
        res.setTypeface(null, Typeface.BOLD);
        return res;
    }
    private void setTable(View v) {
        TableLayout relpinsTable = v.findViewById(R.id.relpinsTable);
        List<RelPin> relPins = pin.getRelPinList();
        if(relPins.isEmpty()) {
            relpinsTable.setVisibility(View.GONE);
            TextView view = v.findViewById(R.id.extraTV);
            view.setVisibility(View.GONE);
        }
        else {
            for(RelPin relPin : relPins) {
                TableRow tableRow = new TableRow(this.getActivity());

                TextView valueTextView = createTextView(relPin.getValue());
                TextView attributeTextView = createTextView(relPin.getAttribute());

                tableRow.addView(valueTextView);
                tableRow.addView(attributeTextView);

                relpinsTable.addView(tableRow);
            }
            TableRow tableRow = new TableRow(this.getActivity());
            tableRow.addView(createTextView(""));
            tableRow.addView(createTextView(""));
            relpinsTable.addView(tableRow);
        }
    }
    @SuppressLint("SetTextI18n")
    private void fillInfo(Pin pin, View v) {
        this.pin = pin;
        setMedia(v);
        setGeneralContent(v);
        setTable(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container, false);
        activity = getActivity();
        PinsViewModel pvm = new ViewModelProvider(this).get(PinsViewModel.class);
        pvm.getPin(pinId).observe(getViewLifecycleOwner(), p -> this.fillInfo(p,v));
        UIFuns.configureTheme(activity);
        return v;
    }
}