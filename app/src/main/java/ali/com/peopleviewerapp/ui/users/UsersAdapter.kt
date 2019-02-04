package ali.com.peopleviewerapp.ui.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import ali.com.peopleviewerapp.R
import ali.com.peopleviewerapp.model.UserInfo


class UsersAdapter(private val moviesList: List<UserInfo.DataModel>) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView

        init {
            name = view.findViewById<View>(R.id.name) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.name.text = movie.first_name
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}