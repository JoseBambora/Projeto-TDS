package com.ruirua.sampleguideapp.ui.pins;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.RelPin;
import com.ruirua.sampleguideapp.repositories.MediaRepository;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;

import java.util.List;

public class PinFragment extends Fragment {

    private final int pinId;
    private Activity activity;
    private VideoView videoView;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private Pin pin;


    public PinFragment( int pinId) {
        this.pinId = pinId;
    }

    private void setImage(Media m, View v) {
        videoView.setVisibility(View.GONE);
        UIFuns.showImage(m.getMedia_file(),v.findViewById(R.id.imagePin),v.findViewById(R.id.playImagem),videoView);
    }
    private void setAudio(Media m, View v) {
        UIFuns.playAudio(m.getMedia_file(),v.findViewById(R.id.playAudio),getActivity(), mediaPlayer);
    }

    private void setVideo(Media m, View v) {
        UIFuns.playVideo(m.getMedia_file(),videoView, v.findViewById(R.id.playVideo), v.findViewById(R.id.imagePin), getActivity());
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
        videoView = v.findViewById(R.id.videoPin);

        // Button buttonImage = v.findViewById(R.id.playImagem);
        // Button buttonAudio = v.findViewById(R.id.playAudio);
        // Button buttonVideo = v.findViewById(R.id.playVideo);
        // buttonImage.setOnClickListener(view -> Toast.makeText(getActivity(),"A carregar imagem.", Toast.LENGTH_SHORT).show());
        // buttonVideo.setOnClickListener(view -> Toast.makeText(getActivity(),"A carregar vídeo.", Toast.LENGTH_SHORT).show());
        // buttonAudio.setOnClickListener(view -> Toast.makeText(getActivity(),"A carregar áudio.", Toast.LENGTH_SHORT).show());
        PinsViewModel pvm = new ViewModelProvider(this).get(PinsViewModel.class);
        pvm.getPin(pinId).observe(getViewLifecycleOwner(), p -> this.fillInfo(p,v));
        UIFuns.configureTheme(activity);
        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (videoView != null && videoView.isPlaying())
            videoView.pause();
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
}