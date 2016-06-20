package leon.designproject;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.victor.loading.rotate.RotateLoading;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 *
 */
public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageView imageView;
    TextView textView, textViewAnmelden;
    RotateLoading rotateLoading;
    RelativeLayout root;
    FrameLayout rootCreate;
    EditText edPassword, edBenutzername;
    boolean login = false;
    int originalWidth;


    public LoginFragment() {
        // Required empty public constructor
    }


    public void setOnTouchListener(OnFragmentInteractionListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = (RelativeLayout) view.findViewById(R.id.rootLayout);
        imageView = (ImageView) view.findViewById(R.id.image);
        final Drawable myIcon = getResources().getDrawable(R.drawable.pineapple);
        myIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageView.setImageDrawable(myIcon);
        rootCreate = (FrameLayout) view.findViewById(R.id.rootLayoutCreate);


        rotateLoading = (RotateLoading) view.findViewById(R.id.rotateloading);
        textViewAnmelden = (TextView) view.findViewById(R.id.textViewAnmelden);
        textView = (TextView) view.findViewById(R.id.textView);
        edBenutzername = (EditText) view.findViewById(R.id.editTextBenutzer);
        edPassword = (EditText) view.findViewById(R.id.editTextPassword);

        textView.postDelayed(new Runnable() {

            @Override
            public void run() {
                textView.invalidate();
                originalWidth = textView.getWidth();
            }
        }, 1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edBenutzername.getText().toString().trim().equals("Leon") && edPassword.getText().toString().trim().equals("Ardaturan99")) {
                    login = true;
                }

                Log.d("Buttn", " clicked");
                enterAniation(textView,view);
            }
        });

        textViewAnmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mListener.onFragmentInteraction();
//                enterReveal(rootCreate, textViewAnmelden, new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });


            }
        });
    }

    private void colorAnimationWrong(String fromColor, String toColor, final ImageView v, int delay) {
        final float[] from = new float[3],
                to = new float[3];

        Color.colorToHSV(Color.parseColor(fromColor), from);   // from white
        Color.colorToHSV(Color.parseColor(toColor), to);     // to red

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
        anim.setDuration(500);                              // for 300 ms
        anim.setStartDelay(delay);


        final float[] hsv = new float[3];                  // transition color
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Transition along each axis of HSV (hue, saturation, value)
                hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();

                Drawable myIcon = getResources().getDrawable(R.drawable.pineapple);
                myIcon.setColorFilter(Color.HSVToColor(hsv), PorterDuff.Mode.SRC_ATOP);
                v.setImageDrawable(myIcon);
            }
        });
        anim.start();

    }

    private void enterAniation(final TextView v,final View bindViews) {
        int height = v.getHeight();


        Resize resizeAnimation = new Resize(textView, height);
        resizeAnimation.setDuration(200);
        resizeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        resizeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rotateLoading.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (login) {
                            enterReveal(root, textView, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                        } else {
                            exitAnimation(v,bindViews);
                        }


                    }
                }, 1000);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(resizeAnimation);

    }

    public void exitAnimation(final TextView v,final View bindViews) {
        if (rotateLoading.isShown()) {
            rotateLoading.stop();
        }

        Resize resizeAnimation = new Resize(textView, originalWidth);
        resizeAnimation.setDuration(200);
        resizeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        resizeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                YoYo.with(Techniques.Swing)
                        .duration(1000)
                        .playOn(bindViews.findViewById(R.id.image));




                edPassword.setText("");
                edBenutzername.setText("");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        edBenutzername.requestFocus();
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(edBenutzername, InputMethodManager.SHOW_IMPLICIT);

                    }
                }, 1200);



            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setText("Sign in");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(resizeAnimation);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void enterReveal(View aim, final View from, Animator.AnimatorListener animatorListener) {


        int[] array = new int[2];


        from.getLocationOnScreen(array);

        int cx2 = (int) (array[0] +from.getPivotX());
        int cy2 = (int) (array[1] + from.getPivotY());


        // get the center for the clipping circle
        int cx = (aim.getLeft() + aim.getRight()) / 2;
        int cy = (aim.getTop() + aim.getBottom()) / 2;

        int diffHeight = cy2 - cy;
        int diffWidth = cx2 -cx;
        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx+diffWidth, cy + diffHeight);

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(aim, cx+diffWidth, cy + diffHeight, 0, finalRadius);
        // make the view visible and start the animation
        aim.setVisibility(View.VISIBLE);
        anim.setDuration(300);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(animatorListener);
        anim.start();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
