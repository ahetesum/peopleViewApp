package ali.com.peopleviewerapp.ui.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import java.util.ArrayList

import ali.com.peopleviewerapp.R
import ali.com.peopleviewerapp.model.UserInfo
import ali.com.peopleviewerapp.viewModel.UserInfoViewModel


class UserListFragment : Fragment() {

    private var mViewModel: UserInfoViewModel? = null
    private val movieList = ArrayList<UserInfo.DataModel>()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: UsersAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.user_list_fragment, container, false)
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        mAdapter = UsersAdapter(movieList)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        mViewModel!!.init()
        mViewModel!!.movies!!.observe(this, Observer { movieModels ->
            movieList.addAll(movieModels!!.data)
            mAdapter!!.notifyDataSetChanged()
        })
    }

    companion object {

        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }

}
