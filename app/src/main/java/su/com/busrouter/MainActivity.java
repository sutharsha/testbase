package su.com.busrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.an
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }

    private void doSomeWork(){

            getObservable()
                    //Run on background thread
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.main)
    }

}
