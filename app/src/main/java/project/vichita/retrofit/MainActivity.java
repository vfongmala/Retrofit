package project.vichita.retrofit;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new HttpAsyncTask().execute();

        // call with callback
        /*RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.dribbble.com").build();
        SimpleRetrofit retrofit = restAdapter.create(SimpleRetrofit.class);
        retrofit.getShotByIdWithCallback(21603, new Callback<Shot>() {
            @Override
            public void success(Shot shot, Response response) {
                Toast.makeText(getApplicationContext(),"Name : "+shot.getTitle() + " URL: "+shot.getUrl(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });*/

        // call by popular and show on gridview

        gridView = (GridView) findViewById(R.id.gridview);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.dribbble.com").build();
        SimpleRetrofit retrofit = restAdapter.create(SimpleRetrofit.class);
        retrofit.getShotsByPopular(new Callback<ShotList>() {
            @Override
            public void success(ShotList shotList, Response response) {
                gridView.setAdapter(new GridAdapter(MainActivity.this,shotList));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class HttpAsyncTask extends AsyncTask<Void,Void,Shot>{
        @Override
        protected Shot doInBackground(Void... params) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.dribbble.com").build();

            SimpleRetrofit retrofit = restAdapter.create(SimpleRetrofit.class);
            Shot shot = retrofit.getShotById(30000);

            return shot;
        }

        @Override
        protected void onPostExecute(Shot shot) {
            Toast.makeText(getApplicationContext(),"Name : "+shot.getTitle() + " URL: "+shot.getUrl(),Toast.LENGTH_SHORT).show();
            super.onPostExecute(shot);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
