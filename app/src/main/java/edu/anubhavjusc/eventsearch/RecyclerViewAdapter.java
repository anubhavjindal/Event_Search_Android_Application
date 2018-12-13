package edu.anubhavjusc.eventsearch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URI;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<SearchItem> mData;
    private RequestQueue mQueue;

    public RecyclerViewAdapter(Context mContext, List<SearchItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.layout_search_item,viewGroup,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        mQueue = Volley.newRequestQueue(mContext);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i  = new Intent(mContext, EventDetails.class);


               try{
                   String url1 = "http://csci-hw8-222600.appspot.com/details/"+mData.get(viewHolder.getAdapterPosition()).getEventId();
                   JsonObjectRequest req1 = new JsonObjectRequest(Request.Method.GET, url1, null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   i.putExtra("event",response.toString());
//                                mContext.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                       }
                   });

                   URI uri2 = new URI(mData.get(viewHolder.getAdapterPosition()).getArtist().replace(" ", "%20"));
                   String url2 = "http://csci-hw8-222600.appspot.com/images/"+uri2;
                   JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.GET, url2, null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   i.putExtra("images",response.toString());
                                   i.putExtra("artistName",mData.get(viewHolder.getAdapterPosition()).getArtist());
//                                mContext.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                       }
                   });

                   JsonObjectRequest req10 = null;
                   JsonObjectRequest req11 = null;


                   //artist2 photos and info
                   if(mData.get(viewHolder.getAdapterPosition()).getArtist2()!="")
                   {
                       URI uri10 = new URI(mData.get(viewHolder.getAdapterPosition()).getArtist2().replace(" ", "%20"));
                       String url10 = "http://csci-hw8-222600.appspot.com/images/"+uri10;
                        req10 = new JsonObjectRequest(Request.Method.GET, url10, null,
                               new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       i.putExtra("images2",response.toString());
                                       i.putExtra("artistName2",mData.get(viewHolder.getAdapterPosition()).getArtist2());
//                                mContext.startActivity(i);
                                   }
                               }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               error.printStackTrace();
                           }
                       });

                       String url11 = "http://csci-hw8-222600.appspot.com/artist/"+uri10;
                        req11 = new JsonObjectRequest(Request.Method.GET, url11, null,
                               new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       i.putExtra("artist2",response.toString());
//                                mContext.startActivity(i);
                                   }
                               }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               error.printStackTrace();
                           }
                       });
                   }


                   String url3 = "http://csci-hw8-222600.appspot.com/artist/"+uri2;
                   JsonObjectRequest req3 = new JsonObjectRequest(Request.Method.GET, url3, null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   i.putExtra("artist",response.toString());
//                                mContext.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                       }
                   });

                   URI uri4 = new URI(mData.get(viewHolder.getAdapterPosition()).getLocation().split("\\(")[0].replace(" ", "%20"));
                   String url4 = "http://csci-hw8-222600.appspot.com/venue/"+uri4;
                   JsonObjectRequest req4 = new JsonObjectRequest(Request.Method.GET, url4, null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   i.putExtra("venue",response.toString());
                                   i.putExtra("venueName",mData.get(viewHolder.getAdapterPosition()).getLocation());
//                                mContext.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                       }
                   });


                   String url5 = "http://csci-hw8-222600.appspot.com/upcoming/"+uri4;
                   JsonObjectRequest req5 = new JsonObjectRequest(Request.Method.GET, url5, null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   i.putExtra("upcoming",response.toString());
                                   mContext.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                       }
                   });
                   //Started making changes...Undo Point!

                   mQueue.add(req1);
                   mQueue.add(req2);
                   if(mData.get(viewHolder.getAdapterPosition()).getArtist2()!="")
                   {
                       mQueue.add(req10);
                       mQueue.add(req11);
                   }

                       mQueue.add(req3);
                   mQueue.add(req4);
                   mQueue.add(req5);
               }
               catch (Exception e){
                   e.printStackTrace();
               }

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.title.setText(mData.get(i).getTitle());
        myViewHolder.location.setText(mData.get(i).getLocation());
        myViewHolder.date.setText(mData.get(i).getDatetime());
        myViewHolder.eventId.setText(mData.get(i).getEventId());
        myViewHolder.eventCategory.setText(mData.get(i).getCategory());
        myViewHolder.artist.setText(mData.get(i).getArtist());
        myViewHolder.artist2.setText(mData.get(i).getArtist2());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView location;
        TextView date;
        TextView eventCategory;
        TextView eventId;
        TextView artist;
        TextView artist2;
        ImageView thumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            location = itemView.findViewById(R.id.locationText);
            date = itemView.findViewById(R.id.dateText);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            view_container = itemView.findViewById(R.id.itemContainer);
            eventCategory = itemView.findViewById(R.id.eventCategory);
            eventId = itemView.findViewById(R.id.eventId);
            artist = itemView.findViewById(R.id.artist);
            artist2 = itemView.findViewById(R.id.artist2);
        }
    }
}
