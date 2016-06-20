package leon.designproject;


import android.animation.Animator;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateAccountFragment extends Fragment {

    EditText edEmai,edGrade,edUser,edPassword;
    TextView textViewSignUp;
    ImageView imageViewBack,imageViewCamera,imageViewUser,imageViewLock,imageViewGrade,imageViewEmail;
    RelativeLayout line1,line2,line3,line4;
    int[] views = new int[]{R.id.imageBack,R.id.imageCamera,R.id.imageUser,R.id.editUser,R.id.line1,R.id.imageLock,
            R.id.editPassword,R.id.line2,R.id.imageGrade,R.id.editGrade,R.id.line3,R.id.imageEmail,R.id.editEmail,
            R.id.line4,R.id.textViewSignUp};
    View[]views2;

    private OnFragmentInteractionListener mListener;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edEmai = (EditText) view.findViewById(R.id.editEmail);
        edGrade = (EditText) view.findViewById(R.id.editGrade);
        edPassword = (EditText) view.findViewById(R.id.editPassword);
        edUser = (EditText) view.findViewById(R.id.editUser);

        imageViewBack = (ImageView) view.findViewById(R.id.imageBack);
        imageViewCamera = (ImageView) view.findViewById(R.id.imageCamera);
        imageViewEmail = (ImageView) view.findViewById(R.id.imageEmail);
        imageViewGrade = (ImageView) view.findViewById(R.id.imageGrade);
        imageViewLock = (ImageView) view.findViewById(R.id.imageLock);
        imageViewUser = (ImageView) view.findViewById(R.id.imageUser);

        textViewSignUp = (TextView) view.findViewById(R.id.textViewSignUp);

        line1 = (RelativeLayout) view.findViewById(R.id.line1);
        line2 = (RelativeLayout) view.findViewById(R.id.line2);
        line3 = (RelativeLayout) view.findViewById(R.id.line3);
        line4 = (RelativeLayout) view.findViewById(R.id.line4);

        views2 = new View[]{imageViewBack,imageViewCamera,imageViewUser,edUser,line1,imageViewLock,
                edPassword,line2,imageViewGrade,edGrade,line3,imageViewEmail,edEmai,
                line4,textViewSignUp,};


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionCreate();
    }
}
