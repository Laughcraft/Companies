package com.laughcraft.android.companies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.laughcraft.android.companies.R
import com.laughcraft.android.companies.repository.Repository
import com.laughcraft.android.companies.repository.entity.CompanyName
import com.laughcraft.android.companies.ui.adapter.MainAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var companiesDisposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //На MVVM меня уже не хватило =(
        
        recycler_view_main.layoutManager = GridLayoutManager(this, 2)
        
        val adapter = MainAdapter(emptyList()) {
            click -> startActivity(DetailActivity.getIntent(this, click))
        }
        recycler_view_main.adapter = adapter
        
        companiesDisposable = Repository.of<CompanyName>()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ (recycler_view_main.adapter as MainAdapter).apply {
                names = it
                notifyDataSetChanged()
            }
            
            }, {it.printStackTrace()})
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (companiesDisposable == null || companiesDisposable?.isDisposed!!){
            return
        }else{
            companiesDisposable!!.dispose()
        }
    }
}
