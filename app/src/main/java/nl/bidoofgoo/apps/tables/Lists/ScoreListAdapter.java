package nl.bidoofgoo.apps.tables.Lists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nl.bidoofgoo.apps.tables.Models.ScoreModel;
import nl.bidoofgoo.apps.tables.R;

public class ScoreListAdapter extends ArrayAdapter<ScoreModel>{

    private List<ScoreModel> dataSet;
    private Context context;

    public ScoreListAdapter(Context cont, int resource, List<ScoreModel> data){
        super(cont, resource, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null){
            vh = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.score_listitem, parent, false);
            vh.score = (TextView) convertView.findViewById(R.id.score);
            vh.username = (TextView) convertView.findViewById(R.id.username);
            convertView.setTag(vh);
        } else{
            vh = (ViewHolder) convertView.getTag();
        }
        ScoreModel sm = getItem(position);
        vh.username.setText((CharSequence) sm.getNaam());
        vh.score.setText((CharSequence) String.valueOf(sm.getScore()));
        return convertView;
    }

    private static class ViewHolder{
        TextView score;
        TextView username;
    }
}
