package carpinteroseverino.com.garbagecollector.Fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import carpinteroseverino.com.garbagecollector.R;
import carpinteroseverino.com.garbagecollector.Recycled;

@SuppressLint("ValidFragment")
public class FragmentActual extends Fragment {

    private static final String TAG = "FragmentActual";

    private View view;

    private Button buttonAddBottles;
    private Button buttonAddTetrabriks;
    private Button buttonAddGlass;
    private Button buttonAddPaperboard;
    private Button buttonAddCans;

    private Button buttonSubBottles;
    private Button buttonSubTetrabriks;
    private Button buttonSubGlass;
    private Button buttonSubPaperboard;
    private Button buttonSubCans;

    private TextView textViewCounterBottles;
    private TextView textViewCounterTetrabriks;
    private TextView textViewCounterGlass;
    private TextView textViewCounterPaperboard;
    private TextView textViewCounterCans;

    private FloatingActionButton buttonSubmission;
    private Recycled actualRecycled;
    private FragmentsListener listener;

    public FragmentActual() {
        Log.d(TAG, "CONSTRUCTOR");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_actual, container, false);

        buttonAddBottles = view.findViewById(R.id.bottles_button_add);
        buttonAddTetrabriks = view.findViewById(R.id.tetrabriks_button_add);
        buttonAddGlass = view.findViewById(R.id.glass_button_add);
        buttonAddPaperboard = view.findViewById(R.id.paperboard_button_add);
        buttonAddCans = view.findViewById(R.id.cans_button_add);

        buttonSubBottles = view.findViewById(R.id.bottles_button_sub);
        buttonSubTetrabriks = view.findViewById(R.id.tetrabriks_button_sub);
        buttonSubGlass = view.findViewById(R.id.glass_button_sub);
        buttonSubPaperboard = view.findViewById(R.id.paperboard_button_sub);
        buttonSubCans = view.findViewById(R.id.cans_button_sub);

        textViewCounterBottles = view.findViewById(R.id.bottles_text_view_counter);
        textViewCounterTetrabriks = view.findViewById(R.id.tetrabriks_text_view_counter);
        textViewCounterGlass = view.findViewById(R.id.glass_text_view_counter);
        textViewCounterPaperboard = view.findViewById(R.id.paperboard_text_view_counter);
        textViewCounterCans = view.findViewById(R.id.cans_text_view_counter);

        buttonSubmission = view.findViewById(R.id.submission_button);

        buttonAddBottles.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setBottlesPP();
                textViewCounterBottles.setText(Integer.toString((actualRecycled.getBottles())));
            }
        }));

        buttonSubBottles.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setBottlesSS();
                textViewCounterBottles.setText(Integer.toString(actualRecycled.getBottles()));
            }
        }));


        buttonAddTetrabriks.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setTetrabriksPP();
                textViewCounterTetrabriks.setText(Integer.toString(actualRecycled.getTetrabriks()));
            }
        }));

        buttonSubTetrabriks.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setTetrabriksSS();
                textViewCounterTetrabriks.setText(Integer.toString(actualRecycled.getTetrabriks()));
            }
        }));


        buttonAddGlass.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setGlassPP();
                textViewCounterGlass.setText(Integer.toString(actualRecycled.getGlass()));
            }
        }));

        buttonSubGlass.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setGlassSS();
                textViewCounterGlass.setText(Integer.toString(actualRecycled.getGlass()));
            }
        }));


        buttonAddPaperboard.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setPaperboardPP();
                textViewCounterPaperboard.setText(Integer.toString(actualRecycled.getPaperboard()));
            }
        }));

        buttonSubPaperboard.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setPaperboardSS();
                textViewCounterPaperboard.setText(Integer.toString(actualRecycled.getPaperboard()));
            }
        }));


        buttonAddCans.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setCansPP();
                textViewCounterCans.setText(Integer.toString(actualRecycled.getCans()));
            }
        }));

        buttonSubCans.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualRecycled.setCansSS();
                textViewCounterCans.setText(Integer.toString(actualRecycled.getCans()));
            }
        }));

        setCustomNumberDialogs();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateTextViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener = (FragmentsListener) getActivity();
        listener.setFragmentActual(this);
        buttonSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSubmissionButton();
                actualRecycled.reset();
                updateTextViews();
            }
        });
    }

    public void updateTextViews() {
        if (view != null && actualRecycled != null) {
            textViewCounterBottles.setText(Integer.toString(actualRecycled.getBottles()));
            textViewCounterTetrabriks.setText(Integer.toString(actualRecycled.getTetrabriks()));
            textViewCounterGlass.setText(Integer.toString(actualRecycled.getGlass()));
            textViewCounterPaperboard.setText(Integer.toString(actualRecycled.getPaperboard()));
            textViewCounterCans.setText(Integer.toString(actualRecycled.getCans()));
        } else
            Log.d(TAG, "updateTextViews: NULL");
    }

    public void setActualRecycled(Recycled actualRecycled) {
        this.actualRecycled = actualRecycled;
        updateTextViews();
    }

    private void setCustomNumberDialogs() {
        textViewCounterBottles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_custom_number, null);
                final EditText editText = dialogView.findViewById(R.id.dialog_custom_number_edit_text);
                builder.setView(dialogView)
                        .setTitle("Ingrese la cantidad de Botellas")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txt = editText.getText().toString();
                                if (!txt.equals("")) {
                                    int number = Integer.parseInt(txt);
                                    actualRecycled.setBottles(number);
                                    updateTextViews();
                                }
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        textViewCounterCans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_custom_number, null);
                final EditText editText = dialogView.findViewById(R.id.dialog_custom_number_edit_text);
                builder.setView(dialogView)
                        .setTitle("Ingrese la cantidad de Latas")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txt = editText.getText().toString();
                                if (!txt.equals("")) {
                                    int number = Integer.parseInt(txt);
                                    actualRecycled.setCans(number);
                                    updateTextViews();
                                }
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        textViewCounterGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_custom_number, null);
                final EditText editText = dialogView.findViewById(R.id.dialog_custom_number_edit_text);
                builder.setView(dialogView)
                        .setTitle("Ingrese la cantidad de Vidrios")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txt = editText.getText().toString();
                                if (!txt.equals("")) {
                                    int number = Integer.parseInt(txt);
                                    actualRecycled.setGlass(number);
                                    updateTextViews();
                                }
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        textViewCounterPaperboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_custom_number, null);
                final EditText editText = dialogView.findViewById(R.id.dialog_custom_number_edit_text);
                builder.setView(dialogView)
                        .setTitle("Ingrese la cantidad de Papeles")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txt = editText.getText().toString();
                                if (!txt.equals("")) {
                                    int number = Integer.parseInt(txt);
                                    actualRecycled.setPaperboard(number);
                                    updateTextViews();
                                }
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        textViewCounterTetrabriks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_custom_number, null);
                final EditText editText = dialogView.findViewById(R.id.dialog_custom_number_edit_text);
                builder.setView(dialogView)
                        .setTitle("Ingrese la cantidad de Tetrabriks")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txt = editText.getText().toString();
                                if (!txt.equals("")) {
                                    int number = Integer.parseInt(txt);
                                    actualRecycled.setTetrabriks(number);
                                    updateTextViews();
                                }
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null)
            outState.putParcelable("recycled", actualRecycled);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            actualRecycled = savedInstanceState.getParcelable("recycled");
            updateTextViews();
        }
    }
}
