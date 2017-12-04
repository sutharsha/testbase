package su.com.busrouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doSomeWork();

        Button dummyButton = (Button) findViewById(R.id.goToDummyButton);
        dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDummyScreen();
            }
        });

    }

    private Observable<String> getObservable(){
        return Observable.just("Cricket", "Football");
    }

    private Observer<String> getObserver(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i(TAG, value);
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
                System.out.println("onComplete");
            }
        };
    }

    private void doSomeWork(){

            getObservable()
                    //Run on background thread
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
    }

    private void goToDummyScreen(){
        Intent intent = new Intent(this, DummyActivity.class);
        startActivity(intent);

    }

}
