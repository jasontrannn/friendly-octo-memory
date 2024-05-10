package com.example.final_reviewer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class RatingFrag : DialogFragment() {
    var ratingScore1 = ""
    var ratingComments1 = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myView : View = inflater.inflate(R.layout.frag_rating, container, false)

        var cancelBTN: Button = myView.findViewById(R.id.cancel_frag_button)
        var saveBtn: Button= myView.findViewById(R.id.save_frag_button)
        var ratebar: RatingBar = myView.findViewById(R.id.frag_ratingBar)
        var commentsET: EditText = myView.findViewById(R.id.comments_frag_editText)

        ratebar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingScore1 = rating.toString()
            Log.i("MY_TAG", "RatingFragment - RateBar - value == $rating")
        }

        cancelBTN.setOnClickListener{
            Log.i("MY_TAG", "RatingFragment - setOnClickListener - cancel button clicked")
            dismiss()
        }

        saveBtn.setOnClickListener {
            var ratingComments = commentsET.text.toString()
            Log.i("MY_TAG", "RatingFragment - saveBtn - commentsET == $ratingComments")

            Toast.makeText(context, "Rating Saved", Toast.LENGTH_LONG).show()
            //Close the fragment when done
            dismiss()
        }

        //return super.onCreateView(inflater, container, savedInstanceState)
        return myView
    }

    public fun getRatingScore() : String {
        return ratingScore1
    }

    public fun getRatingComments() : String {
        return ratingComments1
    }
}