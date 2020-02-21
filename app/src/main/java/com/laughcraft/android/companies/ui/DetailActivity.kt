package com.laughcraft.android.companies.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.laughcraft.android.companies.R
import com.laughcraft.android.companies.repository.Repository
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_det.*

class DetailActivity : AppCompatActivity() {
    
    var namesDisposable: Disposable? = null
    var infoDisposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_det)
        
        val id = intent.getIntExtra(ID_EXTRA, 0) + 1
        
        infoDisposable = Repository.of<CompanyInfo>()
            .query()
            .where("id", id.toString())
            .findFirst()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ initViews(it) }, { it.printStackTrace() })
    
        namesDisposable = Repository.of<CompanyName>()
            .query()
            .where("id", id.toString())
            .findFirst()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ initViews(it) }, { it.printStackTrace() })
    }
    
    private fun initViews(companyInfo: CompanyInfo){
        if (companyInfo.phone.isBlank() || companyInfo.phone.isEmpty()){
            card_view_phone.visibility = View.GONE
            fab_call.visibility = View.GONE
        }else{
            card_view_phone.visibility = View.VISIBLE
            fab_call.visibility = View.VISIBLE
            text_view_phone.text = companyInfo.phone
        }
    
        if (companyInfo.www.isBlank() || companyInfo.www.isEmpty()){
            card_view_website.visibility = View.GONE
        }else{
            card_view_website.visibility = View.VISIBLE
            text_view_website.text = companyInfo.www
        }
    
        if (companyInfo.description.isBlank() || companyInfo.description.isEmpty()){
            card_view_description.visibility = View.GONE
        }else{
            card_view_description.visibility = View.VISIBLE
            text_view_description.text = companyInfo.description
        }
    
        if (companyInfo.lat.isBlank() ||
            companyInfo.lat.isEmpty() ||
            companyInfo.lon.isEmpty() ||
            companyInfo.lon.isBlank() ||
            companyInfo.lat == "0" &&
            companyInfo.lon == "0") {
            card_view_coordinates.visibility = View.GONE
        }else{
            card_view_coordinates.visibility = View.VISIBLE
            text_view_coordinates.text = "${companyInfo.lat}, ${companyInfo.lon}"
        }
    }
    
    private fun initViews(companyName: CompanyName){
        collapsing_toolbar.title = companyName.name
        
        collapsing_toolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorWhite))
        collapsing_toolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite))
        
        Picasso.with(this)
            .load("http://megakohz.bget.ru/test_task/${companyName.img}")
            .fit()
            .error(R.drawable.error)
            .into(image_view_company)
    }
    
    companion object {
        val ID_EXTRA = "ID"
        fun getIntent(context: Context, id: Int): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(ID_EXTRA, id)
            }
        }
    }
    
    private fun dispose(disposable: Disposable?){
        if (disposable == null || disposable.isDisposed){
            return
        }else{
            disposable.dispose()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        dispose(namesDisposable)
        dispose(infoDisposable)
    }
}
