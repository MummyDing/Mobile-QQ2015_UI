package com.demos.tencent_qq_ui.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demos.tencent_qq_ui.Aty.R;

/**
 * Created by mummyding on 15-8-14.
 */
public class QzoneFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_qzone_fragment,null);
        TextView title = (TextView) view.findViewById(R.id.title_tv);
        title.setText("动态");

        Button button = (Button) view.findViewById(R.id.header_imgbtn);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setText("更多");

        return view;
    }
}
