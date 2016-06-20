package leon.designproject;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.victor.loading.rotate.RotateLoading;

public class Main2Activity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    ImageView imageView;
    TextView textView;
    RotateLoading rotateLoading;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        root = (RelativeLayout) findViewById(R.id.rootLayout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layout);
        imageView = (ImageView) findViewById(R.id.image);
        Drawable myIcon = getResources().getDrawable(R.drawable.pineapple);
        myIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageView.setImageDrawable(myIcon);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Buttn", " clicked");

                int height = v.getHeight();


                Resize resizeAnimation = new Resize(textView, height);
                resizeAnimation.setDuration(200);
                resizeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                resizeAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rotateLoading.start();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                enterReveal(root,textView);
                            }
                        }, 3000);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                textView.startAnimation(resizeAnimation);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void enterReveal(View aim,View from) {
        int[] array = new int[2];
        from.getLocationOnScreen(array);
        int cy2 = array[1]+(from.getTop() + from.getBottom()) / 2;;
        // get the center for the clipping circle
        int cx = (aim.getLeft() + aim.getRight()) / 2;
        int cy = (aim.getTop() + aim.getBottom()) / 2;

        int diff = cy2-cy;
        Log.d("Math",cy+" cy, "+cy2+" cy2, "+diff);
        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy+diff);

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(aim, cx, cy+diff, 0, finalRadius);
        // make the view visible and start the animation
        aim.setVisibility(View.VISIBLE);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();


    }


}
