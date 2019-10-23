package com.planet.myapptmdb.view

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.entities.Person
import com.planet.myapptmdb.utils.OnListInteractionListener
import kotlinx.android.synthetic.main.fragment_person_add_dialog.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.content.res.Resources
import com.google.android.material.bottomsheet.BottomSheetDialog

class PersonAddDialog : BottomSheetDialogFragment() {
    private var mListener: OnListInteractionListener<Person>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_person_add_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttom_add_person.setOnClickListener {
            if (isCoorect())
                mListener?.onListClickItem(Person(edt_person_name.text.toString(), edt_person_age.text.toString().toInt()))
            dismiss()
        }
    }

    fun isCoorect(): Boolean {
        if (edt_person_name.text.toString().isEmpty())
            return false
        if (edt_person_name.text.toString().isBlank())
            return false
        if (!edt_person_age.text.toString().isDigitsOnly())
            return false
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mListener = parent as OnListInteractionListener<Person>
        } else {
            mListener = context as OnListInteractionListener<Person>
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    companion object {
        // TODO: Customize parameters
        fun newInstance(): PersonAddDialog = PersonAddDialog()
    }
}
