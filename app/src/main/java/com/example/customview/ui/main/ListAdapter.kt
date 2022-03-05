package com.example.customview.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.R
import com.example.customview.data.model.Mood
import com.example.customview.ui.custom.CustomView


class ListAdapter : PagingDataAdapter<Mood, ListAdapter.ViewHolder>(diffCallback) {


    //RecyclerView.Adapter<ListAdapter.ViewHolder>() {

//    var dataSet = listOf<Mood>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val face: CustomView = view.findViewById(R.id.face)
        private val dateAndTime: TextView = view.findViewById(R.id.day_and_time)

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bindTo(mood: Mood?){
            face.state = mood?.mood ?: 0
            dateAndTime.text = mood?.dateAndTime
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bindTo(getItem(position))
    }

    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = dataSet.size

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         *
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        val diffCallback = object : DiffUtil.ItemCallback<Mood>() {
            override fun areItemsTheSame(oldItem: Mood, newItem: Mood): Boolean {
                return oldItem.moodId == newItem.moodId
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(
                oldItem: Mood,
                newItem: Mood
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
