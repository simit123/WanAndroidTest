package wanandroid.com.wanandroidtest.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.app.MyApplication;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.ui.MainActivity;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.one_animation)
    LottieAnimationView oneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView twoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView threeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView fourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView fiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView sixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView sevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView eightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView nineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView tenAnimation;

    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        //当app是热启动时（activity全部被杀死，application还在）点击 直接跳转 不展示动画
        if (!MyApplication.isFirstRun) {
            jumpToHome();
            return;
        }
        MyApplication.isFirstRun = false;
//        jumpToHome();
    }

    @Override
    protected void initData() {
        startAnims();
        delayJump();
    }

    @Override
    protected void onDestroy() {
        clearAnims();
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void startAnims(){
       starAnim(oneAnimation, "W.json");
       starAnim(twoAnimation, "A.json");
       starAnim(threeAnimation, "N.json");
       starAnim(fourAnimation, "A.json");
       starAnim(fiveAnimation, "N.json");
       starAnim(sixAnimation, "D.json");
       starAnim(sevenAnimation, "R.json");
       starAnim(eightAnimation, "I.json");
       starAnim(nineAnimation, "O.json");
       starAnim(tenAnimation, "D.json");
   }

   private void clearAnims(){
       clearAnim(oneAnimation);
       clearAnim(twoAnimation);
       clearAnim(threeAnimation);
       clearAnim(fourAnimation);
       clearAnim(fiveAnimation);
       clearAnim(sixAnimation);
       clearAnim(sevenAnimation);
       clearAnim(eightAnimation);
       clearAnim(nineAnimation);
       clearAnim(tenAnimation);
   }


    private void starAnim(LottieAnimationView anim,String animName){
        anim.setAnimation(animName);
        anim.playAnimation();
    }

    private void clearAnim(LottieAnimationView anim){
        if (anim != null) {
            anim.cancelAnimation();
        }
    }

    private void jumpToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void delayJump(){
        long time = 2000;
        mDisposable = Observable.timer(time, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> jumpToHome());
    }
}
